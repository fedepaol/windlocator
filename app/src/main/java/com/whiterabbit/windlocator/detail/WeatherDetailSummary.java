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
    @Bind(R.id.detail_speed)
    TextView mSpeed;

    @Bind(R.id.detail_weather_icon)
    ImageView mWeatherIcon;

    @Bind(R.id.detail_temperature)
    TextView mTemperature;

    @Bind(R.id.detail_speed_unit)
    TextView mSpeedUnit;

    @Bind(R.id.detail_weather_desc)
    TextView mWeatherDescription;




    private ImageView mCircle;
    private TextView mDirection;

    public WeatherDetailSummary(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.weather_detail_summary, this, true);
        ButterKnife.bind(this, this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
