package gino.farmfriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Gino on 18-02-2016.
 */
public class contact_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_details);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Contact Details");


    }
}
