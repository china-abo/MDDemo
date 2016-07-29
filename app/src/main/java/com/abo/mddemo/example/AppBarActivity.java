package com.abo.mddemo.example;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.abo.mddemo.R;
import com.abo.mddemo.widget.DetailFragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by abo on 16/7/29.
 */
public class AppBarActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        collapsingToolbarLayout.setTitle("失控");

        ImageView imageView = (ImageView) findViewById(R.id.appbar_image);
        imageView.setImageResource(R.drawable.book1);

        mViewPager = (ViewPager) findViewById(R.id.appbar_viewpager);
        setupViewPager(mViewPager);

        TabLayout tablayout = (TabLayout) findViewById(R.id.appbar_tablayout);
        tablayout.addTab(tablayout.newTab().setText("简介"));
        tablayout.addTab(tablayout.newTab().setText("作者"));
        tablayout.addTab(tablayout.newTab().setText("目录"));
        tablayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager upViewPager) {
        MyPagerAdapter adapter= new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(getAsst("book_content.txt"))," 内容简介");
        adapter.addFragment(DetailFragment.newInstance(getAsst("book_author.txt")),"作者简介");
        adapter.addFragment(DetailFragment.newInstance(getAsst("book_menu.txt"))," 目录");
        upViewPager.setAdapter(adapter);
    }

    private String getAsst(String fileName){
        AssetManager am = getResources().getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName,AssetManager.ACCESS_BUFFER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Scanner(is).useDelimiter("\\Z").next();
    }

    static class MyPagerAdapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title){
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
