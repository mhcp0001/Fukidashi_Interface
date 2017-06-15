package com.example.fukidashi_interface;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View. SYSTEM_UI_FLAG_IMMERSIVE);

        findViewById(R.id.text_one).setOnClickListener(this);
        findViewById(R.id.text_two).setOnClickListener(this);
        findViewById(R.id.text_three).setOnClickListener(this);
        findViewById(R.id.text_four).setOnClickListener(this);
        findViewById(R.id.text_five).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final TextView mTextView = (TextView) findViewById(v.getId());
        if (v != null) {
            switch (v.getId()) {
                case R.id.text_one:
                    // クリック処理
                    break;

                case R.id.text_two:
                    // クリック処理
                    break;

                case R.id.text_three:
                    // クリック処理
                    break;

                case R.id.text_four:
                    // クリック処理
                    break;

                case R.id.text_five:
                    // クリック処理
                    break;

                default:
                    break;
            }
        }
    }
}
