package phuong.example.com.androidproject;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MicrowaveActivity extends AppCompatActivity implements View.OnClickListener {
  private Button btnOnOff, btnstop, btnrestart;
  private EditText editInputTime;
  private ImageView microwaveImage;
  private ProgressBar timeProgress;
  private TextView text_runningTime;
  private static boolean turnOn = true;
  private int processTime = 0, progress = 0;
  private CountDownTimer timer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_microwave);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    //Bind all views
    btnOnOff = (Button) findViewById(R.id.onOffButton);
    btnstop = (Button) findViewById(R.id.microwave_stop);
    btnrestart = (Button) findViewById(R.id.microwave_restart);

    btnOnOff.setOnClickListener(this);
    btnstop.setOnClickListener(this);
    btnrestart.setOnClickListener(this);

    editInputTime = (EditText) findViewById(R.id.editInputTime);
    text_runningTime = (TextView) findViewById(R.id.text_runningTime);
    microwaveImage = (ImageView) findViewById(R.id.microwaveImage);
    timeProgress = (ProgressBar) findViewById(R.id.microwave_progress);
    timeProgress.setProgress(0);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.onOffButton:
        if (turnOn) {
          try {
            processTime = Integer.parseInt(editInputTime.getText().toString());
            microwaveState(true);

            timer = new CountDownTimer(processTime * 1000, 1000) {
              public void onTick(long millisUntilFinished) {

                int timeCooked = processTime - (int) (millisUntilFinished / 1000);

                if (processTime == 0) {
                  progress = 0;
                } else {
                  progress = (timeCooked * 100) / processTime;
                }
                text_runningTime.setText("" + millisUntilFinished / 1000);
                timeProgress.setProgress(progress);
              }

              public void onFinish() {
                timeProgress.setProgress(100);
                text_runningTime.setText("Its Done. Time's Up.");
                Vibrator mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                mVibrator.vibrate(500);
                Toast.makeText(getApplicationContext(), "Your plate is ready.", Toast.LENGTH_SHORT)
                    .show();

                microwaveState(false);
              }
            };
            timer.start();
          } catch (Exception e) {
            Log.e("TAG", "Error", e);
            Toast.makeText(getApplicationContext(), R.string.hint_enter_time, Toast.LENGTH_SHORT)
                .show();
          }
        } else {
          microwaveState(false);
        }
        break;
      case R.id.microwave_stop:
        timer.cancel();
        microwaveState(false);
        Toast.makeText(getApplicationContext(), R.string.stop_microwave, Toast.LENGTH_SHORT).show();
        break;
      case R.id.microwave_restart:
        timer.start();
        microwaveState(true);
        Toast.makeText(getApplicationContext(), R.string.btn_restart, Toast.LENGTH_SHORT).show();
        break;
    }
  }

  public void microwaveState(boolean flag) {
    if (flag) {
      btnstop.setText(R.string.stop);
      btnstop.setEnabled(true);
      timeProgress.setVisibility(View.VISIBLE);

      turnOn = false;
      microwaveImage.setImageResource(R.drawable.mic_on);
      btnOnOff.setText(R.string.turn_off);
    } else {
      btnstop.setEnabled(false);
      processTime = 0;
      progress = 0;
      timeProgress.setVisibility(View.INVISIBLE);
      editInputTime.setText("");

      turnOn = true;
      microwaveImage.setImageResource(R.drawable.mic_off);
      btnOnOff.setText(R.string.turn_on);
    }
  }
}