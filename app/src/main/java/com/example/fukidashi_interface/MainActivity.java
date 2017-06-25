package com.example.fukidashi_interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SpeechRecognitionListenerInterface {

    public static final int NUM_LANG = 4;  /*言語数*/
    public static final int EN = 0;  /*英語*/
    public static final int JP = 1;  /*日本語*/
    public static final int KR = 2;  /*韓国語*/
    public static final int CN = 3;  /*中国語*/

    public static int lang = 99;

    private SpeechRecognitionNotify sn = null;

    TextView[] tv = new TextView[NUM_LANG];
    //String str[] = new String[NUM_LANG];
    String str[] = new String[]{"Hello","こんにちは","アンニョンハセヨ","你好"};
    String s = new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 通知用クラスのインスタンス化
        sn = new SpeechRecognitionNotify();
        // 通知用クラスに通知先のインスタンスを付加
        sn.setListener(this);
        setScreenMain();
    }

    private void setScreenMain(){
        setContentView(R.layout.activity_main);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);

        tvClear(tv);

        /*翻訳側からいい感じにテキスト投げられてくる予定*/
        //str = honyaku();

        findViewById(R.id.text_one).setOnClickListener(this);
        findViewById(R.id.text_two).setOnClickListener(this);
        findViewById(R.id.text_three).setOnClickListener(this);
        findViewById(R.id.text_four).setOnClickListener(this);

        inputTv(str);

    }

    private void setScreenSub(){
        setContentView(R.layout.activity_sub);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);

        TextView tv1 = (TextView)findViewById(R.id.text);

        while(true){

            // TODO:音声認識の待ち受けはこっちでやるべきかどうか確認
            // String spr = SpeechRecognition();
            // String trans = transrator(spr);
            String trans = "translated";
            tv1.setText(trans);
        }

    }

    @Override
    public void onClick(View v) {
        final TextView tex = (TextView) findViewById(v.getId());
        if (v != null && lang == 99) {
            switch (v.getId()) {
                case R.id.text_one:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language English (" +
                            Integer.toString(EN) + ")", Toast.LENGTH_SHORT).show();
                    lang = EN;
                    break;

                case R.id.text_two:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language Japanese (" +
                            Integer.toString(JP) + ")", Toast.LENGTH_SHORT).show();
                    lang = JP;
                    break;

                case R.id.text_three:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language Korean (" +
                            Integer.toString(KR) + ")", Toast.LENGTH_SHORT).show();
                    lang = KR;
                    break;

                case R.id.text_four:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language Chinese (" +
                            Integer.toString(CN) + ")", Toast.LENGTH_SHORT).show();
                    lang = CN;
                    break;

                default:
            }
            inputTv(lang);

            /*transrate(lang);  //翻訳機に言語の番号を送信して翻訳言語確定*/

            setScreenSub();
        }
    }

    public void tvClear(TextView[] tv){
        tv[0] = (TextView)findViewById(R.id.text_one);
        tv[0].setText("......");
        tv[1] = (TextView)findViewById(R.id.text_two);
        tv[1].setText("");
        tv[2] = (TextView)findViewById(R.id.text_three);
        tv[2].setText("");
        tv[3] = (TextView)findViewById(R.id.text_four);
        tv[3].setText("");
    }

    public void inputTv(String str[]){
        for(int i=0; i < NUM_LANG; i++){
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

    @Override
    public void noSpeechRecognition(){ /*音声認識されていないとき*/
    }

    @Override
    public void SpeechRecognition(){ //音声認識がされた時

    }
}
