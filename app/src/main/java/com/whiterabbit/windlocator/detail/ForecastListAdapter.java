package com.whiterabbit.windlocator.detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.model.Forecast;
import com.whiterabbit.windlocator.model.ForecastResults;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.rest.WeatherCodes;
import com.whiterabbit.windlocator.utils.WeatherElementUtils;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ViewHolder> {
    private ForecastResults mDataset;
    private WeatherElementUtils mConversionUtils;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.forecast_day)
        public TextView mDayOfWeek;
        @Bind(R.id.forecast_wind_speed)
        public TextView mWindSpeedView;
        @Bind(R.id.forecast_wind_direction)
        public TextView mWindDirectionView;
        @Bind(R.id.forecast_temp_desc)
        public TextView mWeatherTempDescriptionView;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ForecastListAdapter(Context c, ForecastResults data) {
        mContext = c;
        mConversionUtils = new WeatherElementUtils();
        mDataset = data;
    }

    public void updateData(ForecastResults data) {
        mDataset = data;
        notifyDataSetChanged();
    }

    @Override
    public ForecastListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_forecast_element, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Forecast forecast = mDataset.getForecasts()[position];

        Calendar c = Calendar.getInstance();
        c.setTime(forecast.getTime());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        String day = mContext.getResources().getStringArray(R.array.week_days)[dayOfWeek - 1];
        holder.mDayOfWeek.setText(day);

        @SuppressLint("DefaultLocale")
        String tempWeather = String.format("%f %s", forecast.getTemperature(),
                                           WeatherCodes.getWeatherDescFromId(forecast.getWeatherEnum()));

        holder.mWeatherTempDescriptionView.setText(tempWeather);

        String windOrientation = mConversionUtils
                .getWindOrientationFromDegrees(forecast.getWindDegree(), mContext);
        holder.mWindDirectionView.setText(windOrientation);
        holder.mWindSpeedView.setText("" + forecast.getWindSpeed());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset == null) {
            return 0;
        }
        return mDataset.getForecasts().length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
