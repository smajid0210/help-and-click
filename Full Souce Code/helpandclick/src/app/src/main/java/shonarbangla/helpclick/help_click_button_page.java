package shonarbangla.helpclick;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

import java.util.Locale;

public class help_click_button_page extends AppCompatActivity implements View.OnClickListener{


    SessionManager session;
    private ShowcaseView showcaseView;
    private int contador=0,contador1=20;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    private Target t1,t2;
    public int gh=0;
    public Boolean exit = false;
    public static FloatingActionMenu materialDesignFAM;
    public static View bckgroundDimmer;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5;


    public Button button,cotton,bt5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_help_click_button_page);
        init();
        nit();
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        //setFloatingButtonControls();


        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item5);
        bckgroundDimmer = findViewById(R.id.background_dimmer);


        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(help_click_button_page.this,about_us.class);
                startActivity(toy);
                //TODO something when floating action menu first item clicked

            }
        });
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent toy = new Intent(help_click_button_page.this,emergencyPhoneNumbers .class);
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
                Intent toy = new Intent(help_click_button_page.this,settings_page.class);
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


        //System.out.println(session.getTutorial1()+"JJ");
        t1= new ViewTarget(R.id.help, this);
        t2= new ViewTarget(R.id.click, this);
        bt5 = (Button) findViewById(R.id.tutor2);
        if(session.getTutorial1()==0) {
            showcaseView =new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setOnClickListener(this)
                    .setContentTitle("Lets Help each other.")

                    .setStyle(R.style.Transperencia)

                    .build();
            showcaseView.setButtonText("Next");
            // session.createTutorial1(1);
        }
        bt5.setOnClickListener(this);
        //onBackPressed();



    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //finish();
        //System.exit(0);
        //System.exit(0);
        /*if (exit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }*/

    }


    public void init(){
        button = (Button)findViewById(R.id.help);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //System.out.println("abc");
                Intent toy = new Intent(help_click_button_page.this,incident_notice_button_page.class);
                startActivity(toy);
                //finish();
            }
        });
    }




    public void nit(){
        cotton = (Button)findViewById(R.id.click);
        cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(help_click_button_page.this,search_buttons_page.class);
                startActivity(toy);
                //finish();
            }
        });
    }
    /*private void setFloatingButtonControls() {
        this.bckgroundDimmer = findViewById(R.id.background_dimmer);
        this.materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        materialDesignFAM.setU
        // this.materialDesignFAM.setOnFloatingActionsMenuUpdateListener(new FloatingActionMenu.OnFloatingActionsMenuUpdateListener() {
           @Override
            public void onMenuExpanded() {
                bckgroundDimmer.setVisibility(View.VISIBLE);
            }

            @Override
            public void onMenuCollapsed() {
                bckgroundDimmer.setVisibility(View.GONE);
            }*/




    @Override
    public void onClick(View v) {

        if(session.getTutorial1()==0) {
            switch (contador) {

                case 0:
                    //if(session.getTutorial()!=0)break;

                    showcaseView.setShowcase(t1, true);
                    showcaseView.setContentTitle("Do you want to post a missing person notice or want to post about an unidentified person whom you have found? Click Here.");

                    break;
                case 1:
                    // if(session.getTutorial()!=0)break;
                    showcaseView.setShowcase(t2, true);
                    showcaseView.setContentTitle("Looking desperately for your lost friends? SEARCH here.");
                    //showcaseView.setContentTitle(" আপনি হারিয়ে যাওয়া কাউকে খুঁজে পেতে এখানে সার্চ  করুন  ");

                    showcaseView.setButtonText("GOT IT!");
                    break;

                case 2:
                    // if(session.getTutorial()!=0)break;
                    showcaseView.hide();
                    session.createTutorial1(1);
                    break;

            }
        }
        contador++;

        switch (contador1) {

            case 0:
                //if(session.getTutorial()!=0)break;

                showcaseView.setShowcase(t1, true);
                showcaseView.setContentTitle("Do you want to post a missing person notice or want to post about an unidentified person whom you have found? Click Here.");

                break;
            case 1:
                // if(session.getTutorial()!=0)break;
                showcaseView.setShowcase(t2, true);
                //showcaseView.setContentTitle("Looking desperately for your lost friends? SEARCH here.");
                showcaseView.setContentTitle("Looking desperately for your missing ones? Click Here.  ");

                showcaseView.setButtonText("GOT IT!");
                break;

            case 2:
                // if(session.getTutorial()!=0)break;
                showcaseView.hide();
                break;

        }
        contador1++;

        if(v.getId()==R.id.tutor2) {
            System.out.println("ghh");
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setOnClickListener(this)


                    .setStyle(R.style.Transperencia)

                    .build();
            contador1=0;
            showcaseView.setButtonText("Next");
        }
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
