package mejia.sam.free_bb;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mejia.sam.free_bb.model.Department;
import mejia.sam.free_bb.model.Person;
import mejia.sam.free_bb.util.RetrofitHelper;
import mejia.sam.free_bb.util.adapter.PersonAdapter;
import mejia.sam.free_bb.util.dagger.App;
import mejia.sam.free_bb.util.rest.PerService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static mejia.sam.free_bb.util.Constants.BASE_URL;

public class ListActivity extends AppCompatActivity implements OnEachItemClick {
    @Inject
    RetrofitHelper retrofitHelper;
    Department department;
    @BindView(R.id.recyview)
    RecyclerView recyclerView;
    private PersonAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        App.getAppComponent(this).inject(this);

    try {
        PerService perService = retrofitHelper.create();
        Call<Department> departmentCall = perService.getPersons();

        departmentCall.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                department = response.body();
              final  List<Person> myCombinedList = (department.getMobile());

                int mobileLength = myCombinedList.size();
                myCombinedList.addAll(department.getCXP());
                int CXPLength = myCombinedList.size();
                myCombinedList.addAll(department.getLaunchpad());
                int launchLength = myCombinedList.size();
                mAdapter =   new PersonAdapter(myCombinedList ,mobileLength,CXPLength,launchLength,ListActivity.this);
                RecyclerView.LayoutManager  mLayoutManager  =   new
                        LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {

            }
        });
    }
    catch (Exception e){
        e.printStackTrace();
        }
    }


    @Override
    public void onEachClick(View view, Person person , String dep) {
        Intent i = new Intent(ListActivity.this, DetailActivity.class);
        i.putExtra("detail",person );
        i.putExtra("dep",dep);
        startActivity(i);
    }
}
