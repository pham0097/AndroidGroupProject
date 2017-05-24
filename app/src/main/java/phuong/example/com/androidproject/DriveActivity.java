package phuong.example.com.androidproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DriveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);
        Button drive = (Button)findViewById(R.id.button12);
        final EditText input = (EditText)findViewById(R.id.editText5);
        final Database db = new Database(this);
        SQLiteDatabase sq = db.getWritableDatabase();

        drive.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String value = input.getText().toString();
                if (value.matches("")) {
                    Toast.makeText(getApplicationContext(), "Please Set the Trip Distance", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(value.equals(input.getText().toString())){

                    Intent intent = new Intent(DriveActivity.this,OdometerActivity.class);
                    intent.putExtra("trip",value);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Trip Distance Set Successfully", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}
