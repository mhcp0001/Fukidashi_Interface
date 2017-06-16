package com.example.fukidashi_interface;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int NUM_LANG = 5;  /*言語数*/
    public static final int EN = 0;  /*言語数*/
    public static final int JP = 1;  /*言語数*/
    public static final int DE = 2;  /*言語数*/
    public static final int FR = 3;  /*言語数*/
    public static final int CN = 4;  /*言語数*/

    public static int lang = 99;

    TextView[] tv = new TextView[NUM_LANG];
    //String str[] = new String[NUM_LANG];
    String str[] = new String[]{"Hello","こんにちは","Guten Tag","Bonjour","你好"};

    /** スレッドUI操作用ハンドラ */
    private Handler mHandler = new Handler();
    /** テキストオブジェクト */
    private Runnable updateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        findViewById(R.id.text_five).setOnClickListener(this);

        inputTv(str);

        updateText = new Runnable() {
            public void run() {
                TextView text = (TextView) findViewById(R.id.count);
                Integer count = Integer.valueOf(text.getText().toString());
                count += 1;
                text.setText(count.toString());
                mHandler.removeCallbacks(updateText);
                mHandler.postDelayed(updateText, 1000);
            }
        };
        mHandler.postDelayed(updateText, 1000);

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
                    Toast.makeText(getApplicationContext(), "Set Language Deutsch (" +
                            Integer.toString(DE) + ")", Toast.LENGTH_SHORT).show();
                    lang = DE;
                    break;

                case R.id.text_four:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language French (" +
                            Integer.toString(FR) + ")", Toast.LENGTH_SHORT).show();
                    lang = FR;
                    break;

                case R.id.text_five:
                    // クリック処理
                    Toast.makeText(getApplicationContext(), "Set Language Chinese (" +
                            Integer.toString(CN) + ")", Toast.LENGTH_SHORT).show();
                    lang = CN;
                    break;

                default:
                    break;
            }
            inputTv(lang);
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
        tv[4] = (TextView)findViewById(R.id.text_five);
        tv[4].setText("");
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
}
