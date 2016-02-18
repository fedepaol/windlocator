package com.whiterabbit.windlocator.detail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whiterabbit.windlocator.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherDetailSummary extends ViewGroup {
    @Bind(R.id.detail_wind_compass)
    ImageView mWindIndicator;

    @Bind(R.id.detail_weather_direction)
    TextView mDirection;

    double direction = 0;
    final static int NORTH = 0;

    public WeatherDetailSummary(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherDetailSummary(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.weather_detail_summary, this, true);
        ButterKnife.bind(this, this);
        direction = NORTH;
        mDirection.setText("NE");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = getDefaultSize(0, widthMeasureSpec);
        int heightSize = getDefaultSize(0, heightMeasureSpec);


        measureChild(mDirection, widthMeasureSpec, heightMeasureSpec);

        int worstSize = Math.max(mDirection.getMeasuredWidth(), mDirection.getMeasuredHeight());
        int circleRadius = Math.min(widthSize, heightSize) - worstSize;

        int circleSpec = MeasureSpec.makeMeasureSpec(circleRadius, MeasureSpec.EXACTLY);
        measureChild(mWindIndicator, circleSpec, circleSpec);

        int size = circleRadius + worstSize + getPaddingLeft() + getPaddingRight();
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onLayout(boolean changed, int l, int r, int t, int b) {
        int textSize = Math.max(mDirection.getMeasuredWidth(), mDirection.getMeasuredHeight());
        int windLeft = getPaddingLeft() + textSize;
        int windTop = getPaddingTop() + textSize;
        mWindIndicator.layout(windLeft, windLeft + mWindIndicator.getMeasuredWidth(),
                              windTop, windTop + mWindIndicator.getMeasuredHeight());


        int circleCenterX = windLeft + mWindIndicator.getMeasuredWidth() / 2;
        int circleCenterY = windTop + mWindIndicator.getMeasuredWidth() / 2;
        int circleRadius = mWindIndicator.getMeasuredWidth() / 2;
        int directionX = (int) (circleCenterX + Math.cos(direction) * circleRadius);
        int directionY = (int) (circleCenterY + Math.sin(direction) * circleRadius);
        mDirection.layout(directionX - mDirection.getMeasuredWidth() / 2, directionX + mDirection.getMeasuredWidth(),
                          directionY - mDirection.getMeasuredHeight(), directionY + mDirection.getMeasuredHeight());
    }


    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
