package shonarbangla.helpclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    SessionManager session;
    EditText txtUsername, txtPassword;

    // login button
    Button btnLog, btn,btn1;
    RequestQueue requestQueue;
    public static String username = new String();
    String insertURL="http://198.211.96.87/helpandclick/v1/index.php/register";
    public Button button,button1,cotton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        setContentView(R.layout.activity_main);
        init();
    }


    public void init(){
        button = (Button)findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,introslide.class);
                startActivity(toy);
            }
        });
}


}
