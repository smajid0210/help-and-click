package shonarbangla.helpclick;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import static shonarbangla.helpclick.search_buttons_page.ages;
import static shonarbangla.helpclick.search_buttons_page.genders;
import static shonarbangla.helpclick.search_buttons_page.ids;
import static shonarbangla.helpclick.search_buttons_page.imgcounter;
import static shonarbangla.helpclick.search_buttons_page.imgs;
import static shonarbangla.helpclick.search_buttons_page.locations;


public class  All_Incidents extends AppCompatActivity implements View.OnClickListener {


    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    public int year, month, day;
    public static String selectincident;
    Map<String,Integer> cons;

    public int c=0,c1=0,po1=0,po2=0;
    public int d=0,e=0,e1=0,f1=0;
    String showUrl="http://198.211.96.87/helpandclick/v1/index.php/tasksfull/";
    String showUrl1="http://198.211.96.87/helpandclick/v1/index.php/notes/";
    String showUrl2="http://198.211.96.87/helpandclick/v1/index.php/commentsinc/";
    String showUrl3="http://198.211.96.87/helpandclick/v1/index.php/tasksall/";
    public static Bitmap myBitmap;
    public static String ba1,result,age,gender,date,thana,locdet,contact,descript,post_time,comment,slage,slgender,sldate,id,location,det,image;
    public static ArrayList<String> comments;
    public static ArrayList<String>times;
    RequestQueue requestQueue;
    SessionManager session;
    public Button bl;


    Spinner datababa, oreebaba;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        times= new ArrayList<String>();
        comments= new ArrayList<String>();

        ListView list;
        setContentView(R.layout.activity_all__incidents);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        showUrl3+=session.getUser();


        datababa = (Spinner) findViewById(R.id.spinner_incident_gender);
        adapter = ArrayAdapter.createFromResource(this, R.array.genders, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datababa.setAdapter(adapter);

        oreebaba = (Spinner) findViewById(R.id.spinner_incident_age);
        adapter = ArrayAdapter.createFromResource(this, R.array.ages, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oreebaba.setAdapter(adapter);

        bl=(Button)findViewById(R.id.button_all_incidents);



        dateView = (TextView) findViewById(R.id.textView3_all_incidents);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);

        CustomListAdapter adapter=new CustomListAdapter(this, ages, imgcounter, genders, locations, ids);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem = ids.get(+position);
                setContentView(R.layout.activity_loadingimg);


                selectincident = ids.get(+position);
                if (c == 0) {
                    showUrl += selectincident;
                    c++;
                }
                System.out.println(showUrl);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //System.out.println(response.toString());
                        try {
                            JSONArray incidents = response.getJSONArray("incidents");
                            for (int i = 0; i < incidents.length(); i++) {
                                JSONObject incident = incidents.getJSONObject(i);
                                d++;

                                age = incident.getString("age");
                                gender = incident.getString("gender");
                                date = incident.getString("date of incident");
                                thana = incident.getString("location");
                                locdet = incident.getString("location details");
                                contact = incident.getString("contact");
                                descript = incident.getString("description");


                                image = incident.getString("bigimage");

                                if (image.length() != 0) {

                                    //cimg = (ImageView) findViewById(R.id.imageView144);
                                    //cimg.setImageBitmap(myBitmap);
                                    /*imgs.add(myBitmap);
                                    ages.add(age);
                                    genders.add(gender);
                                    thanas.add(thana);

                                    dates.add(date);
                                    locationdet.add(locdet);
                                    contacts.add(contact);
                                    descriptions.add(descript);*/
                                    //Intent intent1 = new Intent(All_Incidents.this, Comment_page_for_incidents.class);
                                   // startActivity(intent1);
                                }
                                if(age!=null && d!=0) {


                                    System.out.println(ages.size());
                                    Intent intent1 = new Intent(All_Incidents.this, Comment_page_for_incidents.class);
                                    startActivity(intent1);
                                }


                            }
                            /*if (d > 0) {
                                System.out.println(ages.size());
                                Intent intent1 = new Intent(searchList.this, Comment_page_for_view_my_posts.class);
                                startActivity(intent1);
                            }*/


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        System.out.println("eee");
                        System.out.append(error.getMessage());

                    }
                });
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        20000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                requestQueue.add(jsonObjectRequest);

                if (c1 == 0) {
                    showUrl2 += selectincident;
                    c1++;
                }
                System.out.println(showUrl2);

                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET,
                            showUrl2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response.toString());
                            try { times= new ArrayList<String>();
                                comments= new ArrayList<String>();
                                JSONArray incidents = response.getJSONArray("comments_posts");
                                for (int i = 0; i < incidents.length(); i++) {
                                    JSONObject incident = incidents.getJSONObject(i);
                                    e++;


                                    post_time = incident.getString("username");
                                    System.out.println(post_time);
                                    comment = incident.getString("comment");
                                    times.add(post_time);
                                    comments.add(comment);


                                }


                            } catch (JSONException e) {

                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("eee1");
                            System.out.append(error.getMessage());

                        }
                    });
                    jsonObjectRequest1.setRetryPolicy(new DefaultRetryPolicy(
                            20000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    requestQueue.add(jsonObjectRequest1);




                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
            }

        });
        bl.setOnClickListener(this);

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);

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
                    showDate(arg1, arg2 + 1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));


    }
    public Bitmap ConvertToImage(String image){
        try{
            InputStream stream = new ByteArrayInputStream(Base64.decode(image.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            Log.v("Ben", "Image Converted");
            return bitmap;
        }
        catch (Exception e) {

            return null;
        }
    }
    @Override
    public void onClick(View view) {


     if(view.getId()==R.id.button_all_incidents)
     {
         slage = oreebaba.getSelectedItem().toString();
         slgender = datababa.getSelectedItem().toString();


         sldate = Integer.toString(year)+"-"+Integer.toString(month+1)+"-"+Integer.toString(day);
         ages= new ArrayList<String>();
         genders= new ArrayList<String>();
         locations= new ArrayList<String>();
         imgs= new ArrayList<Bitmap>();
         ids= new ArrayList<String>();
         imgcounter= new ArrayList<String>();

         JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                 showUrl3, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 System.out.println(response.toString());
                 try {
                     JSONArray incidents = response.getJSONArray("incidents");
                     for (int i = 0; i < incidents.length(); i++) {
                         e=0;
                         JSONObject incident = incidents.getJSONObject(i);
                         f1++;
                         //id= Integer.toString(incident.getInt("id"));
                         id=incident.getString("id");
                         age = incident.getString("age");

                         gender = incident.getString("gender");
                         location = incident.getString("location");
                         det= incident.getString("date of incident");
                         if(slage.equals("Select age"))
                             e++;
                         if(slgender.equals("Select gender"))
                            e++;

                         if(slage.equals(age))e++;
                         if(slgender.equals(gender))e++;
                         if(det.compareTo(sldate)>=0)e++;




                         //det= incident.getString("det");


                         String image = incident.getString("bigimage");
                         System.out.println(det+" "+sldate+ " " + det.compareTo(sldate));
                         if(image.length()!=0 && e==3) {
                             imgcounter.add(image);
                             ages.add(age);
                             genders.add(gender);
                             locations.add(location);
                             ids.add(id);


                         }




                     }
                     if(f1>0) {
                         System.out.println(ages.size());
                         Intent intent1 = new Intent(All_Incidents.this, All_Incidents.class);
                         startActivity(intent1);
                     }



                 }


                 catch (JSONException e) {
                     e.printStackTrace();
                 }

             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 System.out.append(error.getMessage());

             }
         });
         jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                 20000,
                 DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                 DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

         requestQueue.add(jsonObjectRequest);

     }


    }

    public void onBackPressed()
    { System.out.println("kolu");
        ages= new ArrayList<>();
        genders= new ArrayList<>();
        locations= new ArrayList<>();
        comments= new ArrayList<>();
        times= new ArrayList<>();
        ids= new ArrayList<>();
        imgs= new ArrayList<>();
        Intent intent1 = new Intent(All_Incidents.this, search_buttons_page.class);
        startActivity(intent1);
        //finish();

    }

}
