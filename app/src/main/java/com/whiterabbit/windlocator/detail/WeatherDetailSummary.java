package com.whiterabbit.windlocator.detail;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whiterabbit.windlocator.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDetailSummary extends ViewGroup {
    @Bind(R.id.detail_wind_compass)
    ImageView mWindCompass;

    @Bind(R.id.detail_weather_direction)
    TextView mTextDirection;

    ViewGroup mContent;

    double direction = Math.PI / 4.0;
    final static double NORTH = Math.PI / 4 * 7;

    public WeatherDetailSummary(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherDetailSummary(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.weather_detail_summary, this, true);
        ButterKnife.bind(this, this);
        direction = NORTH;
        mTextDirection.setText("NE");
    }

    private int getTextPad() {
       return (int) getResources().getDisplayMetrics().density * 25;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mContent == null) {
            mContent = (ViewGroup) findViewById(R.id.detail_content);
        }
        int widthSize = getDefaultSize(0, widthMeasureSpec);
        int heightSize = getDefaultSize(0, heightMeasureSpec);
        int layoutSize = Math.min(widthSize, heightSize);

        measureChild(mTextDirection, widthMeasureSpec, heightMeasureSpec);

        int textSize = Math.max(mTextDirection.getMeasuredWidth(), mTextDirection.getMeasuredHeight());

        int textPad = getTextPad();
        int circleDiameter = layoutSize - 2 * (textSize + textPad);
        int circleSpec = MeasureSpec.makeMeasureSpec(circleDiameter, MeasureSpec.EXACTLY);
        measureChild(mWindCompass, circleSpec, circleSpec);

        int contentSpec = MeasureSpec.makeMeasureSpec((int) (circleDiameter * 0.7), MeasureSpec.EXACTLY);
        measureChild(mContent, contentSpec, contentSpec);

        setMeasuredDimension(layoutSize, layoutSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int textSize = Math.max(mTextDirection.getMeasuredWidth(), mTextDirection.getMeasuredHeight());
        int windLeft = getPaddingLeft() + textSize + getTextPad();
        int windTop = getPaddingTop() + textSize + getTextPad();
        mWindCompass.layout(windLeft, windTop, windLeft + mWindCompass.getMeasuredWidth(),
                              windTop + mWindCompass.getMeasuredHeight());


        int circleCenterX = windLeft + mWindCompass.getMeasuredWidth() / 2;
        int circleCenterY = windTop + mWindCompass.getMeasuredWidth() / 2;
        int circleRadius = mWindCompass.getMeasuredWidth() / 2 + getTextPad();
        int directionX = (int) (circleCenterX + Math.cos(direction) * circleRadius);
        int directionY = (int) (circleCenterY + Math.sin(direction) * circleRadius);

        mTextDirection.layout(directionX - mTextDirection.getMeasuredWidth() / 2,
                          directionY - mTextDirection.getMeasuredHeight() / 2,
                          directionX + mTextDirection.getMeasuredWidth() / 2,
                          directionY + mTextDirection.getMeasuredHeight() / 2);


        int contentLeft = (int) (windLeft + 0.15 * mWindCompass.getMeasuredWidth());
        int contentRight = contentLeft + mContent.getMeasuredWidth();
        int contentTop = windTop + (int) (0.15 * mWindCompass.getMeasuredHeight());
        int contentBottom = contentTop + mContent.getMeasuredHeight();
        mContent.layout(contentLeft, contentTop, contentRight, contentBottom);
    }

    public void setWindDirection(double degree, String text) {
        mWindCompass.setRotation((int) degree - 45); // -45 because the pic I got is rotated by 45
        direction = degree / 360.0 * ( 2 * Math.PI) - Math.PI / 2;
        mTextDirection.setText(text);
        invalidate();
    }


    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
