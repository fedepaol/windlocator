package com.whiterabbit.windlocator.views;

import android.app.SearchManager;
import android.content.Context;
import android.database.MatrixCursor;
import android.location.Address;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding.support.v7.widget.RxSearchView;
import com.whiterabbit.windlocator.R;
import com.whiterabbit.windlocator.WindLocatorApp;
import com.whiterabbit.windlocator.nearby.NearbyListFragment;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements MainView {
    @Bind(R.id.tabs)
    TabLayout mTabs;

    @Bind(R.id.pager)
    ViewPager mPager;

    @Inject
    MainPresenter mPresenter;

    private SearchView mSearchView;
    private MenuItem mSearchItem;

    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        WindLocatorApp app = (WindLocatorApp) getApplication();

        DaggerMainActivityComponent.builder()
                .applicationComponent(app.getComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);

        mAdapter = new MyAdapter(getSupportFragmentManager(), getApplicationContext());
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs));
        mPager.setAdapter(mAdapter);
        mTabs.setTabsFromPagerAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        if (!mSearchView.isIconified()) {
            MenuItemCompat.collapseActionView(mSearchItem);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchItem = menu.findItem(R.id.search);
        mSearchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        mPresenter.setAddressObservable(RxSearchView.queryTextChanges(mSearchView)
                .map(CharSequence::toString)
                .debounce(400, TimeUnit.MILLISECONDS));

        mPresenter.setSearchViewObservable(RxSearchView.queryTextChangeEvents(mSearchView));


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    private CursorAdapter getSuggestionsFromList(List<Address> locations) {
        String[] columnNames = {"_id","text"};
        MatrixCursor cursor = new MatrixCursor(columnNames);
        String[] temp = new String[2];
        int id = 0;
        for (Address item : locations){
            temp[0] = Integer.toString(id++);
            temp[1] = item.getFeatureName() + " " + item.getCountryName();
            cursor.addRow(temp);
        }
        String[] from = {"text"};
        int[] to = {android.R.id.text1};
        return new SimpleCursorAdapter(getApplicationContext(),
                                        android.R.layout.simple_list_item_1,
                                        cursor,
                                        from,
                                        to);
    }

    @Override
    public void showAddresses(List<Address> l) {
        CursorAdapter adapter = getSuggestionsFromList(l);
        mSearchView.setSuggestionsAdapter(adapter);
    }

    public static class MyAdapter extends FragmentPagerAdapter {
        private Context mContext;
        public MyAdapter(FragmentManager fm, Context c) {
            super(fm);
            mContext = c;
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new NearbyListFragment();
                case 1:
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return mContext.getString(R.string.nearby);
                case 1:
                    return mContext.getString(R.string.favorites);
            }
            return "";
        }
    }

}

