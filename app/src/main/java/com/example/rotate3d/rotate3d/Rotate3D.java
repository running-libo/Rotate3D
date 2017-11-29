package com.example.rotate3d.rotate3d;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by libo on 2017/11/2.
 */

public class Rotate3D extends Animation {

    // 开始角度
    private final float mFromDegrees;
    // 结束角度
    private final float mToDegrees;
    // X轴中心点
    private final float mCenterX;
    // Y轴中心点
    private final float mCenterY;
    // Z轴中心点
    private final float mDepthZ;
    //是否需要扭曲
    private final boolean mReverse;
    //摄像头
    private Camera mCamera;

    public Rotate3D(float fromDegrees, float toDegrees,
                    float centerX, float centerY,
                    float depthZ, boolean reverse) {
        mFromDegrees = fromDegrees;
        mToDegrees = toDegrees;
        mCenterX = centerX;
        mCenterY = centerY;
        mDepthZ = depthZ;
        mReverse = reverse;
    }

    @Override
    public void initialize(int width, int height,
                           int  parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth,
                parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        // 生成中间角度
        float degrees = fromDegrees + ((mToDegrees - fromDegrees) * interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();//取得当前矩阵
        camera.save();
        if (mReverse) {
            camera.translate(0.0f, 0.0f, mDepthZ * interpolatedTime);
        } else {
            camera.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime));
        }
        camera.rotateY(degrees);//翻转
        camera.getMatrix(matrix);// 取得变换后的矩阵
        camera.restore();
        matrix.preTranslate(-centerX, -centerY);
        matrix.postTranslate(centerX, centerY);
    }
}
