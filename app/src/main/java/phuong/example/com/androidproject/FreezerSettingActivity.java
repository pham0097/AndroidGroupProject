package phuong.example.com.androidproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FreezerSettingActivity extends AppCompatActivity {
  private Button btnSetTemp;
  private TextView initFreezerTemp,freezer;
  private Spinner spinnerTemp;
  String[] spinnerData;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_freezersettings);

    btnSetTemp = (Button) findViewById(R.id.btnSetTemp);
    initFreezerTemp = (TextView) findViewById(R.id.initFreezerTemp);
    freezer = (TextView) findViewById(R.id.freezer);
    spinnerTemp = (Spinner) findViewById(R.id.spinnerTemp);

    Bundle mBundle = getIntent().getExtras();
    if (mBundle.getBoolean("isFridge")) {
      freezer.setText("Fridge Temperature");
      spinnerData = new String[] { "5","4", "3", "2", "1", "0", "-1", "-2" };
      Toast.makeText(getApplicationContext(), "Fridge", Toast.LENGTH_SHORT).show();
    } else {
      freezer.setText("Freezer Temperature");
      spinnerData = new String[] { "-16", "-18", "-20", "-22", "-24" };
      Toast.makeText(getApplicationContext(), "FREEZER", Toast.LENGTH_SHORT).show();
    }
    ArrayAdapter<String> mAdapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerData);
    spinnerTemp.setAdapter(mAdapter);
    btnSetTemp.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        initFreezerTemp.setText(String.valueOf(spinnerTemp.getSelectedItem()));
        Toast.makeText(FreezerSettingActivity.this, spinnerTemp.getSelectedItem() + "",
            Toast.LENGTH_SHORT).show();
      }
    });
  }
}
