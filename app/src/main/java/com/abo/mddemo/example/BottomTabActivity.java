package com.abo.mddemo.example;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abo.mddemo.R;

/**
 * Created by abo on 16/8/1.
 */
public class BottomTabActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomtab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mViewPager = (ViewPager) findViewById(R.id.bottom_viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.bottom_tablayout);

        SampleFragmentPagerAdapter adapter = new SampleFragmentPagerAdapter(getSupportFragmentManager(),this);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);

        for(int i = 0;i < mTabLayout.getTabCount();i++){
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if(tab != null){
                tab.setCustomView(adapter.getTabView(i));
            }
        }

        mViewPager.setCurrentItem(1);
    }

    public class  SampleFragmentPagerAdapter extends FragmentPagerAdapter{

        final int PAGE_COUNT = 4;
        private String tabTitle[] = new String[]{"Tab1","Tab2","Tab3","Tab4"};
        private Context mContext;

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.mContext = context;
        }

        public View getTabView(int positon){
            View v = LayoutInflater.from(mContext).inflate(R.layout.custom_tab,null);
            TextView tv = (TextView) v.findViewById(R.id.tab_text);
            tv.setText(tabTitle[positon]);
            ImageView img = (ImageView) v.findViewById(R.id.tab_image);
            return v;
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstace(position+1);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitle[position];
        }
    }

    public static class PageFragment extends Fragment{

        public static final String ARG_PAGE = "ARG_PAGE";
        private int mPage;
        /*
         Activity重新创建时，会重新构建它所管理的Fragment，
         原先的Fragment的字段值将会全部丢失，
         但是通过Fragment.setArguments(Bundle bundle)方法设置的bundle会保留下来
         */
        public static PageFragment newInstace(int page){
            Bundle args = new Bundle();
            args.putInt(ARG_PAGE,page);
            PageFragment fragment= new PageFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            Bundle bundle = getArguments();
            mPage = bundle.getInt(ARG_PAGE);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_page,null);
            TextView textView= (TextView) view.findViewById(R.id.frag_page_text);
            textView.setText("Fragment"+mPage);
            return view;
        }
    }
}
