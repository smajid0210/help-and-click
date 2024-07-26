package shonarbangla.helpclick;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import static shonarbangla.helpclick.searchList.comments;
import static shonarbangla.helpclick.searchList.times;

public class comment_incidentmine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_incidentmine);
        ListView list;

        CustomListAdaptermyposts adapter = new CustomListAdaptermyposts(this, comments, times);
        list = (ListView) findViewById(R.id.list33);
        list.setAdapter(adapter);
    }

}
