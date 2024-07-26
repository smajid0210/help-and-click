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
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static shonarbangla.helpclick.all_notices.age;
import static shonarbangla.helpclick.all_notices.app;
import static shonarbangla.helpclick.all_notices.comments;
import static shonarbangla.helpclick.all_notices.con;
import static shonarbangla.helpclick.all_notices.date;
import static shonarbangla.helpclick.all_notices.descript;
import static shonarbangla.helpclick.all_notices.gender;
import static shonarbangla.helpclick.all_notices.locdet;
import static shonarbangla.helpclick.all_notices.myBitmap;
import static shonarbangla.helpclick.all_notices.name;
import static shonarbangla.helpclick.all_notices.occ;
import static shonarbangla.helpclick.all_notices.selectnotice;
import static shonarbangla.helpclick.all_notices.times;

public class Comment_Page_for_notice extends AppCompatActivity implements View.OnClickListener {
    String showUrl="http://198.211.96.87/helpandclick/v1/index.php/commentsnots";
    EditText comment;
    String comms;
    RequestQueue requestQueue;
    SessionManager session;
    public Button br;
    FloatingActionButton floats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list;
        setContentView(R.layout.activity_comment__page_for_notice);
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
        //System.out.println("G"+comments.size()+ times.size());

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

    }*/ floats=(FloatingActionButton)findViewById(R.id.fab1);
        TextView extratxt = (TextView) findViewById(R.id.comment_page_name_notice);
        TextView extratxt1 = (TextView) findViewById(R.id.comment_page_age_notice);
        TextView extratxt2 = (TextView) findViewById(R.id.comment_page_gender_notice);

        TextView extratxt3 = (TextView) findViewById(R.id.comment_page_date_notice);
        TextView extratxt4 = (TextView) findViewById(R.id.comment_page_location_notice);
        TextView extratxt5 = (TextView) findViewById(R.id.comment_page_occupation_notice);
        TextView extratxt6 = (TextView) findViewById(R.id.comment_page_appearance_notice);

        TextView extratxt7 = (TextView) findViewById(R.id.comment_page_contact_notice);
        TextView extratxt8 = (TextView) findViewById(R.id.comment_page_description_notice);


        ImageView imageView = (ImageView) findViewById(R.id.comment_page_imageView1_notice);
        extratxt.setText(name);
        extratxt1.setText( age);
        extratxt2.setText(  gender);
        extratxt3.setText( date);
        extratxt4.setText( locdet);
        extratxt5.setText( occ);
        extratxt6.setText( app);
        extratxt7.setText(  con);
        extratxt8.setText( descript);
        Picasso.with(this)
                .load(all_notices.image)
                .fit().centerInside()
                .into(imageView);
        comment=(EditText)findViewById(R.id.comment_page_not_comment);
        br=(Button)findViewById(R.id.comment_page_not_button);
        br.setOnClickListener(this);
        imageView.setOnClickListener(this);
        floats.setOnClickListener(this);




    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.comment_page_not_button) {


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


                        parameters.put("post_id", selectnotice);
                        parameters.put("comment", comms);
                        comments.add(comms);
                        times.add(session.getUser());


                        return parameters;
                    }
                };
                //System.out.println("HI");
                requestQueue.add(request);


                Intent toy = new Intent(Comment_Page_for_notice.this,comment_notice.class);
                startActivity(toy);

            }
        }
        else if(view.getId()==R.id.comment_page_imageView1_notice)
        {
            Intent toy = new Intent(Comment_Page_for_notice.this,big_image_notices.class);
            startActivity(toy);
        }
        else if(view.getId()==R.id.fab1)
        {
            Intent toy = new Intent(Comment_Page_for_notice.this,comment_notice.class);
            startActivity(toy);
        }

    }
    public void onBackPressed()
    {   comments= new ArrayList<>();
        times= new ArrayList<>();
        Intent toy = new Intent(Comment_Page_for_notice.this,all_notices.class);
        startActivity(toy);
        //finish();
    }

}
