package shonarbangla.helpclick;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import static shonarbangla.helpclick.searchList1.comments;
import static shonarbangla.helpclick.searchList1.times;

public class comment_noticemine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list;
        setContentView(R.layout.activity_comment_noticemine);
        CustomListAdapterViewMyNotices adapter = new CustomListAdapterViewMyNotices(this, comments, times);
        list = (ListView) findViewById(R.id.list_mynotice);
        list.setAdapter(adapter);
    }

}
