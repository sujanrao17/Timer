package main.coderindigo.com.countdown.activity.helper;

/**
 * Created by sujana on 12/15/2015.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;




public class PageAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();
    public PageAdapter(FragmentManager fm) {
        super(fm);

    }

    public void addFragment(Fragment fragment,
                            String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);

    }

    public Fragment getFragment(int pageIndex) {
        return mFragmentList.get(pageIndex);
    }
    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}