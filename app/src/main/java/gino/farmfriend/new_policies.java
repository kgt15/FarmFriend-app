package gino.farmfriend;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Gino on 18-02-2016.
 */

public class new_policies extends AppCompatActivity {
    ListView listView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.new_policies);

            listView=(ListView)findViewById(R.id.listView);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("New Policies");
            String[] values = new String[] { "Over 4 per cent annual growth rate aimed over next two decades",

                    "Greater private sector participation through contract farming",
                    "Price protection for farmers",

                    "National agricultural insurance scheme to be launched"
                    ,"Dismantling of restrictions on movement of agricultural commodities throughout the country",

                    "Rational utilisation of country's water resources for optimum use of irrigation potential",

                    "High priority to development of animal husbandry, poultry, dairy and aquaculture",
                    "Capital inflow and assured markets for crop production",

                    "Exemption from payment of capital gains tax on compulsory acquisition of agricultural land",

                    "Minimise fluctuations in commodity prices",
                    "Continuous monitoring of international prices",

                    "Plant varieties to be protected through a legislation.",

                    "Adequate and timely supply of quality inputs to farmers",

                    "High priority to rural electrification",
                    "Setting up of agro-processing units and creation of off-farm employment in rural areas"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_activated_1, android.R.id.text1, values);



            // Assign adapter to ListView
            listView.setAdapter(adapter);





        }

    }


