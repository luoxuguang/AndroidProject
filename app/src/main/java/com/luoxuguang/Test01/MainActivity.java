package com.luoxuguang.Test01;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private ViewFlipper viewFlipper;
    private Context mContext;
    private int[] resId = {R.mipmap.ic_help_view_1,R.mipmap.ic_help_view_2,R.mipmap.ic_help_view_3,R.mipmap.ic_help_view_4};
    private GestureDetector mDetector;
    private MyGestureListener listener;
    private final static int MIN_MOVE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_view_flipper);

        mContext = MainActivity.this;
        listener = new MyGestureListener();
        mDetector = new GestureDetector(this,listener);

        viewFlipper = findViewById(R.id.demoViewFlipper);
        for (int i = 0; i < resId.length; i++) {
            viewFlipper.addView(getImageView(resId[i]));
        }
//        viewFlipper.startFlipping();

        Intent intent = getIntent();
        if (intent!=null){
            String title = intent.getStringExtra("title");
            UserInfo userInfo = (UserInfo) intent.getSerializableExtra("userInfo");
            setTitle(title);
        }

    }

    private ImageView getImageView(int resId){
        ImageView img = new ImageView(this);
        img.setBackgroundResource(resId);
        return img;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX()-e2.getX()>MIN_MOVE){
                viewFlipper.setInAnimation(mContext,R.anim.right_in);
                viewFlipper.setOutAnimation(mContext,R.anim.right_out);
                viewFlipper.showNext();
            }else if(e2.getX()-e1.getX()>MIN_MOVE){
                viewFlipper.setInAnimation(mContext,R.anim.left_in);
                viewFlipper.setOutAnimation(mContext,R.anim.left_out);
                viewFlipper.showPrevious();
            }
            return true;
        }
    }

}
