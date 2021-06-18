package com.example.hotdog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static androidx.core.content.ContextCompat.getSystemService;

public class MainActivity extends AppCompatActivity {
    //音用
    private PlaySound playSound;
    //ホットドック表示
    private ImageView hotdog;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playSound = new PlaySound(this);

        hotdog = findViewById(R.id.hotdogstand);

        tmp = C();

        //tmpに温度を入れてね
        ((TextView) findViewById(R.id.clock)).setText(tmp);

        //温度
        /* センサーマネージャを取得する */
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

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
    public boolean onTouchEvent(MotionEvent event) {
        //指を離したかどうか
        if (event.getAction() == MotionEvent.ACTION_UP) {
            playSound.PlayVoice();
        }
        return true;
    }
    //温度を取得しようとしたなにか
    public String C() {
        //センサー名が出てきちゃっただよ
        temperature =mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        Log.d("aaa",String.valueOf(temperature));
        return String.valueOf(temperature);
    }
    //センサーのリスナー@@
    private Sensor mAccelerometerSensor;
    public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    // センサーマネージャを獲得する
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    // マネージャから加速度センサーオブジェクトを取得
	    mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    //@
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
         StringBuilder builder = new StringBuilder();
         // X軸
         float x = event.values[0];
         // Y軸
         float y = event.values[1];
         // Z軸
         float z = event.values[2];

         builder.append("X : " + (x) + "\n");
         builder.append("Y : " + (y) + "\n");
         builder.append("Z : " + (z) + "\n");

         // Logに出力
         Log.d(TAG, builder.toString());
     }
    }
    @Override
protected void onResume() {
     super.onResume();

     // 200msに一度SensorEventを観測するリスナを登録
     mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
}

@Override
protected void onPause() {
     super.onPause();

     // 非アクティブ時にSensorEventをとらないようにリスナの登録解除
     mSensorManager.unregisterListener(this);
}
}

public class AccelerometerSampleActivity extends Activity implements SensorEventListener {

    /** センサーマネージャオブジェクト */
    private SensorManager mSensorManager;

    /** 加速度センサーオブジェクト */
    private Sensor mAccelerometerSensor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // センサーマネージャを獲得する
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // マネージャから加速度センサーオブジェクトを取得
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
