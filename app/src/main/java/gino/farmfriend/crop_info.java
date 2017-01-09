package gino.farmfriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Gino on 18-02-2016.
 */
public class crop_info extends AppCompatActivity {
    ImageButton rice;
    ImageButton wheat;
    ImageButton maize;
    ImageButton banana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_info);
        rice=(ImageButton)findViewById(R.id.rice);
        wheat=(ImageButton)findViewById(R.id.wheat);
        maize=(ImageButton)findViewById(R.id.maize);
        banana=(ImageButton)findViewById(R.id.banana);
        rice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreinfo();
            }
        });
        wheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Check...............", "Check");

                moreinfo2();
            }
        });
        maize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreinfo3();
            }
        });
        banana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreinfo4();
            }
        });
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("CROPS");
    }
    public void moreinfo()
    {
        Intent intent=new Intent(getApplicationContext(), gino.farmfriend.rice.class);
        startActivity(intent);
    }
    public void moreinfo2()
    {
        Intent intent=new Intent(getBaseContext(), gino.farmfriend.wheat.class);
        startActivity(intent);

    }
    public void moreinfo3(){
        Intent intent=new Intent(this, gino.farmfriend.maize.class);
        startActivity(intent);
    }
    public void moreinfo4()
    {
        Intent intent=new Intent(crop_info.this, gino.farmfriend.banana.class);
        startActivity(intent);
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
