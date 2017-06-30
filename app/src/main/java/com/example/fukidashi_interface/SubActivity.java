package com.example.fukidashi_interface;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by koki on 2017/07/01.
 */

public class SubActivity extends MainActivity {

    private boolean timerFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);//FullScreen

        mainRoutine();
    }

    public void mainRoutine(){
        TextView tv1 = (TextView)findViewById(R.id.text); //activity_subのTextView
        int i = 0;
        Log.d(TAG,"setScreenSub");

        //音声認識待ち
        //もしくは通信待ち

        //viewは表示するためのString型変数。翻訳クラスや通信クラスはこれに表示したい文字列を放り込む。
        // String view = transrator();
        String view = "I have a pen";

        setText(tv1,view); //Viewが配列の場合でも対応できるようにはなってる。

        if(timerFlag) {  //一定時間が経過したらtimerFlagをTrueにする。これで初期画面に遷移できる
            Log.d(TAG, "loop break");
            timerFlag = false;

            Intent intent = new Intent();
            // keyword "RESULT" でデータの可算結果 value を返す
            intent.putExtra("mode_you", 99);
            intent.putExtra("mode_me", 99);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    private void setText(TextView tv, String view){ //TextViewに文字を表示する
        tv.setText(view);
    }
    private void setText(TextView tv, String[] view){//TextViewにString配列の中身を順番に表示する
        int i = 0;
        tv.setText(view[i]);
    }

}
