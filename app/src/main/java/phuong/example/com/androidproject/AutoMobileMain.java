package phuong.example.com.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AutoMobileMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //getSupportActionBar().setIcon(R.drawable.ic_action_name);


        final ListView listView  = (ListView) findViewById(R.id.list);
        String[] values = new String[] {  "Temparature Settings",
                "Fuel Level",
                "Radio Controls",
                "GPS Directions",
                "Lights",
                "Odometer",
                "Drive",
        };
        ArrayAdapter<String> Array = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        listView.setAdapter(Array);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item value
                String value= (String) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "User Clicked:"+value , Toast.LENGTH_LONG)
                        .show();
                if (position==0){
                    Intent intent = new Intent(AutoMobileMain.this, TemparatureSettingsActivity.class);
                    startActivity(intent);
                }
                if (position==1){
                    Intent intent = new Intent(AutoMobileMain.this, FuelLevelActivity.class);
                    startActivity(intent);
                }
                if (position==2){
                    Intent intent = new Intent(AutoMobileMain.this, RadioControl.class);
                    startActivity(intent);
                }
                if (position==3){
                    Intent intent = new Intent(AutoMobileMain.this, GpsActivity.class);
                    startActivity(intent);
                }
                if (position==4){
                    Intent intent = new Intent(AutoMobileMain.this, LightsActivity.class);
                    startActivity(intent);
                }/*
                if (position==5){
                    Intent intent = new Intent(AutoMobileMain.this, OdometerActivity.class);
                    startActivity(intent);
                }*/
                if (position==6){
                    Intent intent = new Intent(AutoMobileMain.this, DriveActivity.class);
                    startActivity(intent);
                }
            }
        });









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.automobile_activity_menu, menu);
        return true;


    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_Author) {
            Toast.makeText(getApplicationContext(),
                    "Author: Phuong Pham @Version 1.1.0", Toast.LENGTH_LONG)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
