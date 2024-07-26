package shonarbangla.helpclick;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import static shonarbangla.helpclick.All_Incidents.comments;
import static shonarbangla.helpclick.All_Incidents.times;

public class comment_incident extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_incident);
        ListView list;
        CustomListAdapterIncidents adapter = new CustomListAdapterIncidents(this, comments, times);
        list = (ListView) findViewById(R.id.list_comment_page_for_incident);
        list.setAdapter(adapter);




            }

}
