package shonarbangla.helpclick;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.security.SecurityPermission;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class incident_page_2 extends AppCompatActivity {




    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    public int year, month, day;
    private static final int x=1,y=2;
    Button btpic, btnup,btsub;
    private Uri fileUri;
    String picturePath;
    Uri selectedImage;
    Bitmap photo;

    String ba1;
    ImageView image1,image2;
    Spinner ag,gendr,loction;
    EditText des,loct,cont;
    String ag1,gendr1,loction1,loction_det1,date1,contact1,desc;
    public static String URL = "Paste your URL here";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    //Declaring an Spinner
    private Spinner spinner1;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;
    SessionManager session;
    //TextViews to display details
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;
    String insertURL="http://198.211.96.87/helpandclick/v1/index.php/tasks";
    RequestQueue requestQueue;



    public Button button;




    Spinner spinner, oreebaba, thana;
    ArrayAdapter<CharSequence> adapter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_page_2);
        session = new SessionManager(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());




        spinner=(Spinner) findViewById(R.id.spinner);
        adapter=ArrayAdapter.createFromResource(this, R.array.genders,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        oreebaba=(Spinner) findViewById(R.id.age);
        adapter=ArrayAdapter.createFromResource(this, R.array.ages,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oreebaba.setAdapter(adapter);

        thana=(Spinner) findViewById(R.id.thana);
        adapter=ArrayAdapter.createFromResource(this, R.array.Thana,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        thana.setAdapter(adapter);
        des=(EditText)findViewById(R.id.contact);
        loct=(EditText)findViewById(R.id.location);
        cont=(EditText)findViewById(R.id.adress);



        dateView = (TextView) findViewById(R.id.textView3);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        init();
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,myDateListener, year, month, day);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    year=arg1;
                    month=arg2;
                    day=arg3;
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));


    }
    public void init(){
        button = (Button)findViewById(R.id.upload_incident);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_loadingimg);
                ag1 = oreebaba.getSelectedItem().toString();
                gendr1 = spinner.getSelectedItem().toString();
                loction1 = thana.getSelectedItem().toString();
                date1= Integer.toString(year)+"-" + Integer.toString(month+1)+"-" + Integer.toString(day);
                desc = des.getText().toString();
                if(ag1.equals("Select age"))
                    ag1= new String();
                if(gendr1.equals("Select gender"))
                    gendr1= new String();
                if(loction1.equals("Nearby Thana"))
                    loction1= new String();
                if(desc.length()==0)
                    desc="none";
                loction_det1= loct.getText().toString();
                if(loction_det1.length()==0)
                    loction_det1="none";
                contact1= cont.getText().toString();
                if(contact1.length()==0)
                    contact1="none";
                StringRequest request = new StringRequest(Request.Method.POST, insertURL, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
                        Toast.makeText(getApplicationContext(), "Upload Successful",
                                Toast.LENGTH_LONG)
                                .show();
                        Intent toy = new Intent(incident_page_2.this,search_buttons_page.class);
                        startActivity(toy);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        if (error instanceof TimeoutError)
                        {
                            Toast.makeText(getApplicationContext(), "Upload Successful",
                                    Toast.LENGTH_LONG)
                                    .show();
                            Intent toy = new Intent(incident_page_2.this,search_buttons_page.class);
                            startActivity(toy);
                            //finish();
                        }


                        else
                        {
                            Toast.makeText(getApplicationContext(), "Sorry!! Post not uploaded. Try once again. Check if all required fields are not empty",
                                    Toast.LENGTH_LONG)
                                    .show();
                            Intent toy = new Intent(incident_page_2.this,incident_page_2.class);
                            startActivity(toy);


                        }
                            System.out.println(error);
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("username",session.getUser());


                        parameters.put("image",session.getUser()+session.getpic());
                        session.createpic(session.getpic()+1);
                        parameters.put("imgmap",incident_page_1.upldimg);
                        parameters.put("age",ag1);
                        parameters.put("gender",gendr1);
                        parameters.put("date",date1);
                        parameters.put("location",loction1);
                        parameters.put("location_details",loction_det1);
                        parameters.put("contact",contact1);

                        parameters.put("description",desc);






                        return parameters;
                    }
                };
                System.out.println("HI");
                requestQueue.add(request);


            }
        });
    }


}
