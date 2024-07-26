package shonarbangla.helpclick;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.Locale;

public class incident_notice_button_page extends AppCompatActivity implements View.OnClickListener {
    SessionManager session;
    private ShowcaseView showcaseView;
    private int contador=0,contador1=20;;
    private Target t1,t2;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;

    public static FloatingActionMenu materialDesignFAM;
    public Button button,lutton,bt5;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5;

    public void init(){
        button = (Button)findViewById(R.id.incident);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(incident_notice_button_page.this,incident_page_1.class);
                startActivity(toy);
                //finish();
            }
        });
    }




    public void nit(){
        lutton = (Button)findViewById(R.id.notice);
        lutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(incident_notice_button_page.this,notice_page_1.class);
                startActivity(toy);
                //finish();
            }
        });
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_notice_button_page);
        init();
        nit();
        session = new SessionManager(getApplicationContext());
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menuv);
        //setFloatingButtonControls();


        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_itemv1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_itemv2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_itemv3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_itemv4);
        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_itemv5);

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(incident_notice_button_page.this,about_us.class);
                startActivity(toy);
                //TODO something when floating action menu first item clicked

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(incident_notice_button_page.this,emergencyPhoneNumbers .class);
                startActivity(toy);
                //TODO something when floating action menu second item clicked

            }
        });
        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu third item clicked
                makePhoneCall(v);

            }
        });

        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(incident_notice_button_page.this,settings_page.class);
                startActivity(toy);
                //TODO something when floating action menu third item clicked

            }
        });
        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                try
                {
                    startActivity(intent);
                }
                catch(ActivityNotFoundException ex)
                {
                    try
                    {
                        Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(unrestrictedIntent);
                    }
                    catch(ActivityNotFoundException innerEx)
                    {
                        //Toast.makeText(this, "Please install a maps application", Toast.LENGTH_LONG).show();
                    }
                }//TODO something when floating action menu third item clicked

            }
        });

        t1= new ViewTarget(R.id.incident, this);
        t2= new ViewTarget(R.id.notice, this);
        bt5 = (Button) findViewById(R.id.tutor3);
        System.out.println(session.getTutorial2()+"HH");
        if(session.getTutorial2()==0) {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setOnClickListener(this)


                    .setStyle(R.style.Transperencia)

                    .build();
            showcaseView.setButtonText("Next");
           // session.createTutorial2(1);
        }
        bt5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(session.getTutorial2()==0) {
            switch (contador) {

                case 0:
                    showcaseView.setShowcase(t1, true);
                    showcaseView.setContentText(" Do you want to provide information about any unidentified person? Click Here. ");

                    break;
                case 1:
                    showcaseView.setShowcase(t2, true);
                   // showcaseView.setContentText("Want to post notice about your friends or family who is lost!");
                    showcaseView.setContentText("Do you want to post a missing person notice? Click Here.");
                    showcaseView.setButtonText("GOT IT!");
                    break;

                case 2:
                    showcaseView.hide();
                    session.createTutorial2(1);
                    break;

            }
        }
        contador ++;

        switch (contador1) {

            case 0:
                showcaseView.setShowcase(t1, true);
                showcaseView.setContentText("Do you want to provide information about any unidentified person? Click Here. ");

                break;
            case 1:
                showcaseView.setShowcase(t2, true);
                showcaseView.setContentText("Do you want to post a missing person notice? Click Here.");

                showcaseView.setButtonText("GOT IT!");
                break;

            case 2:
                showcaseView.hide();
                break;

        }
        contador1 ++;

        if(v.getId()==R.id.tutor3) {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setOnClickListener(this)


                    .setStyle(R.style.Transperencia)

                    .build();
            contador1=0;
            showcaseView.setButtonText("Next");
        }


    }
    public void onBackPressed()
    {
        Intent toy = new Intent(incident_notice_button_page.this,help_click_button_page.class);
        startActivity(toy);
        //finish();
    }
    public void makePhoneCall(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
            }
        }
    }

    private void callPhone() {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" +session.getphone() ));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }
}
