package shonarbangla.helpclick;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.Target;
import com.github.amlcurran.showcaseview.targets.ViewTarget;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;

public class search_buttons_page extends AppCompatActivity implements View.OnClickListener {





    private ShowcaseView showcaseView;
    private int contador=0,contador1=20;
    private Target t1,t2,t3,t4;

    private static final int x=1,y=2;
    public static ArrayList<String>imgcounter;
    Button bt1, bt2,bt3,bt4,bt5;
    private Uri fileUri;
    String showUrl="http://198.211.96.87/helpandclick/v1/index.php/tasks/";
    String showUrl1="http://198.211.96.87/helpandclick/v1/index.php/notes/";
    String showUrl2="http://198.211.96.87/helpandclick/v1/index.php/tasksall/";
    String showUrl3="http://198.211.96.87/helpandclick/v1/index.php/notesall/";
    String picturePath;
    Uri selectedImage;
    int c=0,c1=0,d=0,d1=0,e=0,f=0,ups1=0,ups2=0,ups3=0,ups4=0;
    Bitmap photo;
    ImageView cimg;
    public static ArrayList<Bitmap>imgs;
    public static ArrayList<String>ages;
    public static ArrayList<String>genders;
    public static ArrayList<String>locations;
    public static ArrayList<String>ids;

    public Integer[] imgid;
    Blob blob;
    Bitmap myBitmap;
    String ba1,result,age,gender,location,id,name,det;
    RequestQueue requestQueue;
    SessionManager session;
    ;
    ImageView image1;
    private long mRequestStartTime;
    public static String URL = "Paste your URL here";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_buttons_page);
        ages= new ArrayList<String>();
        genders= new ArrayList<String>();
        locations= new ArrayList<String>();
        ids= new ArrayList<String>();
        imgs= new ArrayList<Bitmap>();
        imgcounter= new ArrayList<String>();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        t1= new ViewTarget(R.id.all_incidents, this);
        t2= new ViewTarget(R.id.all_notices, this);
        t3= new ViewTarget(R.id.view_my_post, this);
        t4= new ViewTarget(R.id.view_my_notices, this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        System.out.println(session.getTutorial3());
        if(session.getTutorial3()==0) {
            showcaseView =new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setOnClickListener(this)


                    .setStyle(R.style.Transperencia)

                    .build();
            showcaseView.setButtonText("Next");
            //session.createTutorial3(1);
        }

      /*  showcaseView =new ShowcaseView.Builder(this)
                .setTarget(Target.NONE)
                .setOnClickListener(this)


                .setStyle(R.style.Transperencia)

                .build();
        showcaseView.setButtonText("Next");*/
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        bt1 = (Button) findViewById(R.id.all_incidents);
        bt2 = (Button) findViewById(R.id.all_notices);
        bt3 = (Button) findViewById(R.id.view_my_post);
        bt4 = (Button) findViewById(R.id.view_my_notices);
        bt5 = (Button) findViewById(R.id.tutor1);


        bt1.setOnClickListener(this);

        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        System.out.println("hagu3");
        showUrl+=(session.getUser());
        showUrl1+=(session.getUser());
        showUrl2+=(session.getUser());
        showUrl3+=(session.getUser());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
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
    //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    @Override
    public void onClick(View view){
        if(session.getTutorial3()==0) {

            switch (contador) {

                case 0:
                    showcaseView.setShowcase(t1, true);
                    showcaseView.setContentTitle("Do you want to see all the uploaded posts about unidentified persons? Click Here. ");

                    break;
                case 1:
                    showcaseView.setShowcase(t2, true);
                    showcaseView.setContentTitle("Do you want to see all the missing person notices? Click Here.");


                    break;

                case 2:
                    showcaseView.setShowcase(t3, true);
                    showcaseView.setContentTitle("Do you want to see all of your posts about any unidentified person? Click Here.");


                    break;
                case 3:
                    showcaseView.setShowcase(t4, true);
                    showcaseView.setContentTitle("Do you want to see all of your missing person notices? Click Here.");

                    showcaseView.setButtonText("GOT IT!");

                    break;

                case 4:
                    showcaseView.hide();
                    session.createTutorial3(1);

                    break;

            }
        }
        contador++;
        /*switch (contador) {

            case 0:
                showcaseView.setShowcase(t1, true);
                showcaseView.setContentTitle("Search for all posted incidents.");

                break;
            case 1:
                showcaseView.setShowcase(t2, true);
                showcaseView.setContentTitle("Search for all posted notices.");


                break;

            case 2:
                showcaseView.setShowcase(t3, true);
                showcaseView.setContentTitle("Search my posted incidents");


                break;
            case 3:
                showcaseView.setShowcase(t4, true);
                showcaseView.setContentTitle("Search my posted notices");

                showcaseView.setButtonText("GOT IT!");
                break;

            case 4:
                showcaseView.hide();
                break;

        }
        contador++;
         */


            switch (contador1) {

                case 0:
                    showcaseView.setShowcase(t1, true);
                    //contador1++;
                    showcaseView.setContentTitle("Do you want to see all the uploaded posts about unidentified persons? Click Here.");

                    break;
                case 1:
                    showcaseView.setShowcase(t2, true);
                    //contador1++;
                    showcaseView.setContentTitle("Do you want to see all the missing person notices? Click Here.");


                    break;

                case 2:
                    showcaseView.setShowcase(t3, true);
                    //contador1++;
                    showcaseView.setContentTitle("Do you want to see all of your posts about any unidentified person? Click Here.");

                    break;
                case 3:
                    showcaseView.setShowcase(t4, true);
                   // contador1++;
                    showcaseView.setContentTitle("Do you want to see all of your missing person notices? Click Here.");

                    showcaseView.setButtonText("GOT IT!");

                    break;

                case 4:
                    showcaseView.hide();
                   // contador1=0;
                    session.createTutorial3(1);


                    break;

            }
        contador1++;

        if(view.getId()==R.id.tutor1) {
            showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(Target.NONE)
                    .setOnClickListener(this)


                    .setStyle(R.style.Transperencia)

                    .build();
            contador1=0;
            showcaseView.setButtonText("Next");
        }






        if(view.getId()==R.id.all_incidents && ups1==0 && ups2==0 & ups3==0 && ups4==0) {
            ups1++;
            setContentView(R.layout.activity_loadingimg);
            mRequestStartTime = System.currentTimeMillis();
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    showUrl2, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    long totalRequestTime = System.currentTimeMillis() - mRequestStartTime;
                    System.out.println(totalRequestTime);
                    System.out.println("GG"+response.toString());
                    try { c1++;

                        System.out.println(c1);
                        JSONArray incidents = response.getJSONArray("incidents");
                        for (int i = 0; i < incidents.length(); i++) {
                            e=0;
                            JSONObject incident = incidents.getJSONObject(i);
                            c1++;
                            id= incident.getString("id");
                            age = incident.getString("age");

                            gender = incident.getString("gender");
                            location = incident.getString("location");
                            //det= incident.getString("det");

                            String image=new String();
                            image = incident.getString("bigimage");

                            if(image.length()!=0) {
                                imgcounter.add(image);
                                /*imageLoader.get(url, ImageLoader.getImageListener(imageView,
                                        R.drawable.image, android.R.drawable
                                                .ic_dialog_alert));*/
                                /*try {
                                    URL url = new URL(image);
                                    myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                } catch(IOException e) {
                                    System.out.println(e);
                                }

                                //myBitmap= ImageLoader.ImageCache.(image);

                                //myBitmap =

                                int wid= myBitmap.getWidth();
                                int hi=myBitmap.getHeight();

                                while(wid<=500 && hi<=500)
                                {
                                    wid=wid*2;
                                    hi*=2;
                                }



                                Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);

                                imgs.add(bitmap);*/
                                ages.add(age);
                                genders.add(gender);
                                locations.add(location);
                                ids.add(id);


                            }




                        }
                        if(c1>0) {
                            System.out.println(ages.size());
                            Intent intent1 = new Intent(search_buttons_page.this, All_Incidents.class);
                            startActivity(intent1);
                            //finish();
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
        else if(view.getId()==R.id.view_my_post && ups2==0 && ups1==0 & ups3==0 && ups4==0) {
                ups2++;
            try {
                setContentView(R.layout.activity_loadingimg);
            }
            catch(Exception e) {
                System.out.println(e);
            }
                System.out.println(showUrl);
           // setContentView(R.layout.activity_loadingimg);
            System.out.println("abc");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    showUrl, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try { c++;


                        System.out.println(c);
                        JSONArray incidents = response.getJSONArray("incidents");
                        for (int i = 0; i < incidents.length(); i++) {
                            JSONObject incident = incidents.getJSONObject(i);
                            c++;
                            id= incident.getString("id");
                            age = incident.getString("age");
                            gender = incident.getString("gender");
                            location = incident.getString("location");


                            String image = incident.getString("bigimage");

                            if(image.length()!=0) {
                                imgcounter.add(image);
                                /*try {
                                    URL url = new URL(image);

                                    myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                } catch(IOException e) {
                                    System.out.println(e);
                                }

                                //myBitmap= ImageLoader.ImageCache.(image);

                                //myBitmap =

                                int wid= myBitmap.getWidth();
                                int hi=myBitmap.getHeight();

                                while(wid<=500 || hi<=500)
                                {
                                    wid=wid*2;
                                    hi*=2;
                                }



                                Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);

                                imgs.add(bitmap);*/
                                ages.add(age);
                                genders.add(gender);
                                locations.add(location);
                                ids.add(id);


                            }




                        }
                        if(c>0) {
                            System.out.println(ages.size());
                            Intent intent1 = new Intent(search_buttons_page.this, searchList.class);
                            startActivity(intent1);
                            //finish();
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

            System.out.println(c);

               /* for (int i = 0; i < imgcounter.size(); i++) {
                    try {

                        java.net.URL url = new URL(imgcounter.get(i));
                        myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                        System.out.println("vodla");
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                    //myBitmap= ImageLoader.ImageCache.(image);

                    //myBitmap =

                    Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);
                    imgs.add(bitmap);

                }
          */




        }
        else if(view.getId()==R.id.view_my_notices && ups3==0 && ups2==0 & ups1==0 && ups4==0) {
               ups3++;
            setContentView(R.layout.activity_loadingimg);
            System.out.println(showUrl1);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                    showUrl1, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try {c++;
                        JSONArray incidents = response.getJSONArray("notices");
                        for (int i = 0; i < incidents.length(); i++) {
                            JSONObject incident = incidents.getJSONObject(i);
                            c++;
                            id= incident.getString("id");
                            age = incident.getString("age");
                            gender = incident.getString("gender");
                            name = incident.getString("name");


                            String image = incident.getString("bigimage");

                            if(image.length()!=0) {
                                imgcounter.add(image);
                                /*try {
                                    URL url = new URL(image);
                                    myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                                } catch(IOException e) {
                                    System.out.println(e);
                                }
                                int wid= myBitmap.getWidth();
                                int hi=myBitmap.getHeight();

                                while(wid<=500 && hi<=500)
                                {
                                    wid=wid*2;
                                    hi*=2;
                                }



                                Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);

                                imgs.add(bitmap);*/
                                ages.add(age);
                                genders.add(gender);
                                locations.add(name);
                                ids.add(id);


                            }




                        }
                        if(c>0) {
                            System.out.println(ages.size());
                            Intent intent1 = new Intent(search_buttons_page.this, searchList1.class);
                            startActivity(intent1);
                            //finish();
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
        else if(view.getId()==R.id.all_notices && ups4==0 && ups2==0 & ups3==0 && ups1==0)
        {   ups4++;
            setContentView(R.layout.activity_loadingimg);
            System.out.println(showUrl3);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                showUrl3, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());


                try { d1++;
                    JSONArray incidents = response.getJSONArray("notices");
                    for (int i = 0; i < incidents.length(); i++) {
                        JSONObject incident = incidents.getJSONObject(i);
                        d1++;
                        id= incident.getString("id");
                        age = incident.getString("age");
                        gender = incident.getString("gender");
                        name = incident.getString("name");


                        String image = incident.getString("bigimage");

                        if(image.length()!=0) {
                            imgcounter.add(image);
                            /*try {
                                URL url = new URL(image);
                                myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                            } catch(IOException e) {
                                System.out.println(e);
                            }
                            int wid= myBitmap.getWidth();
                            int hi=myBitmap.getHeight();

                            while(wid<=500 && hi<=500)
                            {
                                wid=wid*2;
                                hi*=2;
                            }



                            Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);

                            imgs.add(bitmap);*/
                            ages.add(age);
                            genders.add(gender);
                            locations.add(name);
                            ids.add(id);


                        }




                    }
                    if(d1>0) {
                        System.out.println(ages.size());
                        Intent intent1 = new Intent(search_buttons_page.this, all_notices.class);
                        startActivity(intent1);
                        //finish();
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
        Intent toy = new Intent(search_buttons_page.this,help_click_button_page.class);
        startActivity(toy);
        //finish();
    }


}
