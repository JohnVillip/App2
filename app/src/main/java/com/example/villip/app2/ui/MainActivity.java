package com.example.villip.app2.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.villip.app2.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    TextView digital_clock;
    Typeface tf;
    int mCurrentPeriod = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        digital_clock = findViewById(R.id.tv_digital_clock);
        tf = Typeface.createFromAsset(getAssets(), "DS-DIGI.TTF");
        digital_clock.setTypeface(tf);

        StartTimer();
    }

    public void StartTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                TimerMethod();
            }
        }, 0, 1000);
    }

    private void TimerMethod() {
        this.runOnUiThread(Timer_Tick);
    }

    private Runnable Timer_Tick = new Runnable() {
        public void run() {
            mCurrentPeriod--;
            if(mCurrentPeriod > 0){
                String temp = (new SimpleDateFormat("mm:ss")).format(new Date(
                        mCurrentPeriod * 1000));
                digital_clock.setText(temp);
            } else {
                finish();
            }

        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MyLog", "write external storage permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Функциональность ограничена");
                    builder.setMessage("Вы не сможете сохранять картинки во внешнее хранилище");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }
                    });
                    builder.show();
                }
            }
        }
    }
}
