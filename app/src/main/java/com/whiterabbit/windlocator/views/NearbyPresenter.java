package com.whiterabbit.windlocator.views;


public class NearbyPresenter implements NearbyPresenterContract {
    private NearbyViewContract mView;

    public NearbyPresenter(NearbyViewContract view) {
        mView = view;
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void update() {
        mView.setProgress(true);
    }
}
