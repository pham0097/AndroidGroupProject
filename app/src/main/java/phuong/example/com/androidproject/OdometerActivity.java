package phuong.example.com.androidproject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class OdometerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometer);
        Button resetbtn =(Button)findViewById(R.id.button11);
        final EditText ret = (EditText)findViewById(R.id.editText4);
        final EditText odometer = (EditText)findViewById(R.id.editText3);
        final Database db = new Database(this);

        Cursor cursor = db.getDistance();
        cursor.moveToFirst();
        String value = cursor.getString(cursor.getColumnIndex(Database.COLUAM1));
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ret.setText("0");
                Toast.makeText(getApplicationContext(),
                        "Trip Distance Reset Done Successfully", Toast.LENGTH_LONG)
                        .show();
            }
        });
        String tripValue = getIntent().getStringExtra("trip");
        int value1 = Integer.parseInt(tripValue);
        int value2 = Integer.parseInt(value);
        int total = value1 + value2;
        String result = Integer.toString(total);
        ret.setText(tripValue);
        odometer.setText(result);
        db.setDistance(result);
    }
}
