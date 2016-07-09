package com.whiterabbit.windlocator.detail;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whiterabbit.windlocator.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailCurrentTime extends LinearLayout {
    @Bind(R.id.detail_current_date)
    TextView mCurrentDate;

    @Bind(R.id.detail_current_time)
    TextView mCurrentTime;

    public DetailCurrentTime(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ((Activity) getContext()).getLayoutInflater()
                .inflate(R.layout.detail_current_time, this, true);

        ButterKnife.bind(this);
    }

    public DetailCurrentTime(Context context) {
        this(context, null);
    }

    public DetailCurrentTime(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setCurrentDate(String date) {
        mCurrentDate.setText(date);
    }

    public void setCurrentTime(String time) {
        mCurrentTime.setText(time);
    }
}
