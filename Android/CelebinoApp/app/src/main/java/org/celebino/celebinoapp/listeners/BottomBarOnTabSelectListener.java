package org.celebino.celebinoapp.listeners;

import android.support.annotation.IdRes;

import com.roughike.bottombar.OnTabSelectListener;

import org.celebino.celebinoapp.R;
import org.celebino.celebinoapp.activities.MainActivity;

/**
 * Created by henri on 15/06/2017.
 */

public class BottomBarOnTabSelectListener implements OnTabSelectListener {
    MainActivity mainActivity;

    public BottomBarOnTabSelectListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_air:
                mainActivity.airfragmet();
                break;
            case R.id.tab_garden:
                mainActivity.gardenfragmet();
                break;
            case R.id.tab_home:
                mainActivity.homefragmet();
                break;
        }
    }
}
