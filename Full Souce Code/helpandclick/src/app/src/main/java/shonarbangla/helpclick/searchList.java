package shonarbangla.helpclick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static shonarbangla.helpclick.search_buttons_page.ages;
import static shonarbangla.helpclick.search_buttons_page.genders;
import static shonarbangla.helpclick.search_buttons_page.ids;
import static shonarbangla.helpclick.search_buttons_page.imgs;
import static shonarbangla.helpclick.search_buttons_page.imgcounter;
import static shonarbangla.helpclick.search_buttons_page.locations;


public class searchList extends AppCompatActivity {
    ListView list;
    public static String selectincident;

    public int c=0,c1=0,po1=0,po2=0;
    Map<String,Integer> cons;
    public int d=0,e=0;
    String showUrl="http://198.211.96.87/helpandclick/v1/index.php/tasksfull/";
    String showUrl1="http:/198.211.96.87/helpandclick/v1/index.php/notes/";
    String showUrl2="http://198.211.96.87/helpandclick/v1/index.php/commentsinc/";
    public static Bitmap myBitmap;

    public static String image;
    public static String ba1,result,age,gender,date,thana,locdet,contact,descript,post_time,comment;
    public static ArrayList<String>comments;
    public static ArrayList<String>times;
    RequestQueue requestQueue;
    SessionManager session;
    ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cons= new Map<String, Integer>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(Object key) {
                return false;
            }

            @Override
            public boolean containsValue(Object value) {
                return false;
            }

            @Override
            public Integer get(Object key) {
                return null;
            }

            @Override
            public Integer put(String key, Integer value) {
                return null;
            }

            @Override
            public Integer remove(Object key) {
                return null;
            }

            @Override
            public void putAll(Map<? extends String, ? extends Integer> m) {

            }

            @Override
            public void clear() {

            }

            @NonNull
            @Override
            public Set<String> keySet() {
                return null;
            }

            @NonNull
            @Override
            public Collection<Integer> values() {
                return null;
            }

            @NonNull
            @Override
            public Set<Entry<String, Integer>> entrySet() {
                return null;
            }
        };
        times= new ArrayList<String>();
        comments= new ArrayList<String>();
        setContentView(R.layout.activity_search_list);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        session = new SessionManager(getApplicationContext());


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
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
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



                                    //myBitmap= ImageLoader.ImageCache.(image);

                                    //myBitmap =


                                    System.out.println(image.length());
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



                            }
                            /*if (d > 0) {
                                System.out.println(ages.size());
                                Intent intent1 = new Intent(searchList.this, Comment_page_for_view_my_posts.class);
                                startActivity(intent1);
                            }*/
                               if(age!=null && d>0)
                               {
                                   Intent intent1 = new Intent(searchList.this, Comment_page_for_view_my_posts.class);
                                   startActivity(intent1);
                               }

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
                    showUrl2 += selectincident;
                    c1++;
                }

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

            }
    public Bitmap ConvertToImage(String image) {
        try {
            InputStream stream = new ByteArrayInputStream(Base64.decode(image.getBytes(), Base64.DEFAULT));
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            Log.v("Ben", "Image Converted");
            return bitmap;
        } catch (Exception e) {

            return null;
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
        Intent intent1 = new Intent(searchList.this, search_buttons_page.class);
        startActivity(intent1);
        //finish();
    }


}
