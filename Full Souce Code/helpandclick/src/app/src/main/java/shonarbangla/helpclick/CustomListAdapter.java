package shonarbangla.helpclick;

/**
 * Created by Shadman on 1/2/2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> itemname1;
    private final ArrayList<String> itemname2;
    private final ArrayList<String> itemname3;
    private Bitmap myBitmap;

    private final ArrayList<String> imgid;

    public CustomListAdapter(Activity context, ArrayList<String> itemname, ArrayList<String> imgid,ArrayList<String> itemname1,ArrayList<String> itemname2,ArrayList<String> itemname3) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;


        this.imgid = imgid;
        this.itemname1 = itemname1;
        this.itemname2 = itemname2;
        this.itemname3 = itemname3;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);

        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1111);
        TextView extratxt1 = (TextView) rowView.findViewById(R.id.textView2111);
        TextView extratxt2 = (TextView) rowView.findViewById(R.id.textView3111);
        txtTitle.setText("Incident No- "+ itemname3.get(position));
        Picasso.with(getContext())
                .load(imgid.get(position))
                .fit().centerCrop()
                .into(imageView);

       /* ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        try {
            URL url = new URL(imgid.get(position));
            myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);

        imageView.setImageBitmap(bitmap);
         */
        extratxt.setText("Age: " + itemname.get(position));
        extratxt1.setText("Gender: " + itemname1.get(position));
        extratxt2.setText("Location: " + itemname2.get(position));
        return rowView;

    }
    public View getView1(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        try {
            URL url = new URL(imgid.get(position));
            myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }
        Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap, 500, 500, true);

        imageView.setImageBitmap(bitmap);
        return rowView;
    }

}
