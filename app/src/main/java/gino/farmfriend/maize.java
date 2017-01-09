package gino.farmfriend;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
/**
 * Created by Gino on 19-02-2016.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class maize extends AppCompatActivity {
    TextView uid1;
    TextView name11;
    TextView email11;
    TextView pre1;


    //JSON Node Names
    private static final String TAG_USER = "weather";
    private static final String TAG_USER1 = "main";
    private static final String TAG_ID = "temp_min";

    private static final String TAG_EMAIL = "description";
    private static final String TAG_ID1 = "temp_max";
    private static final String TAG_NAME1 = "humidity";
    int flag = 0;

    private static final String TAG_USER11 = "main";

    JSONArray user = null;
     JSONObject d = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maize);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("track", "0");
        ed.apply();
        if (isonline()==true)
        new JSONParse().execute();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    private class JSONParse extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            uid1 = (TextView) findViewById(R.id.textView);
            name11 = (TextView) findViewById(R.id.textView2);
            email11 = (TextView) findViewById(R.id.textView3);
            pre1 = (TextView) findViewById(R.id.textView4);

            pDialog = new ProgressDialog(maize.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            ServiceHandler jParser = new ServiceHandler();

            // Getting JSON from URL
            String url2 = "http://usehumzz.site88.net/corn.json";
            JSONObject json = jParser.getJSONFromUrl(url2);
            System.out.println(json);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {System.out.println("secomd"+json);
                // Getting JSON Array
                user = json.getJSONArray(TAG_USER);
                System.out.println("user  "+user);
                JSONObject c = user.getJSONObject(0);

                d = json.getJSONObject(TAG_USER1);
                System.out.println("d  "+d);
                // Storing  JSON item in a Variable

                //String name = c.getString(TAG_NAME);
                String email1 = c.getString(TAG_EMAIL);

                String a1 = d.getString(TAG_ID1);
                String b1 = d.getString(TAG_NAME1);
                String id1 = d.getString(TAG_ID);
                System.out.println("stuff  "+email1+" "+a1+ " "+ b1 +" "+ id1+" ");
                uid1.setText(id1);
                // name1.setText(name);
                email11.setText(email1);
                pre1.setText(a1);
                name11.setText(b1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
     public boolean isonline()
    {


        ConnectivityManager cm=(ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if (info!= null) {
            return true;
        }
        else {
            return false;
        }
    }
}
