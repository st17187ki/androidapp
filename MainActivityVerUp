package com.example.hotdog;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements Runnable, SensorEventListener {
    //音用
    private PlaySound playSound;

    //画像表示
    private ImageView hotdog;
    private int i=0;

    //時計用
    private Handler mHandler;
    private Timer mTimer;

    //温度用
    private SensorManager mSensorManager;

    private boolean mIsSensor;
    private Sensor temperature;
    private String tmp;

    // 時刻表示のフォーマット
    private static SimpleDateFormat mSimpleDataFormat = new SimpleDateFormat("yyyy年　MM月dd日　HH:mm:ss");

    //　加速度
    SensorManager sm;
    TextView tv;
    Handler h;
    float gx, gy, gz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playSound = new PlaySound(this);

        /* センサーマネージャを取得する */
        mSensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        hotdog = findViewById(R.id.hotdogstand);
        hotdog.setImageResource(R.drawable.hotstand);
        tv = findViewById(R.id.temperature);

        h = new Handler();
        h.postDelayed(this, 500);

        //時計
        mHandler = new Handler(getMainLooper());
        mTimer = new Timer();

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        String nowDate = mSimpleDataFormat.format(calendar.getTime());
                        // 時刻表示をするTextView
                        ((TextView) findViewById(R.id.clock)).setText(nowDate);
                    }
                });
            }
        }, 0, 1000);
        //ここまで時計
    }

    //加速度
    @Override
    protected void onResume() {
        super.onResume();
        //加速度
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors =
                sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (0 < sensors.size()) {
            sm.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

    }


    //時計用
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 定期実行をcancelする
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
    //時計用ここまで

    //音再生
    //タップ中は画像を切り替えて指を離したら画像を元に戻す機能を追加しました
    public boolean onTouchEvent(MotionEvent event) {
        //タップしている時
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            hotdog.setImageResource(R.drawable.shake);
        }
        //指を離したかどうか
        if (event.getAction() == MotionEvent.ACTION_UP) {
            playSound.PlayVoice();
            //画像の切り替え
            hotdog.setImageResource(R.drawable.hotstand);
        }
        return true;
    }


    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        gx = event.values[0];
        gy = event.values[1];
        gz = event.values[2];

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void run() {
      /*  if(gx >= 10){
            hotdog.setImageResource(R.drawable.shake);
            while(i < 100){
                i++;
            }
            hotdog.setImageResource(R.drawable.hotstand);
        }

       */
    }

}
