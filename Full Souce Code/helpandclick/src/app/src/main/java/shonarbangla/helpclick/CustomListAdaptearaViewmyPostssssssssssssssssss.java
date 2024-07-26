package shonarbangla.helpclick;

/**
 * Created by Shadman on 1/2/2017.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdaptearaViewmyPostssssssssssssssssss extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> itemname1;
    private final ArrayList<String> itemname2;
    private final ArrayList<String> itemname3;

    private final ArrayList<Bitmap> imgid;

    public CustomListAdaptearaViewmyPostssssssssssssssssss(Activity context, ArrayList<String> itemname, ArrayList<Bitmap> imgid,ArrayList<String> itemname1,ArrayList<String> itemname2,ArrayList<String> itemname3) {
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

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1111);
        TextView extratxt1 = (TextView) rowView.findViewById(R.id.textView2111);
        TextView extratxt2 = (TextView) rowView.findViewById(R.id.textView3111);
        txtTitle.setText("Article No- "+ itemname3.get(position));
        imageView.setImageBitmap(imgid.get(position));
        extratxt.setText("Age: " + itemname.get(position));
        extratxt1.setText("Gender: " + itemname1.get(position));
        extratxt2.setText("Location: " + itemname2.get(position));
        return rowView;

    }
}
