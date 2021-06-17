//音声再生用
package com.example.hotdog;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.Random;

public class PlaySound {

    private  static SoundPool soundPool;
    private  static  int helloSound;
    private  static int tenkivoice;
    private  static int giftvoice;
    private static int hotvoice;
    private static int coldvoice;

    public PlaySound(Context context){

        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        //音声ファイルの読み込み
        helloSound = soundPool.load(context,R.raw.hello,1);
        tenkivoice = soundPool.load(context,R.raw.tenkiv,1);
        giftvoice = soundPool.load(context,R.raw.gift,1);
        hotvoice = soundPool.load(context,R.raw.hotvoice,1);
        coldvoice = soundPool.load(context,R.raw.cold,1);

    }

    //ランダム再生用
    public void PlayVoice(){
        Random rand = new Random();
        int r = rand.nextInt(3);

        switch (r){
            case 0:
                soundPool.play(helloSound,3.0f,3.0f,1,0,1.0f);
                break;

            case 1:
                soundPool.play(tenkivoice,4.0f,4.0f,1,0,1.0f);
                break;

            case 2:
                soundPool.play(giftvoice,3.0f,3.0f,1,0,1.0f);
                break;

        }
    }

    //暑い時用
    private void Hot(){
        soundPool.play(hotvoice,1.0f,1.0f,1,0,1.0f);
    }

    //寒い時用
    private void Cold(){
        soundPool.play(coldvoice,1.0f,1.0f,1,0,1.0f);
    }



}
