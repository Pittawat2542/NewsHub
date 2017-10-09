package com.example.newshub.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.newshub.fragment.HFragment;
import com.example.newshub.fragment.BFragment;
import com.example.newshub.fragment.EFragment;
import com.example.newshub.fragment.DFragment;
import com.example.newshub.fragment.GFragment;
import com.example.newshub.fragment.AFragment;
import com.example.newshub.fragment.FFragment;
import com.example.newshub.fragment.CFragment;

/**
 * Created by pitta on 16/3/2559.
 */
public class NewsCategoryPagerAdapter extends FragmentStatePagerAdapter {

    private String tabTitles[];

    public NewsCategoryPagerAdapter(FragmentManager fm, String[] news) {
        super(fm);
        this.tabTitles = news;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new AFragment();
                break;
            case 1:
                fragment = new BFragment();
                break;
            case 2:
                fragment = new CFragment();
                break;
            case 3:
                fragment = new DFragment();
                break;
            case 4:
                fragment = new EFragment();
                break;
            case 5:
                fragment = new FFragment();
                break;
            case 6:
                fragment = new GFragment();
                break;
            case 7:
                fragment = new HFragment();
                break;
            default:
                fragment = new AFragment();
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }
}
