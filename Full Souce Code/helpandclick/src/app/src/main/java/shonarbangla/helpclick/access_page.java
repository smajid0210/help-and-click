package shonarbangla.helpclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class  access_page extends AppCompatActivity {


    SessionManager session;
    EditText txtUsername, txtPassword;

    // login button
    Button btnLog, btn,btn1;
    RequestQueue requestQueue;
    public static String username = new String();
    String insertURL="http://198.211.96.87/helpandclick/v1/index.php/register";


    public Button button,futton,button1,cotton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        if (!session.isLoggedIn()) {
            setContentView(R.layout.activity_access_page);
            init1();
            fnit();
        }
        else
        {
            //setContentView(R.layout.activity_help_click_button_page);
            Intent launchNextActivity;
            launchNextActivity = new Intent(this, help_click_button_page.class);

            startActivity(launchNextActivity);
            //finish();
            //init();
           // nit();

        }
    }
    public void onBackPressed() {
        //finish();
    }

    public void init1(){
        button = (Button)findViewById(R.id.sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(access_page.this,Sign_in_page.class);
                startActivity(toy);
            }
        });
    }

    public void fnit(){
        button = (Button)findViewById(R.id.create_an_account_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent toy = new Intent(access_page.this,create_account_page.class);
                startActivity(toy);
            }
        });
    }
  /*  public void init(){
        button1 = (Button)findViewById(R.id.help);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //System.out.println("abc");
                Intent toy = new Intent(access_page.this,incident_notice_button_page.class);
                startActivity(toy);
            }
        });
    } */




  /*  public void nit(){
        cotton = (Button)findViewById(R.id.click);
        cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(access_page.this,search_buttons_page.class);
                startActivity(toy);
            }
        });
    } */



}
