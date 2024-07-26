package shonarbangla.helpclick;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import static shonarbangla.helpclick.all_notices.comments;
import static shonarbangla.helpclick.all_notices.times;

public class comment_notice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list;
        setContentView(R.layout.activity_comment_notice);
        CustomListAdapterAllNotices adapter = new CustomListAdapterAllNotices(this, comments, times);
        list = (ListView) findViewById(R.id.list_comment_page_for_notice);
        list.setAdapter(adapter);
    }

}
