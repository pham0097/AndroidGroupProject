package phuong.example.com.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class FuelLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_level);
        final SeekBar seekbar = (SeekBar)findViewById(R.id.seekBar2);
        Button fuelbtn = (Button)findViewById(R.id.button9);
        final TextView tv = (TextView)findViewById(R.id.textView16);
        final TextView tv1 = (TextView)findViewById((R.id.textView22));
        final TextView tv2 = (TextView)findViewById(R.id.textView21);

        tv.setText("0");

        seekbar.setProgress(0);
        seekbar.setMax(45);

        int a = Integer.parseInt(tv.getText().toString());
        int b = Integer.parseInt(tv1.getText().toString());

        final int result = a*b ;
        tv2.setText("" +result);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv.setText("" +progress);
                int e = seekBar.getProgress();
                int f = Integer.parseInt(tv1.getText().toString());

                final int answer = e * f;
                tv2.setText(""+answer);

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        fuelbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                for(int i = Integer.parseInt(tv.getText().toString()); i<=45; i++){
                    seekbar.setProgress(i);
                    tv.setText(Integer.toString(i));
                    int c = Integer.parseInt(tv.getText().toString());
                    int d = Integer.parseInt(tv1.getText().toString());
                    final int value = c * d;
                    tv2.setText(""+value);
                }

            }
        });


    }
}
