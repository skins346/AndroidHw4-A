package com.example.user.androidhw4_a;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

/**
 Program to playing a mini game
 Author: Kim Young Song.
 E-mail Address: infall346@gmail.com.
 Programming homework #4-A
 Last Changed: June 3, 2016
 */


public class MyView extends View {

    Bitmap bubble1;
    Bitmap bubble2;
    int x=0, y=0;
    int prev[][] = new int[11][11];
    DisplayMetrics dm;

    //get height of screen
    private int getClientHeight() {
        return ((Activity) getContext()).getWindowManager().getDefaultDisplay().getHeight() - getStatusBarHeight();
    }

    private int getStatusBarHeight() {
        int statusHeight = 0;
        int screenSizeType = (getContext().getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK);
        if (screenSizeType != Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            int resourceId = getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusHeight = getContext().getResources().getDimensionPixelSize(resourceId);
            }
        }
        return statusHeight;
    }


    public MyView(Context c) {
        super(c);
        init();

      }
    public MyView(Context c, AttributeSet a) {
        super(c, a);
        init();

    }

    //Initialize bitmaps and divide screen as 10 cells. -> 10 x 10
    public void init()
    {
        dm = this.getResources().getDisplayMetrics();

        bubble1 = BitmapFactory.decodeResource(getResources(), R.drawable.bubble1);
        bubble2 = BitmapFactory.decodeResource(getResources(), R.drawable.bubble2);
        bubble1 = Bitmap.createScaledBitmap(bubble1, (int) (dm.widthPixels * 0.1), (int) (getClientHeight() * 0.1), false);   //divide screen as 10 vells
        bubble2 = Bitmap.createScaledBitmap(bubble2, (int) (dm.widthPixels * 0.1), (int) (getClientHeight() * 0.1), false);
        Paint pnt = new Paint();
    }

    //add touch event handler
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        x = ((int)eventX / (dm.widthPixels/10)) +1;
        y = ((int)eventY / (getClientHeight()/10)) +1 ;

        if (event.getAction() == MotionEvent.ACTION_UP) {

            invalidate();
        }

        return true;
    }

    protected void onDraw(Canvas canvas) {

        Paint pnt = new Paint();


        //draw bubble2 when user click
        for(int i = 1; i<=10; i++)
        {
            for(int j =1; j<=10; j++)
            {

                if(x==i && y==j) {
                    canvas.drawBitmap(bubble2, (i - 1) * (int) (dm.widthPixels * 0.1), (j - 1) * (int) (getClientHeight() * 0.1), pnt);
                    prev[i][j] = 1;
                }
                else {

                    if(prev[i][j] == 1)
                        canvas.drawBitmap(bubble2, (i - 1) * (int) (dm.widthPixels * 0.1), (j - 1) * (int) (getClientHeight() * 0.1), pnt);
                    else
                        canvas.drawBitmap(bubble1, (i - 1) * (int) (dm.widthPixels * 0.1), (j - 1) * (int) (getClientHeight() * 0.1), pnt);
                }
            }
        }
    }

}


