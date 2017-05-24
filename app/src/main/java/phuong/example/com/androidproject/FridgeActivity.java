package phuong.example.com.androidprojectt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class FridgeActivity extends AppCompatActivity {

  private RadioButton fridgeSettings;
  private RadioButton freezerSettings;
  private Button tempSettings;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fridge);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    fridgeSettings = (RadioButton) findViewById(R.id.fridge);
    freezerSettings = (RadioButton) findViewById(R.id.freezer);
    tempSettings = (Button) findViewById(R.id.tempSettings);
    tempSettings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), FreezerSettingActivity.class);
        if (fridgeSettings.isChecked()) {
          intent.putExtra("isFridge", true);
        }
        if (freezerSettings.isChecked()) {
          intent.putExtra("isFridge", false);
        }
        startActivity(intent);
      }
    });
  }
}
