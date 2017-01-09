package gino.farmfriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Gino on 18-02-2016.
 */
public class about_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("About-Us");


    }
}