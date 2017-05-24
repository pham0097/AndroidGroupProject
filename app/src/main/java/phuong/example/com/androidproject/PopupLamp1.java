package phuong.example.com.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class PopupLamp1 extends AppCompatActivity {
TextView textView;
Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_lamp1);
       textView = (TextView) findViewById(R.id.result);
        aSwitch = (Switch) findViewById(R.id.lamp1);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    textView.setText("Lamp1 ON");

                } else{
                    textView.setText("Lamp1 OFF");
                }
            }
        });
    }
}
