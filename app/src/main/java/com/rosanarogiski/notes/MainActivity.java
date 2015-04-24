package com.rosanarogiski.notes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import com.rosanarogiski.notes.fragment.NoteListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauricio on 4/21/15.
 */
public class MainActivity extends ActionBarActivity {

    private ViewPager viewPager;
    private HomePagerAdapter homePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Home");

        viewPager = (ViewPager) findViewById(R.id.pager);
        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(homePagerAdapter);
    }

    class HomePagerAdapter extends FragmentStatePagerAdapter {

        String[] TITLES = {"All", "Recent", "Uploads", "Downloads"};

        List<Fragment> fragmentList;

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);

            fragmentList = new ArrayList<Fragment>();

            fragmentList.add(new NoteListFragment(NoteListFragment.ALL));
            fragmentList.add(new NoteListFragment(NoteListFragment.RECENT));
            fragmentList.add(new NoteListFragment(NoteListFragment.UPLOAD));
            fragmentList.add(new NoteListFragment(NoteListFragment.DOWNLOAD));
        }

        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        homePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(homePagerAdapter);
    }
}
