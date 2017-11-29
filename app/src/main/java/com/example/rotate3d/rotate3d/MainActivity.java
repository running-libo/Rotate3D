package com.example.rotate3d.rotate3d;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout rlContainer;
    private ImageView imageView1,imageView2;
    private final int PAGE_LOGIN = 0;
    private final int PAGE_REGISTER = 1;
    private final int DEPTHZ = 500;
    private final int DURATION = 300;
    private int curPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        rlContainer = (RelativeLayout) findViewById(R.id.rl);
        imageView1 = (ImageView) findViewById(R.id.iv_page1);
        imageView2 = (ImageView) findViewById(R.id.iv_page2);
        rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(curPage == PAGE_LOGIN){
                    rotateAnim();
                    curPage = PAGE_REGISTER;
                }else{
                    rotateAnim();
                    curPage = PAGE_LOGIN;
                }
            }
        });
    }

    private void rotateAnim(){
        Rotate3D rotate3D = new Rotate3D(0,90,rlContainer.getWidth()/2,rlContainer.getHeight()/2,DEPTHZ,true);
        rotate3D.setDuration(DURATION);
        rotate3D.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(curPage == PAGE_LOGIN){
                    imageView1.setVisibility(View.GONE);
                    imageView2.setVisibility(View.VISIBLE);
                }else{
                    imageView1.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.GONE);
                }

                Rotate3D rotate3D = new Rotate3D(270,360,rlContainer.getWidth()/2,rlContainer.getHeight()/2,DEPTHZ,false);
                rotate3D.setDuration(DURATION);
                rotate3D.setInterpolator(new DecelerateInterpolator());
                rlContainer.startAnimation(rotate3D);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlContainer.startAnimation(rotate3D);
    }

}
