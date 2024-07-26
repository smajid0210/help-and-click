package shonarbangla.helpclick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static shonarbangla.helpclick.searchList.comments;

import static shonarbangla.helpclick.searchList.image;
import static shonarbangla.helpclick.searchList.myBitmap;
import static shonarbangla.helpclick.searchList.selectincident;
import static shonarbangla.helpclick.searchList.times;
import static shonarbangla.helpclick.search_buttons_page.ages;
import static shonarbangla.helpclick.search_buttons_page.genders;
import static shonarbangla.helpclick.search_buttons_page.ids;
import static shonarbangla.helpclick.search_buttons_page.imgs;
import static shonarbangla.helpclick.search_buttons_page.locations;

public class Comment_page_for_view_my_posts extends AppCompatActivity implements View.OnClickListener {
    /*public static ArrayList<Bitmap> imgs;
    public static ArrayList<String>ages;
    public static ArrayList<String>genders;
    public static ArrayList<String>thanas;
    public static ArrayList<String>dates;
    public static ArrayList<String>locationdet;
    public static ArrayList<String>contacts;
    public static ArrayList<String>descriptions;*/
    String showUrl="http://198.211.96.87/helpandclick/v1/index.php/commentsinc";
    String showUrl2="http://198.211.96.87/helpandclick/v1/index.php/tasksdel/";
    EditText comment;
    String comms;
    RequestQueue requestQueue;
    SessionManager session;
    public Button br,brz;
    FloatingActionButton floats;



    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        ListView list;
        setContentView(R.layout.activity_comment_page_for_view_my_posts);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        session = new SessionManager(getApplicationContext());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);



        /*imgs= new ArrayList<Bitmap>();
        ages= new ArrayList<String>();
        genders= new ArrayList<String>();
        thanas= new ArrayList<String>();
        dates= new ArrayList<String>();
        locationdet=  new ArrayList<String>();
        contacts= new ArrayList<String>();
        descriptions= new ArrayList<String>();*/
        //if(comments.size()!=0 && times.size()!=0) {
            System.out.println("G"+comments.size()+ times.size());

        //}


    /*public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = this.getLayoutInflater();
        View rowView = inflater.inflate(R.layout., null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1111);
        TextView extratxt1 = (TextView) rowView.findViewById(R.id.textView2111);
        TextView extratxt2 = (TextView) rowView.findViewById(R.id.textView3111);
        txtTitle.setText("Article No. "+ itemname3.get(position));
        imageView.setImageBitmap(imgid.get(position));
        extratxt.setText("Age " + itemname.get(position));
        extratxt1.setText("Gender " + itemname1.get(position));
        extratxt2.setText("Location " + itemname2.get(position));
        return rowView;

    }*/ floats=(FloatingActionButton)findViewById(R.id.fab2);
        TextView extratxt = (TextView) findViewById(R.id.comment_page_age_viewMyPost);
        TextView extratxt1 = (TextView) findViewById(R.id.comment_page_gender_viewMyPost);
        TextView extratxt2 = (TextView) findViewById(R.id.comment_page_thana_viewMyPost);
        TextView extratxt3 = (TextView) findViewById(R.id.comment_page_date_viewMyPost);
        TextView extratxt4 = (TextView) findViewById(R.id.comment_page_location_viewMyPost);
        TextView extratxt5 = (TextView) findViewById(R.id.comment_page_contact_viewMyPost);
        TextView extratxt6 = (TextView) findViewById(R.id.comment_page_description_viewMyPost);
        ImageView imageView = (ImageView) findViewById(R.id.imageView1_viewMyPost);
        extratxt.setText( searchList.age);
        extratxt1.setText( searchList.gender);
        extratxt2.setText( searchList.thana);
        extratxt3.setText( searchList.date);
        extratxt4.setText( searchList.locdet);
        extratxt5.setText( searchList.contact);
        extratxt6.setText( searchList.descript);
        floats.setOnClickListener(this);

        System.out.println(image);
        Picasso.with(this)
                .load(image)
                .fit().centerInside()
                .into(imageView);
        comment=(EditText)findViewById(R.id.comment_mypost);
        br=(Button)findViewById(R.id.button_viewMyPost);
        brz=(Button)findViewById(R.id.delete_post);
        br.setOnClickListener(this);
        brz.setOnClickListener(this);
        imageView.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button_viewMyPost) {


            comms= comment.getText().toString();
            System.out.println("abc");
            if(comms.length()!=0) {
                StringRequest request = new StringRequest(Request.Method.POST, showUrl, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        System.out.println(response.toString());
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
                        parameters.put("username", session.getUser());


                        parameters.put("post_id", selectincident);
                        parameters.put("comment", comms);
                        comments.add(comms);
                        times.add(session.getUser());


                        return parameters;
                    }
                };
                //System.out.println("HI");
                requestQueue.add(request);


                Intent toy = new Intent(Comment_page_for_view_my_posts.this, comment_incidentmine.class);
                startActivity(toy);
            }

        }
        else if(view.getId()==R.id.imageView1_viewMyPost)
        {
            Intent toy = new Intent(Comment_page_for_view_my_posts.this,big_image_incidents_mine.class);
            startActivity(toy);
        }
        else if(view.getId()==R.id.delete_post)
        {    showUrl2+=selectincident;
            JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.DELETE,
                    showUrl2, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println(response.toString());
                    try { times= new ArrayList<String>();
                        comments= new ArrayList<String>();
                        String inc=response.getString("error");
                        System.out.println(inc);


                            if(inc.equals("false"))
                            {
                                System.out.println("sumon");
                                Intent toy = new Intent(Comment_page_for_view_my_posts.this,search_buttons_page.class);
                                startActivity(toy);

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
        }
        else if(view.getId()==R.id.fab2)
        {
            Intent toy = new Intent(Comment_page_for_view_my_posts.this,comment_incidentmine.class);
            startActivity(toy);
        }


    }
    public void onBackPressed()
    {   comments= new ArrayList<>();
        times= new ArrayList<>();

        Intent toy = new Intent(Comment_page_for_view_my_posts.this,searchList.class);
        startActivity(toy);
        //finish();
    }
}
