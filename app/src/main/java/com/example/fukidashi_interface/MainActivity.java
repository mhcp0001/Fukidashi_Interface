package com.example.fukidashi_interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebView;

import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Test02_01";

    public static final int NUM_LANG = 5;  /*言語数*/
    public static final int EN = 0;  /*英語*/
    public static final int JP = 1;  /*日本語*/
    public static final int KR = 2;  /*韓国語*/
    public static final int CN = 3;  /*中国語*/

    static final int RESULT_SUBACTIVITY = 1000;

    public static int mode_me = 99;
    public static int mode_you = 99;

    TextView[] tv = new TextView[NUM_LANG];
    //String str[] = new String[NUM_LANG];
    String str[] = new String[]{"Hello","こんにちは","안녕하세요","你好"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        waitTouch();
    }

    public void waitTouch(){
        setContentView(R.layout.activity_initial);
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);

//        ImageView imageView = (ImageView) findViewById(R.id.gifView);
//        GlideDrawableImageViewTarget target = new GlideDrawableImageViewTarget(imageView);
        //いらん

        boolean sig = false; //通信相手からシグナルを受け取ったらTrueになる、という想定。

        //gifアニメーション表示関数(妙に重いのとサイズがあっていないためコメントアウト)
        //Glide.with(this).load(R.raw.waterdrop).into(target);

        //テキストエリア１（真ん中）をタッチ可能に設定&wait表示
        readyfor(tv);


        WebView target = (WebView) findViewById(R.id.gifarea);
        target.getSettings().setUseWideViewPort(true);
        target.getSettings().setJavaScriptEnabled(true);
        target.getSettings().setLoadWithOverviewMode(true);
        String filePath = "file:///android_res/raw/" + "waterdrop.gif";
        target.loadUrl(filePath);

        //タッチが検出されるまで待つ
        findViewById(R.id.text_one).setOnClickListener(this);


        if(sig){//タッチが検出されない側、つまりシグナルを受け取った側
            findViewById(R.id.text_one).setEnabled(false); //touchWaitを無効化
            tvClear2(tv);
            //～待ち時間～

            //AからString[]を受け取ったらsetLanguage

            int getMode=0;//A側から受け取ったMode
            mode_you = getMode;
            String[] fromA = str;
            setLanguage(fromA);
        }
    }

    public void setLanguage(){ //A側（画面をタッチして言語選択をする方）
        setContentView(R.layout.activity_set);
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);

        tvClear1(tv);

        //タッチイベントを待機
        findViewById(R.id.text_two).setOnClickListener(this);
        findViewById(R.id.text_three).setOnClickListener(this);
        findViewById(R.id.text_four).setOnClickListener(this);
        findViewById(R.id.text_five).setOnClickListener(this);
    }


    public void setLanguage(String[] trans){//B側（Aから受け取った翻訳済み言語をタッチして言語選択する方）
        setContentView(R.layout.activity_set);
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);

        inputTv(trans);

        //strを領域2～5に表示
        findViewById(R.id.text_two).setOnClickListener(this);
        findViewById(R.id.text_three).setOnClickListener(this);
        findViewById(R.id.text_four).setOnClickListener(this);
        findViewById(R.id.text_five).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        final TextView tex = (TextView) findViewById(v.getId());
        if (v != null) {
            switch (v.getId()) {
                case R.id.text_one:
                    onClickArea1(tv);
                    break;

                case R.id.text_two:
                    // クリック処理
                    onClickArea2();
                    break;

                case R.id.text_three:
                    // クリック処理
                    onClickArea3();
                    break;

                case R.id.text_four:
                    // クリック処理
                    onClickArea4();
                    break;

                case R.id.text_five:
                    // クリック処理
                    onClickArea5();
                    break;

                default:
            }
        }
        if(mode_you==99) { //mode_youが決まっていない→A側なので音声認識と翻訳と翻訳結果の送信を行う
//            speechRecognition();
//            translate();
//            sendTranslated();
            //～待ち時間～

            int getMode = 0;//B側から送られてきたBのmode
            mode_you = getMode;
        }

        if(mode_me!=99 && mode_you!=99) {
            Intent intent = new Intent(getApplication(), SubActivity.class);
            int requestCode = RESULT_SUBACTIVITY;
            startActivityForResult( intent, requestCode );
        }
    }

    public void readyfor(TextView[] tv){

//        WebView target = (WebView) findViewById(R.id.gifarea);
//        String filePath = "file:///android_res/raw/" + "waterdrop.gif";
//        target.loadUrl(filePath);

        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText("wait touching");
        tv[1] = (TextView)findViewById(R.id.text_two);
        tv[1].setText("");
        tv[2] = (TextView)findViewById(R.id.text_three);
        tv[2].setText("");
        tv[3] = (TextView)findViewById(R.id.text_four);
        tv[3].setText("");
        tv[4] = (TextView)findViewById(R.id.text_five);
        tv[4].setText("");


    }

    public void tvClear1(TextView[] tv){//A側の言語選択画面

        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText("");
        tv[1] = (TextView)findViewById(R.id.text_two);
        tv[1].setText("English");
        tv[2] = (TextView)findViewById(R.id.text_three);
        tv[2].setText("日本語");
        tv[3] = (TextView)findViewById(R.id.text_four);
        tv[3].setText("한국어");
        tv[4] = (TextView)findViewById(R.id.text_five);
        tv[4].setText("簡体字");
    }

    public void tvClear2(TextView[] tv){//相手からのシグナル待ち
        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText("wait");
        tv[1] = (TextView)findViewById(R.id.text_two);
        tv[1].setText("");
        tv[2] = (TextView)findViewById(R.id.text_three);
        tv[2].setText("");
        tv[3] = (TextView)findViewById(R.id.text_four);
        tv[3].setText("");
        tv[4] = (TextView)findViewById(R.id.text_five);
        tv[4].setText("");
    }

    public void inputTv(String str[]){//A側から投げられたString[]を全て表示する
        for(int i=1; i < NUM_LANG; i++){
            tv[i].setText(str[i-1]);
        }
    }

    public void inputCenterTv(int lang){//言語選択された時にその言葉を真ん中のTextViewに表示する
        lang += 1;
        for(int i=0; i < NUM_LANG; i++){
            if(i == lang){
                tv[0].setText(tv[i].getText().toString());
                tv[i].setText("");
                continue;
            }
            tv[i].setText("");
        }

    }

    private void onClickArea1(TextView[] tv){ //初期状態でタッチされたら、自分の言語設定画面に遷移
        findViewById(R.id.text_one).setEnabled(false);
        //ここで相方へシグナルを送信する必要がある。
        setLanguage();
    }

    private void onClickArea2(){
        Toast.makeText(getApplicationContext(), "Set Language English (" +
                Integer.toString(EN) + ")", Toast.LENGTH_SHORT).show();
        mode_me = EN;
        inputCenterTv(mode_me);
    }

    private void onClickArea3(){
        Toast.makeText(getApplicationContext(), "日本語に設定しました (" +
                Integer.toString(JP) + ")", Toast.LENGTH_SHORT).show();
        mode_me = JP;
        inputCenterTv(mode_me);
    }

    private void onClickArea4(){
        Toast.makeText(getApplicationContext(), "한국어로 설정했습니다 (" +
                Integer.toString(KR) + ")", Toast.LENGTH_SHORT).show();
        mode_me = KR;
        inputCenterTv(mode_me);
    }

    private void onClickArea5(){
        Toast.makeText(getApplicationContext(), "在中文设定了 (" +
                Integer.toString(CN) + ")", Toast.LENGTH_SHORT).show();
        mode_me = CN;
        inputCenterTv(mode_me);
    }

    // SubActivity からの返ってきた時にModeを初期化、初期画面に戻る
    protected void onActivityResult( int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK && requestCode == RESULT_SUBACTIVITY && null != intent) {
            mode_you = intent.getIntExtra("mode_you", 0);
            mode_me = intent.getIntExtra("mode_me", 0);
            waitTouch();
        }
    }

}
