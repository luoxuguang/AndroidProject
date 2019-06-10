package com.luoxuguang.Test01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends Activity {

    public static final int REQUEST_CODE = 999;

    private Button mEnterButton;
    private View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.enter_button:
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        mEnterButton = findViewById(R.id.enter_button);
//        mEnterButton.setOnClickListener(mOnClick);

        TextView textView = (TextView) findViewById(R.id.splash_text);
        final String title = textView.getText().toString();
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                UserInfo userInfo = new UserInfo("xiaoming",21);

                Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("userInfo",userInfo);

                startActivity(intent);
                startActivityForResult(intent,REQUEST_CODE);
            }
        },1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
