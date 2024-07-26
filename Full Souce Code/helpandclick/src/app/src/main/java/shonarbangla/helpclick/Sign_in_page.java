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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Sign_in_page extends AppCompatActivity {

    SessionManager session;
    EditText txtUsername, txtPassword;

    // login button
    Button btnLog, btn,btn1;
    RequestQueue requestQueue;
    public static String username = new String();
    public static String password = new String();
    public static String rest = new String();
    String insertURL="http://198.211.96.87/helpandclick/v1/index.php/login";

    public Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        session = new SessionManager(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        init();

    }

    public void init(){
        button3 = (Button)findViewById(R.id.sign_in_button_click);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = txtUsername.getText().toString();
                password = txtPassword.getText().toString();
                if (!(username.trim().length() > 0 && password.trim().length() >= 6)) {
                    Toast.makeText(getApplicationContext(), "Sorry!! Sign in not successful. Please fill up all the fields correctly",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            rest = response.toString();
                            System.out.println(response.toString());
                            String fuks = response;
                            System.out.println(fuks.charAt(9));
                            if (username.trim().length() > 0 && password.trim().length() >= 3 && fuks.charAt(11) == 'f') {
                                session.createLoginSession(username, password);
                                session.createphone("0");
                                Intent toy = new Intent(Sign_in_page.this, help_click_button_page.class);
                                startActivity(toy);
                            } else if (fuks.charAt(11) == 't') {
                                Toast.makeText(getApplicationContext(), "Sorry!! Sign in not successful. Password and username don't match.Try again",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error.equals("com.android.volley.TimeoutError"))
                            {
                                //Toast.makeText(getApplicationContext(), "Tor baap",
                                  //      Toast.LENGTH_LONG)
                                    //    .show();
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Sorry!! Sign in not successful due to an internal error. Please try again",
                                        Toast.LENGTH_LONG)
                                        .show();
                            }
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
                    System.out.println("G" + rest);
                    System.out.println("HI");
                    requestQueue.add(request);


                }
            }
        });

    }



}
