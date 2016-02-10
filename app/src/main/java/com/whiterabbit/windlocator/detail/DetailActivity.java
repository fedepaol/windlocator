package com.whiterabbit.windlocator.detail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.whiterabbit.windlocator.Constants;
import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.model.Weather;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        Weather w = i.getParcelableExtra(Constants.WEATHER_EXTRA);

        if (getFragmentManager().findFragmentById(R.id.detail_fragment) == null) {
            Fragment f = DetailFragment.createInstance(w);
            getSupportFragmentManager().beginTransaction().replace(R.id.detail_fragment, f)
                    .commit();
        }
    }
}
