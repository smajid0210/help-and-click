package shonarbangla.helpclick;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static shonarbangla.helpclick.All_Incidents.myBitmap;

public class big_image_incidents extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image_incidents);
        ImageView lo= (ImageView)findViewById(R.id.big_image_incidents);
        Picasso.with(this)
                .load(All_Incidents.image)
                .fit().centerInside()
                .into(lo);

    }
            }



