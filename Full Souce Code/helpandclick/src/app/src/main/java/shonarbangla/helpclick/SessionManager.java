package shonarbangla.helpclick;

/**
 * Created by Shadman on 1/1/2017.
 */
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;


    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "AndroidHivePref";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_NAME = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_EMAIL = "email";

    public static final String KEY_LOG1= "log1";
    public static final String KEY_LOG2= "log2";
    public static final String KEY_LOG3= "log3";
    public static final String KEY_PIC = "poc";
    public static final String KEY_NUMBER = "pot";

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void createphone(String man)

    {
        editor.putString(KEY_NUMBER, man);
        editor.commit();
    }
    /**
     * Create login session
     * */
    public void createLoginSession(String name, String email){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_NAME, name);

        // Storing email in pref
        editor.putString(KEY_EMAIL, email);

        // commit changes
        editor.commit();
    }
    public void createpic(int man)
    {
        editor.putInt(KEY_PIC,man);
        editor.commit();

    }
    public void createTutorial1(int man)
    {
        editor.putInt(KEY_LOG1,man);
        editor.commit();

    }
    public void createTutorial2(int man)
    {
        editor.putInt(KEY_LOG2,man);
        editor.commit();

    }
    public void createTutorial3(int man)
    {
        editor.putInt(KEY_LOG3,man);
        editor.commit();

    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            //Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            //_context.startActivity(i);
        }

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));

        // user email id
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        //Intent i = new Intent(_context, LoginActivity.class);
        // Closing all the Activities
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        //_context.startActivity(i);
    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }
    public String getUser()
    {
        return pref.getString(KEY_NAME,null);
    }
    public int getTutorial1(){return pref.getInt(KEY_LOG1,0);}
    public int getTutorial2(){return pref.getInt(KEY_LOG2,0);}
    public int getTutorial3(){return pref.getInt(KEY_LOG3,0);}
    public int getpic(){return pref.getInt(KEY_PIC,0);}
    public String getphone()
    {
        return pref.getString(KEY_NUMBER,"0");
    }

}