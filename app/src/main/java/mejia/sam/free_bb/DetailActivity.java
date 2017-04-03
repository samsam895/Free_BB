package mejia.sam.free_bb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mejia.sam.free_bb.model.Person;

import static mejia.sam.free_bb.util.Constants.BASE_PHOTO;

/**
 * Created by User on 4/2/2017.
 */

public class DetailActivity extends AppCompatActivity {
    private Person  person;
    @BindView(R.id.imageView)
    ImageView myImag ;
    @BindView(R.id.depdetail)
    TextView depdetail ;
    @BindView(R.id.namedetail)
    TextView namedetail ;
    @BindView(R.id.roledetail)
    TextView roledetail ;

    @Override
    protected void onCreate(Bundle saveInsatanceState){
        super.onCreate(saveInsatanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent i    =   getIntent();
        person  = (Person) i.getSerializableExtra("detail");
        String dep = i.getStringExtra("dep");
        depdetail.setText(dep);
        namedetail.setText(person.getName()+" "+person.getSurname());
        roledetail.setText(person.getRole());
//        if(person.getPhoto()!= null) {
            Glide.with(DetailActivity.this).load(BASE_PHOTO + person.getPhoto()).error(R.mipmap.ic_launcher).into(myImag);
//        }
//        else {
//            myImag.setImageResource(R.mipmap.ic_launcher);
//        }
    }

    public void bigImage(View view) {
        Intent i = new Intent(DetailActivity.this, BigImage.class);
        i.putExtra("bigimage",person.getPhoto() );
        startActivity(i);


        //if (person.getPhoto() != null)
       // Log.d("To BigImage", person.getPhoto());
    }

    public void sendAnEmail(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL , new String[]{person.getEmail()});
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
    }
}
