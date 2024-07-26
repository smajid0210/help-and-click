package shonarbangla.helpclick;

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
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.Map;

import static shonarbangla.helpclick.search_buttons_page.ages;
import static shonarbangla.helpclick.search_buttons_page.genders;
import static shonarbangla.helpclick.search_buttons_page.ids;
import static shonarbangla.helpclick.search_buttons_page.imgcounter;
import static shonarbangla.helpclick.search_buttons_page.imgs;
import static shonarbangla.helpclick.search_buttons_page.locations;

public class all_notices extends AppCompatActivity implements View.OnClickListener {


    ListView list;
    public static String selectnotice;
    Spinner datababa, oreebaba ;
    ArrayAdapter<CharSequence> adapter;
    public int c=0,c1=0,f1=0,po1=0,po2=0;
    public int d=0,e=0;
    Map<String,Integer> cons;
    String showUrl="http://198.211.96.87/helpandclick/v1/index.php/notesfull/";
    String showUrl1="http://198.211.96.87/helpandclick/v1/index.php/notes/";
    String showUrl2="http://198.211.96.87/helpandclick/v1/index.php/commentsnot/";
    String showUrl3="http://198.211.96.87/helpandclick/v1/index.php/notesall/";
    public static Bitmap myBitmap;
    public static String ba1,result,age,gender,date,name,locdet,con,descript,occ,app,post_time,comment,id,slage,slgender,image;
    public static ArrayList<String> comments;
    public static ArrayList<String>times;
    RequestQueue requestQueue;
    SessionManager session;
    public Button bl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        times= new ArrayList<String>();
        comments= new ArrayList<String>();
        setContentView(R.layout.activity_all_notices);
        System.out.println("abool1");
        datababa=(Spinner) findViewById(R.id.spinner_notices_gender);
        adapter= ArrayAdapter.createFromResource(this, R.array.genders,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datababa.setAdapter(adapter);

        oreebaba=(Spinner) findViewById(R.id.spinner_notices_age);
        adapter=ArrayAdapter.createFromResource(this, R.array.ages,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        oreebaba.setAdapter(adapter);

        times= new ArrayList<String>();
        comments= new ArrayList<String>();


        requestQueue = Volley.newRequestQueue(getApplicationContext());
        session = new SessionManager(getApplicationContext());
        bl=(Button)findViewById(R.id.button_all_notices);
        showUrl3+=session.getUser();



        CustomListAdapter1 adapter=new CustomListAdapter1(this, ages, imgcounter, genders, locations, ids);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                String Slecteditem= ids.get(+position);
                setContentView(R.layout.activity_loadingimg);

                selectnotice = ids.get(+position);
                Slecteditem+="abul";
                //Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();
                if (c == 0) {
                    showUrl += selectnotice;
                    c++;
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray incidents = response.getJSONArray("notices");
                            for (int i = 0; i < incidents.length(); i++) {
                                JSONObject incident = incidents.getJSONObject(i);
                                d++;

                                age = incident.getString("age");
                                gender = incident.getString("gender");
                                date = incident.getString("date of incident");
                                name = incident.getString("name");
                                locdet = incident.getString("location");
                                con = incident.getString("contact");
                                descript = incident.getString("description");
                                occ= incident.getString("occupation");
                                app= incident.getString("appearance");


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


                                }
                                if(age!=null && d>0)
                                {
                                    Intent intent1 = new Intent(all_notices.this, Comment_Page_for_notice.class);
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
                        System.out.append(error.getMessage());

                    }
                });
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                        20000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                requestQueue.add(jsonObjectRequest);

                if (c1 == 0) {
                    showUrl2 += selectnotice;
                    c1++;
                }
                //po1=cons.get(Slecteditem);

                    JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET,
                            showUrl2, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response.toString());
                            try {  times= new ArrayList<String>();
                                   comments= new ArrayList<String>();
                                JSONArray incidents = response.getJSONArray("comments_notices");
                                for (int i = 0; i < incidents.length(); i++) {
                                    JSONObject incident = incidents.getJSONObject(i);
                                    e++;


                                    post_time = incident.getString("username");
                                    comment = incident.getString("comment");
                                    times.add(post_time);
                                    comments.add(comment);


                                }

                                System.out.println(ages.size());


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
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

        if(view.getId()==R.id.button_all_notices)
        {
            slage = oreebaba.getSelectedItem().toString();
            slgender = datababa.getSelectedItem().toString();


            //sldate = Integer.toString(year)+"-"+Integer.toString(month+1)+"-"+Integer.toString(day);
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
                        JSONArray incidents = response.getJSONArray("notices");
                        for (int i = 0; i < incidents.length(); i++) {
                            e=0;
                            JSONObject incident = incidents.getJSONObject(i);
                            f1++;
                            id= incident.getString("id");
                            age = incident.getString("age");

                            gender = incident.getString("gender");
                            locdet = incident.getString("name");
                            //det= incident.getString("date of incident");
                            if(slage.equals("Select age"))
                                e++;
                            if(slgender.equals("Select gender"))
                                e++;

                            if(slage.equals(age))e++;
                            if(slgender.equals(gender))e++;
                            //if(det.compareTo(sldate)>=0)e++;




                            //det= incident.getString("det");


                            String image = incident.getString("bigimage");
                            //System.out.println(det+" "+sldate+ " " + det.compareTo(sldate));
                            if(image.length()!=0 && e==2) {
                                imgcounter.add(image);
                                ages.add(age);
                                genders.add(gender);
                                locations.add(locdet);
                                ids.add(id);


                            }




                        }
                        if(f1>0) {
                            System.out.println(ages.size());
                            Intent intent1 = new Intent(all_notices.this, all_notices.class);
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
    {
        ages= new ArrayList<>();
        genders= new ArrayList<>();
        locations= new ArrayList<>();
        comments= new ArrayList<>();
        times= new ArrayList<>();
        ids= new ArrayList<>();
        imgs= new ArrayList<>();
        Intent intent1 = new Intent(all_notices.this, search_buttons_page.class);
        startActivity(intent1);
        //finish();
    }

}
