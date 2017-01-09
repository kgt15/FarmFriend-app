package gino.farmfriend;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gino on 19-02-2016.
 */
public class forum extends ActionBarActivity {
    public static String[] dumList = {"dummy", "dummy", "dummy",""};
    public static String strTest;
    EditText editText;
    String str="";
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);

        editText=(EditText)findViewById(R.id.edittext);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Forum");

        bt=(Button)findViewById(R.id.bt);
        jsonDownload downloader = new jsonDownload();
        try {
            URL url = new URL("http://192.168.168.38:8080/forum");
            downloader.execute(url);

            jsonParse();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str=editText.getText().toString();
                dumList[3]=str;
            }
        });

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.activity_displist, dumList);
        ListView list = (ListView) findViewById(R.id.dum_list);
        list.setAdapter(arrayAdapter);}

    public String jsonParse() throws JSONException {

        JSONObject jsonObject = null;
        System.out.println("Checking :::::::::>" + strTest);

        if (strTest != null) {
            System.out.println("Checking :::::::::" + strTest);
            jsonObject = new JSONObject(strTest);
            JSONObject qn = jsonObject.getJSONObject("que");
            JSONObject ans1 = jsonObject.getJSONObject("ans1");
            JSONObject ans2 = jsonObject.getJSONObject("ans2");
            dumList[0] = (qn.getString("name") + ":\n" + qn.getString("question") + "\nContact Info :" + qn.getString("number"));
            System.out.println("Checking--------------:" + dumList[0]);
            dumList[1] = (ans1.getString("name") + ":\n" + ans1.getString("answer") + "\nContact Info :" + ans1.getString("number"));
            ;
            dumList[2] = (ans2.getString("name") + ":\n" + ans2.getString("answer") + "\nContact Info :" + ans2.getString("number"));

        }
        return null;
    }

}

