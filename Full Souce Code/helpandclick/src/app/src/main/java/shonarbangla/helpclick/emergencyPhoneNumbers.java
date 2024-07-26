package shonarbangla.helpclick;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class emergencyPhoneNumbers extends AppCompatActivity {


    CircleMenu circleMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_phone_numbers);


        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.mipmap.police)

                .addSubMenu(Color.parseColor("#CCFF90"), R.mipmap.hospital)

                .addSubMenu(Color.parseColor("#3F51B5"), R.mipmap.blood);


        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             Toast.makeText(emergencyPhoneNumbers.this, "Eemergency Police Numbers", Toast.LENGTH_SHORT).show();
                                                             Intent toy = new Intent(emergencyPhoneNumbers.this,police.class);
                                                             startActivity(toy);
                                                             break;
                                                         case 1:
                                                             Toast.makeText(emergencyPhoneNumbers.this, "Eemergency Hospital Numbers", Toast.LENGTH_SHORT).show();
                                                             Intent toy1 = new Intent(emergencyPhoneNumbers.this,hospital.class);
                                                             startActivity(toy1);
                                                             break;
                                                         case 2:
                                                             Toast.makeText(emergencyPhoneNumbers.this, "Eemergency Blood Bank Numbers", Toast.LENGTH_SHORT).show();
                                                             Intent toy2 = new Intent(emergencyPhoneNumbers.this,bloodbank.class);
                                                             startActivity(toy2);
                                                             break;

                                                     }
                                                 }
                                             }

        );



    }



}
