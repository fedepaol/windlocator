package com.whiterabbit.windlocator.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whiterabbit.windlocator.Constants;
import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.model.Weather;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements DetailView {
    @Bind(R.id.detail_city_name)
    TextView mCityName;

    @Inject
    DetailPresenter mPresenter;

    public static DetailFragment createInstance(Weather w) {
        DetailFragment res = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable(Constants.WEATHER_EXTRA, w);
        return res;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View res = inflater.inflate(R.layout.fragment_weather_detail, container);
        ButterKnife.bind(this, res);
        return res;
    }

    @Override
    public void showWeather(Weather w) {
        mCityName.setText(w.getCityName());
    }
}
