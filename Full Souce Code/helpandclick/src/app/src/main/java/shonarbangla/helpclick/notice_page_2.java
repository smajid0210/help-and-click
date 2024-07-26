package shonarbangla.helpclick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.DatePickerDialog;
import android.app.Dialog;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class notice_page_2 extends AppCompatActivity {



    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;
    private static final int x=1,y=2;
    Button btpic, btnup,btsub;
    private Uri fileUri;
    String picturePath;
    Uri selectedImage;
    Bitmap photo;

    String ba1;
    ImageView image1,image2;
    Spinner ag,gendr,loction;
    EditText des,loct,cont,nam,occ,app;
    String ag1,gendr1,loction1,loction_det1,date1,contact1,desc,nam1,occ1,app1;
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
    String insertURL="http://198.211.96.87/helpandclick/v1/index.php/notes";
    RequestQueue requestQueue;
    Spinner datababa, oreebaba ;
    ArrayAdapter<CharSequence> adapter;



    public Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_page_2);
        session = new SessionManager(getApplicationContext());
        requestQueue = Volley.newRequestQueue(getApplicationContext());



        datababa=(Spinner) findViewById(R.id.spinner11);
        adapter=ArrayAdapter.createFromResource(this, R.array.genders,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datababa.setAdapter(adapter);

        oreebaba=(Spinner) findViewById(R.id.age11);
        adapter=ArrayAdapter.createFromResource(this, R.array.ages,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oreebaba.setAdapter(adapter);
        des=(EditText)findViewById(R.id.add);
        nam= (EditText)findViewById(R.id.Name);
        occ= (EditText)findViewById(R.id.appearance);
        app= (EditText)findViewById(R.id.app);

        loct=(EditText)findViewById(R.id.location11);
        cont=(EditText)findViewById(R.id.contact11);





        dateView = (TextView) findViewById(R.id.textView33);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        init();
    }



    public void init(){
        button = (Button)findViewById(R.id.upload_notice);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_loadingimg);
                ag1 = oreebaba.getSelectedItem().toString();
                gendr1 = datababa.getSelectedItem().toString();
                nam1 = nam.getText().toString();
                occ1= occ.getText().toString();
                if(ag1.equals("Select age"))
                   ag1= new String();
                if(gendr1.equals("Select gender"))
                    gendr1= new String();

                System.out.println(gendr1);


                //System.out.println(occ1);
                if(occ1.length()==0)
                    occ1="none";
                System.out.println(occ1);
                app1= app.getText().toString();
                if(app1.length()==0)
                    app1="none";
                date1= Integer.toString(year)+"-" + Integer.toString(month+1)+"-" + Integer.toString(day);
                if(date1.length()==0)
                    date1="none";
                desc = des.getText().toString();
                if(desc.length()==0)
                   desc="none";
                loction1= loct.getText().toString();
                if(loction1.length()==0)
                    loction1="none";
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
                        Intent toy = new Intent(notice_page_2.this,search_buttons_page.class);
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
                            Intent toy = new Intent(notice_page_2.this,search_buttons_page.class);
                            startActivity(toy);
                        }

                        else
                        {
                            Toast.makeText(getApplicationContext(), "Sorry!! Notice not uploaded. Try once again. Check if all required fields are not empty",
                                    Toast.LENGTH_LONG)
                                    .show();
                            Intent toy = new Intent(notice_page_2.this,notice_page_2.class);
                            startActivity(toy);
                            //finish();

                        }
                            System.out.println(error);
                    }
                })

                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("username",session.getUser());
                        parameters.put("image",session.getUser()+session.getpic());
                        session.createpic(session.getpic()+1);
                        parameters.put("imgmap",notice_page_1.upldimg1);
                        parameters.put("name",nam1);
                        parameters.put("age",ag1);
                        parameters.put("gender",gendr1);
                        parameters.put("date",date1);
                        parameters.put("location",loction1);
                        parameters.put("occupation",occ1);
                        parameters.put("appearance",app1);

                        parameters.put("contact",contact1);

                        parameters.put("addition",desc);

                        return parameters;
                    }
                };
                System.out.println("HI");
                requestQueue.add(request);




            }
        });
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
                    month= arg2;
                    day= arg3;
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }



    }


