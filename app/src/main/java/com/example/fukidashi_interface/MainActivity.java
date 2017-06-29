package com.example.fukidashi_interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "Test02_01";

    public static final int NUM_LANG = 5;  /*言語数*/
    public static final int EN = 0;  /*英語*/
    public static final int JP = 1;  /*日本語*/
    public static final int KR = 2;  /*韓国語*/
    public static final int CN = 3;  /*中国語*/

    public static int lang = 99;
    public static int mode_me = lang;
    public static int mode_you = lang;

    boolean timerFlag = false;

    TextView[] tv = new TextView[NUM_LANG];
    //String str[] = new String[NUM_LANG];
    String str[] = new String[]{"Hello","こんにちは","안녕하세요","你好"};
    String s = new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setScreenSub();
    }

    public void setScreenMain(){
        setContentView(R.layout.activity_main);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);


        //テキストエリア１（真ん中）をタッチ可能に設定&wait表示
        //タッチが検出された側
        findViewById(R.id.text_one).setOnClickListener(this);
        readyfor(tv);

        //～認識待ち～

        /*翻訳側からいい感じにテキスト投げられてくる予定*/
        //str = honyaku();
        //認識した言語(s_lang)およびその翻訳結果(String[])が投げられてくる？
        //mode_me = s_lang;//認識した言語
        //langとstrを相方に投げる
        //テキストエリア１にs_lang表示
        //set_me(s_lang);

        //ラスト、相手の言語(t_lang)が返ってくるので
        //mode_you = t_lang;



        //タッチが検出されない側、つまりシグナルを受け取る側
        tvClear2(tv);

        //～待ち時間～
        //langとstrを受け取る
        mode_you=lang;

        //strを領域2～5に表示
        findViewById(R.id.text_two).setOnClickListener(this);
        findViewById(R.id.text_three).setOnClickListener(this);
        findViewById(R.id.text_four).setOnClickListener(this);
        findViewById(R.id.text_five).setOnClickListener(this);

        inputTv(str);


    }

    public void setScreenSub(){

        Log.d(TAG,"into setScreenSub");

        setContentView(R.layout.activity_sub);
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);//FullScreen

        TextView tv1 = (TextView)findViewById(R.id.text); //activity_subのTextView
        int i = 0;
        Log.d(TAG,"setScreenSub");

        //音声認識か、通信によってsに文字列が入っているかを確認する

        //viewは表示するためのString型変数。翻訳クラスや通信クラスはこれに表示したい文字列を放り込む。
        // String view = transrator();
        String view = "I have a pen";

        tv1.setText(view);


        if(timerFlag) {  //一定時間が経過したらtimerFlagをTrueにする。これで初期画面に遷移できる
            Log.d(TAG, "loop break");
            setScreenMain();
        }

    }

    @Override
    public void onClick(View v) {
        final TextView tex = (TextView) findViewById(v.getId());
        if (v != null && lang == 99) {
            switch (v.getId()) {
                case R.id.text_one:

                    findViewById(R.id.text_one).setEnabled(false);
                    tvClear1(tv);
                    //ここで相方へシグナルを送信する必要がある。
                    break;

                case R.id.text_two:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language English (" +
                            Integer.toString(EN) + ")", Toast.LENGTH_SHORT).show();
                    lang = EN;
                    mode_me = EN;
                    break;

                case R.id.text_three:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language Japanese (" +
                            Integer.toString(JP) + ")", Toast.LENGTH_SHORT).show();
                    lang = JP;
                    mode_me = JP;
                    break;

                case R.id.text_four:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language Deutsch (" +
                            Integer.toString(CN) + ")", Toast.LENGTH_SHORT).show();
                    lang = CN;
                    mode_me = CN;
                    break;

                case R.id.text_five:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language French (" +
                            Integer.toString(KR) + ")", Toast.LENGTH_SHORT).show();
                    lang = KR;
                    mode_me = KR;
                    break;

                default:
            }
            inputTv(lang);

            /*transrate(lang);  //翻訳機に言語の番号を送信して翻訳言語確定*/

            setScreenSub();
        }
    }

    public void readyfor(TextView[] tv){
        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText("wait touching");

    }

    public void tvClear1(TextView[] tv){//音声認識待ちするところ
        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText("・・・");
        tv[1] = (TextView)findViewById(R.id.text_two);
        tv[1].setText("");
        tv[2] = (TextView)findViewById(R.id.text_three);
        tv[2].setText("");
        tv[3] = (TextView)findViewById(R.id.text_four);
        tv[3].setText("");
        tv[4] = (TextView)findViewById(R.id.text_five);
        tv[4].setText("");
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

    public void set_me(TextView[] tv){
        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText(' '/*翻訳される前の言語*/);

    }

    public void inputTv(String str[]){
        for(int i=1; i < NUM_LANG-1; i++){
            tv[i].setText(str[i]);
        }
    }

    public void inputTv(int lang){
        for(int i=0; i < NUM_LANG; i++){
            if(i == lang){
                tv[0].setText(tv[i].getText().toString());
                tv[i].setText("");
                continue;
            }
            tv[i].setText("");
        }

    }

}
