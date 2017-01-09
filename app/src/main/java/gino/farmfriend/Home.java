package gino.farmfriend;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
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
import android.net.NetworkInfo;
import android.content.Context;
import android.net.ConnectivityManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Home extends AppCompatActivity {
    TextView uid;
    TextView name1;
    TextView email1;
    TextView pre;
    TextView hum;
    Button Btngetdata;
    TextView desc2;
    TextView tempmin;
    TextView tempmax;
    TextView desc3;

    //URL to get JSON Array
    private static String url = "http://api.openweathermap.org/data/2.5/weather?q=Kochi,India&appid=44db6a862fba0b067b1930da0d769e98";
    private static String url2 = "http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&appid=44db6a862fba0b067b1930da0d769e98";

    //JSON Node Names
    private static final String TAG_USER = "weather";
    private static final String TAG_USER1 = "main";
    private static final String TAG_ID = "temp_min";
    private static final String TAG_NAME = "main";
    private static final String TAG_EMAIL = "description";
    private static final String TAG_ID1 = "temp_max";
    private static final String TAG_NAME1 = "humidity";
    int flag = 0;

    private static final String TAG_USER11 = "main";
    private static final String TAG_ID111 = "temp_min";

    private static final String TAG_EMAIL11 = "description";
    private static final String TAG_ID11 = "temp_max";
    private static final String TAG_NAME11 = "humidity";
    JSONArray user = null;
    JSONObject d = null;
    JSONArray user1 = null;
    JSONObject d1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString("track", "0");
        ed.apply();
        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);


            if (isonline()==true)
            new JSONParse().execute();



      //  Btngetdata = (Button) findViewById(R.id.getdata);
//Btngetdata.performClick();

       // Btngetdata.setOnClickListener(new View.OnClickListener() {

           // @Override
          //  public void onClick(View view) {


               // new JSONParse().execute();
             //   new JSONParse2().execute();

       //     }
       // });

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
            uid = (TextView) findViewById(R.id.uid);
            name1 = (TextView) findViewById(R.id.name);
            email1 = (TextView) findViewById(R.id.email);
            pre = (TextView) findViewById(R.id.pre);
            hum = (TextView) findViewById(R.id.hum);
            desc2 = (TextView) findViewById(R.id.desc);
            desc3=(TextView) findViewById(R.id.humid);
            tempmin= (TextView) findViewById(R.id.tempmin);
            tempmax=(TextView) findViewById(R.id.tempm);
            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected JSONObject doInBackground(String... args) {
            ServiceHandler jParser = new ServiceHandler();

            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            System.out.println(json);
            return json;
        }

        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                user = json.getJSONArray(TAG_USER);
                JSONObject c = user.getJSONObject(0);
                d = json.getJSONObject(TAG_USER1);
                // Storing  JSON item in a Variable

                //String name = c.getString(TAG_NAME);
                String email = c.getString(TAG_EMAIL);
                String p = c.getString(TAG_USER11);
                String a = d.getString(TAG_ID1);
                String b = d.getString(TAG_NAME1);
                String id = d.getString(TAG_ID);
                String g="27.0C";
                String h="30.0C";
                //Set JSON Data in TextView
                double mintemp = Double.parseDouble(id);
                mintemp = mintemp - 273;
                mintemp = Math.ceil(mintemp);
                id = mintemp + "C"+" ";
                double maxtemp = Double.parseDouble(a);
                maxtemp = Math.ceil(maxtemp);

                maxtemp = maxtemp - 273;
                a = maxtemp + "C"+" ";
                uid.setText(id);
                // name1.setText(name);
                email1.setText(email);
                pre.setText(a);
                hum.setText(b);
                desc2.setText(p);
                desc3.setText(b);
                tempmax.setText(g);
                tempmin.setText(h);
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

  /*  private class JSONParse2 extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            desc2 = (TextView)findViewById(R.id.desc);
            tempmin = (TextView)findViewById(R.id.tempmin);
            tempmax = (TextView)findViewById(R.id.tempm);
            humid = (TextView)findViewById(R.id.humid);

            pDialog = new ProgressDialog(Home.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected JSONObject doInBackground(String... args) {
            ServiceHandler jParser = new ServiceHandler();

            // Getting JSON from URL
            JSONObject json2 = jParser.getJSONFromUrl(url2);
            return json2;
        }
        @Override
        protected void onPostExecute(JSONObject json2) {
            pDialog.dismiss();
            try {
                // Getting JSON Array
                //user1 = json2.getJSONArray(TAG_USER111);
                JSONObject c1 = user1.getJSONObject(0);
                d1=json2.getJSONObject(TAG_USER11);
                // Storing  JSON item in a Variable

                //String name = c.getString(TAG_NAME);
                String email = c1.getString(TAG_EMAIL11);
                String a=d1.getString(TAG_ID111);
                String b= d1.getString(TAG_NAME11);
                String id = d1.getString(TAG_ID11);
                //Set JSON Data in TextView
                double mintemp=Double.parseDouble(id);
                mintemp=mintemp-273;
                mintemp=Math.ceil(mintemp);
                id=mintemp+"";
                double maxtemp=Double.parseDouble(a);
                maxtemp=Math.ceil(maxtemp);

                maxtemp=maxtemp-273;
                a=maxtemp+"";
                Log.v("Checking..............:",a);
                tempmin.setText(a);
                // name1.setText(name);
                desc2.setText(email);
                tempmax.setText(id);
                humid.setText(b);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

*/
