package shonarbangla.helpclick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class settings_page extends AppCompatActivity implements View.OnClickListener {
    SessionManager session;
    EditText tvt;
    TextView viewer;
    String editor;
    Button butn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        session = new SessionManager(getApplicationContext());
        viewer = (TextView) findViewById(R.id.setnum);
        butn = (Button) findViewById(R.id.updateshona);
        butn.setOnClickListener(this);
        System.out.println(session.getphone());
        tvt = (EditText) findViewById(R.id.editnum);

        if (session.getphone().length() == 1 && session.getphone().charAt(0) == '0') {
            viewer.setText("None");
        } else {
            viewer.setText(session.getphone());
        }



    }


    @Override
    public void onClick(View view){
        if(view.getId()==R.id.updateshona)
        {
            editor= tvt.getText().toString();
            System.out.println(editor);
            if(editor.length()!=0)
            {
                       session.createphone(editor);
                Intent toy = new Intent(settings_page.this,settings_page.class);
                startActivity(toy);
            }
        }
    }
    public void onBackPressed()
    {
        Intent toy = new Intent(settings_page.this,help_click_button_page.class);
        startActivity(toy);
        //finish();
    }


}
