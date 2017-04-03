package mejia.sam.free_bb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mejia.sam.free_bb.model.Person;

import static mejia.sam.free_bb.util.Constants.BASE_PHOTO;

public class BigImage extends AppCompatActivity {
@BindView(R.id.myBigImage)
    ImageView myBigImage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image);
        ButterKnife.bind(this);
        Intent i    =   getIntent();
        String pic = i.getStringExtra("bigimage");
//        if(pic != null) {
        Glide.with(BigImage.this).load(BASE_PHOTO+pic).error(R.mipmap.ic_launcher).into(myBigImage);
//        }
//        else {
//            myBigImage.setImageResource(R.mipmap.ic_launcher);
//        }
    }
}
