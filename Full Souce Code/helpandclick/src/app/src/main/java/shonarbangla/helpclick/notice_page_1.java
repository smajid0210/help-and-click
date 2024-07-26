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

public class notice_page_1 extends AppCompatActivity {


    public Button button, button1, button2;
    private static final int x = 1, y = 2;
    Button btpic, btnup, btsub;
    private Uri fileUri;
    String picturePath;
    Uri selectedImage;
    Bitmap photo;
    int ro=0,rob=0;
    public static String ba1, upldimg1;
    public static ImageView image3, image2;
    Spinner ag, gendr, loction;
    EditText des;
    String ag1, gendr1, loction1, desc;
    public static String URL = "Paste your URL here";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    //Declaring an Spinner
    private Spinner spinner;

    //An ArrayList for Spinner Items
    private ArrayList<String> students;

    //JSON Array
    private JSONArray result;
    SessionManager session;
    //TextViews to display details
    private TextView textViewName;
    private TextView textViewCourse;
    private TextView textViewSession;
    String insertURL = "http://198.211.96.87/helpandclick/v1/index.php/tasks";
    RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_page_1);
        image3 = (ImageView) findViewById(R.id.imageView11);
        init();


    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void init() {
        button = (Button) findViewById(R.id.erpor);
        button2 = (Button) findViewById(R.id.cpic1);
        button1 = (Button) findViewById(R.id.up1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(notice_page_1.this, notice_page_2.class);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == x && resultCode == RESULT_OK && data != null) {
            System.out.println("hagu5");
            ro=1;
            Uri pickedImage = data.getData();
            String[] filePath = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(pickedImage, filePath, null, null, null);
            cursor.moveToFirst();
            String imagePath = cursor.getString(cursor.getColumnIndex(filePath[0]));

            /*BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap1 = BitmapFactory.decodeFile(imagePath, options);
            Bitmap image4 = bitmap1;*/
            Bitmap bitmap1=null;
            try {
                bitmap1 = getBitmapFromUri(pickedImage);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            upldimg1= getStringImage(bitmap1);
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

            System.out.println(upldimg1.length());
            if(upldimg1.length()>400000)
            {

                upldimg1= getStringImage(bitmap);
            }


            System.out.println(upldimg1.length());

            //Bitmap bitmap2= Bitmap.createScaledBitmap(image4,100,100,true);

            image3.setImageBitmap(bitmap);



           // Bitmap bitmap = Bitmap.createScaledBitmap(bitmap1, wid, hi, true);
            //upldimg1 = getStringImage(bitmap);

            //Bitmap bitmap2= Bitmap.createScaledBitmap(image4,100,100,true);


            //image2.setImageBitmap(bitmap2);
        }

        if (requestCode == y && resultCode==RESULT_OK && data!=null) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            Bitmap image4 = image;
            ro=1;

            //Bitmap bitmap = Bitmap.createScaledBitmap(image, 500, 500, true);
            upldimg1 = getStringImage(image);
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

            System.out.println(upldimg1.length());
            if(upldimg1.length()>400000)
            {

                upldimg1= getStringImage(bitmap);
            }


            System.out.println(upldimg1.length());

            //System.out.println(upldimg);
            //Bitmap bitmap2= Bitmap.createScaledBitmap(image4,100,100,true);
            image3.setImageBitmap(bitmap);
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
        Intent toy = new Intent(notice_page_1.this,incident_notice_button_page.class);
        startActivity(toy);
        //finish();
    }
}
