package shonarbangla.helpclick;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;

import org.json.JSONArray;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;

public class incident_page_1 extends AppCompatActivity {


    public Button button,button1,button2;
    private static final int x=1,y=2;
    Button btpic, btnup,btsub;
    private Uri fileUri;
    String picturePath;
    Uri selectedImage;
    Bitmap photo,image,image4;

    public static String ba1,upldimg;
    public static ImageView image1,image2;
    Spinner ag,gendr,loction;
    EditText des;
    String ag1,gendr1,loction1,desc;
    public static String URL = "Paste your URL here";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    //Declaring an Spinner
    private Spinner spinner;
    int ro=0;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_page_1);
        image1 = (ImageView) findViewById(R.id.imageView1);
        init();

    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void init(){
        button = (Button)findViewById(R.id.tarpor);
        button2 = (Button)findViewById(R.id.cpic);
        button1 = (Button)findViewById(R.id.up);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toy = new Intent(incident_page_1.this,incident_page_2.class);
                if(ro==0)
                {
                    Toast.makeText(getApplicationContext(), "Please upload an image and try again",
                            Toast.LENGTH_LONG)
                            .show();
                }
                else
                {
                    startActivity(toy);

                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(galleryIntent, x);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");


                startActivityForResult(intent, y);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==x && resultCode==RESULT_OK && data!=null)
        {   System.out.println("hagu5");
            Uri pickedImage= data.getData();
            ro=1;
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            /*BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap1 = (Bitmap)BitmapFactory.decodeFile(imagePath, options);*/
            Bitmap bitmap1=null;
            try {
                bitmap1 = getBitmapFromUri(pickedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            upldimg= getStringImage(bitmap1);
            int wid= bitmap1.getWidth();
            int hi=bitmap1.getHeight();

            while(wid<=500 && hi<=500)
            {
                wid=wid*2;
                hi*=2;
            }
            while(wid>500 && hi>500)
            {
                wid=wid/2;
                hi=hi/2;
            }
            Bitmap bitmap= Bitmap.createScaledBitmap(bitmap1,wid,hi,true);

            System.out.println(upldimg.length());
            if(upldimg.length()>400000)
            {

                upldimg= getStringImage(bitmap);
            }


            System.out.println(upldimg.length());

            //Bitmap bitmap2= Bitmap.createScaledBitmap(image4,100,100,true);

            image1.setImageBitmap(bitmap);
            //image2.setImageBitmap(bitmap2);
        }

        if (requestCode == y && resultCode==RESULT_OK && data!=null) {

            ro=1;
            image = (Bitmap) data.getExtras().get("data");
            image4 =image;
            upldimg= getStringImage(image);

            int wid= image.getWidth();
            int hi=image.getHeight();

            while(wid<=500 && hi<=500)
            {
                wid=wid*2;
                hi*=2;
            }
            while(wid>500 && hi>500)
            {
                wid=wid/2;
                hi=hi/2;
            }






            Bitmap bitmap= Bitmap.createScaledBitmap(image,wid,hi,true);
            System.out.println(upldimg.length());
            if(upldimg.length()>400000)
            {

                upldimg= getStringImage(bitmap);
            }
            //upldimg= getStringImage(bitmap);

            System.out.println(upldimg.length());
            //System.out.println(upldimg);
            //Bitmap bitmap2= Bitmap.createScaledBitmap(image4,100,100,true);
            image1.setImageBitmap(bitmap);
            //image2.setImageBitmap(bitmap2);

        }

    }


    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor =
                getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }
    public void onBackPressed()
    {
        Intent toy = new Intent(incident_page_1.this,incident_notice_button_page.class);
        startActivity(toy);
        //finish();
    }





}
