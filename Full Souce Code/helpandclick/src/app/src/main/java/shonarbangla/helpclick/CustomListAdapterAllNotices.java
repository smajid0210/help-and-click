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

public class CustomListAdapterAllNotices extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> itemname;
    private final ArrayList<String> itemname1;


    public CustomListAdapterAllNotices(Activity context, ArrayList<String> itemname, ArrayList<String> itemname1) {
        super(context, R.layout.mylistforcommentpagemynotices, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        System.out.println("123");



        this.itemname1 = itemname1;


    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylistforcommentpagemynotices, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item_notices);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1111_mynotices);
        txtTitle.setText(itemname.get(position));
        System.out.println(itemname.get(position));
        extratxt.setText("Posted by: "+itemname1.get(position));

        return rowView;

    }
}

