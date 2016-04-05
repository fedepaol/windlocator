package com.whiterabbit.windlocator.nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.model.Weather;
import com.whiterabbit.windlocator.model.WeatherResults;
import com.whiterabbit.windlocator.weatherclient.WeatherCodes;
import com.whiterabbit.windlocator.utils.WeatherElementUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder> {
    private WeatherResults mDataset;
    private WeatherElementUtils mConversionUtils;
    private Context mContext;
    private ListElemClick mClickListener;

    public interface ListElemClick {
        void onViewClicked(long id);

        void onPreferredClicked(long id);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.time)
        public TextView mTimeView;
        @Bind(R.id.city_name)
        public TextView mNameView;
        @Bind(R.id.weather_description)
        public TextView mWeatherDescriptionView;
        @Bind(R.id.temperature)
        public TextView mTemperatureView;
        @Bind(R.id.wind_direction)
        public TextView mWindDirectionView;
        @Bind(R.id.wind_speed)
        public TextView mWindSpeedView;
        @Bind(R.id.weather_icon)
        public ImageView mWeatherIcon;

        public ListElemClick mListener;
        public long _id;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
            v.setOnClickListener(this);
            //mPreferredIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            /*
            if (v.getId() == R.id.weather_list_preferred) {
                mListener.onPreferredClicked(_id);
            } else {
                mListener.onViewClicked(_id);
            } */
        }

    }

    public WeatherListAdapter(ListElemClick listener, Context c) {
        mContext = c;
        mConversionUtils = new WeatherElementUtils();
        mClickListener = listener;
    }

    public void updateData(WeatherResults data) {
        mDataset = data;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public WeatherListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_elem, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Weather w = mDataset.getWeathers()[position];
        holder.mNameView.setText(w.getCityName());

        holder.mTimeView.setText(mConversionUtils.timeToStringTime(w.getTime().getTime()));
        holder.mWeatherDescriptionView.setText(WeatherCodes.getWeatherDescFromId(w.getWeatherEnum()));
        holder.mTemperatureView.setText("" + w.getTemperature());

        String windOrientation = mConversionUtils
                .getWindOrientationFromDegrees(w.getWindDegree(), mContext);
        holder.mWindDirectionView.setText(windOrientation);
        holder.mWindSpeedView.setText("" + w.getWindSpeed());
        holder._id = w.getId();
        holder.mListener = mClickListener;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (mDataset == null) {
            return 0;
        }
        return mDataset.getWeathers().length;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
