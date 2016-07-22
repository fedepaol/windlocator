package com.whiterabbit.windlocator;

import com.whiterabbit.windlocator.detail.DetailModule;
import com.whiterabbit.windlocator.detail.DetailView;

/**
 * Created by fedepaol on 14/07/16.
 */
public class TestWindLocatorApp extends WindLocatorApp {
    private DetailModule mDetailModule;

    @Override
    public DetailModule getDetailModule(DetailView view) {
        if (mDetailModule != null) {
            return mDetailModule;
        }
        return super.getDetailModule(view);
    }

    public void setDetailModule(DetailModule m) {
        this.mDetailModule = m;
    }
}
