package phuong.example.com.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TemparatureSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temparature_settings);
        final EditText edit1 = (EditText)findViewById(R.id.editText);
        final EditText edit2 = (EditText)findViewById(R.id.editText2);
        final TextView text1 = (TextView)findViewById(R.id.textView6);
        final TextView text2 = (TextView)findViewById(R.id.textView7);

        Button tempbtn = (Button)findViewById(R.id.button7);
        tempbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edit1.getText().toString();
                String b = edit2.getText().toString();

                if(a.matches("") && b.matches("") ){
                    Toast.makeText(getApplicationContext(),
                            "Please Set Front Temparature and Back Temparature ", Toast.LENGTH_LONG)
                            .show();
                    return;
                }else if(a.matches("")){
                    Toast.makeText(getApplicationContext(),
                            "Please Set Front Temparature of Car", Toast.LENGTH_LONG)
                            .show();
                    return;
                }else if(b.matches("")){
                    Toast.makeText(getApplicationContext(),
                            "Please Set Back Temparature of Car ", Toast.LENGTH_LONG)
                            .show();
                    return;
                }else {
                    text1.setText("Front Temparature Set to:" + edit1.getText());
                    text2.setText("Back Temparature Set to:" + edit2.getText());
                    Toast.makeText(getApplicationContext(),
                            "Temparatures Set Succesfully ", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
            }
        });
        Button resetbtn = (Button)findViewById(R.id.button6);
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text1.setText("Front Temparatue set to: 0");
                text2.setText("Back Temparature set to: 0");
                edit1.setText("0");
                edit2.setText("0");
                Toast.makeText(getApplicationContext(),
                        "Temparatures of Front and Back Zones are Reset to Zero ", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}
