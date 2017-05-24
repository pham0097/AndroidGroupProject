package phuong.example.com.androidproject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class KitchenMainLightActivity extends AppCompatActivity implements View.OnClickListener {

  private SeekBar seekBarBrightNessChanger;
  TextView txtPercentage;
  ImageView imViewAndroid;
  Button btnOn, btnOff;
  int brightness = 0;//initial loading Lights are off make it 100 to turn on

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_kitchenmainlight);
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    btnOn = (Button) findViewById(R.id.kitchen_light_on);
    btnOff = (Button) findViewById(R.id.kitchen_light_off);

    btnOn.setOnClickListener(this);
    btnOff.setOnClickListener(this);

    imViewAndroid = (ImageView) findViewById(R.id.imViewAndroid);
    seekBarBrightNessChanger = (SeekBar) findViewById(R.id.seekBarBrightNessChanger);

    txtPercentage = (TextView) findViewById(R.id.txtPercentage);

    //Initial Brightness 0%
    seekBarBrightNessChanger.setProgress(brightness);
    txtPercentage.setText((int) brightness + " %");
    //Register OnSeekBarChangeListener, so it can actually change values
    seekBarBrightNessChanger.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
      public void onStopTrackingTouch(SeekBar seekBar) {

      }

      public void onStartTrackingTouch(SeekBar seekBar) {
        //Nothing handled here
      }

      public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        imViewAndroid.setImageBitmap(
            SetBrightness(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
                (Math.abs(progress))));
        txtPercentage.setText((int) progress + " %");
      }
    });
    imViewAndroid.setImageBitmap(
        SetBrightness(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
            (Math.abs(brightness))));
  }

  public Bitmap SetBrightness(Bitmap src, int value) {
    // original image size
    int width = src.getWidth();
    int height = src.getHeight();
    // create output bitmap
    Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
    // color information
    int A, R, G, B;
    int pixel;

    // scan through all pixels
    for (int x = 0; x < width; ++x) {
      for (int y = 0; y < height; ++y) {
        // get pixel color
        pixel = src.getPixel(x, y);
        A = Color.alpha(pixel);
        R = Color.red(pixel);
        G = Color.green(pixel);
        B = Color.blue(pixel);

        // increase/decrease each channel
        R += value;
        if (R > 255) {
          R = 255;
        } else if (R < 0) {
          R = 0;
        }

        G += value;
        if (G > 255) {
          G = 255;
        } else if (G < 0) {
          G = 0;
        }

        B += value;
        if (B > 255) {
          B = 255;
        } else if (B < 0) {
          B = 0;
        }

        // apply new pixel color to output bitmap
        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
      }
    }

    // return final image
    return bmOut;
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.kitchen_light_off:
        txtPercentage.setText(0 + " %");
        imViewAndroid.setImageBitmap(
            SetBrightness(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 0));
        seekBarBrightNessChanger.setProgress(0);
        break;
      case R.id.kitchen_light_on:
        seekBarBrightNessChanger.setProgress(100);
        txtPercentage.setText(100 + " %");
        imViewAndroid.setImageBitmap(
            SetBrightness(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 100));
        break;
    }
  }
}
