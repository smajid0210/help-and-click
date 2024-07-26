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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class create_account_page extends AppCompatActivity {

    SessionManager session;
    EditText txtUsername, txtPassword;
    public static String password= new String();
    public static String rest = new String();

    // login button
    Button btnLog, btn,btn1;
    RequestQueue requestQueue;
    public static String username = new String();
    String insertURL="http://198.211.96.87/helpandclick/v1/index.php/register";
    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_page);
        txtUsername = (EditText) findViewById(R.id.Username);
        txtPassword = (EditText) findViewById(R.id.username_exists);
        session = new SessionManager(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        init();

    }

    public void init(){
        button = (Button)findViewById(R.id.sign_in_button_abar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                if(!(username.trim().length() > 0 && password.trim().length() >= 6))
                {
                    Toast.makeText(getApplicationContext(), "Sorry!! Sign up not successful. Password/username might be too short",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else
                {
                    StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {

                            System.out.println(response.toString());
                            String fuks=response;
                            System.out.println(fuks.charAt(11));





                            if (username.trim().length() > 0 && password.trim().length() >= 6 && fuks.charAt(11)=='f') {
                                session.createLoginSession(username, password);
                                session.createpic(0);
                                session.createphone("0");

                                Intent toy = new Intent(create_account_page.this, help_click_button_page.class);
                                startActivity(toy);
                            }

                            else if(fuks.charAt(11)=='t')
                            {
                                Toast.makeText(getApplicationContext(), "Sorry!! Sign up not successful. Username already exists. Try a new one",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
                            //System.out.println(response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println(error);
                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> parameters = new HashMap<String, String>();
                            parameters.put("username", txtUsername.getText().toString());
                            parameters.put("password", txtPassword.getText().toString());


                            return parameters;
                        }
                    };
                    request.setRetryPolicy(new DefaultRetryPolicy(
                            20000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    System.out.println("G"+rest);
                    requestQueue.add(request);
                }
            }
        });
    }





}
