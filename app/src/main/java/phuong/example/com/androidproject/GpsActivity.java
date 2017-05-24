package phuong.example.com.androidproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GpsActivity extends AppCompatActivity {
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
        final Button googlebutton = (Button)findViewById(R.id.button5);
        final TextView tv = (TextView)findViewById(R.id.textView15);
        final ProgressBar bar = (ProgressBar)findViewById(R.id.progressBar2);
        bar.setVisibility(View.INVISIBLE);
        googlebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.setVisibility(View.VISIBLE);
                tv.setText("Launching...");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Create a Uri from an intent string. Use the result to create an Intent
                        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
                        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        // Make the Intent explicit by setting the Google Maps package
                        mapIntent.setPackage("com.google.android.apps.maps");
                        startActivity(mapIntent);
                        Toast.makeText(getApplicationContext(),
                                "Google Navigation Launched ", Toast.LENGTH_LONG)
                                .show();
                    }

                }, 1700L);


            }
        });






    }
}
