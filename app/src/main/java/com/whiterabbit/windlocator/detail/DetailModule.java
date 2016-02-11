package com.whiterabbit.windlocator.detail;



import com.whiterabbit.windlocator.mainactivity.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {
    private DetailView mView;
    public DetailModule(DetailView view) {
        mView = view;
    }

    @Provides
    public DetailView provideDetailView() {
        return mView;
    }


    @Provides
    public DetailPresenter provideDetailPresenter(DetailView v) {
        return new DetailPresenterImpl(v);
    }
}
