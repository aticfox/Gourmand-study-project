package com.artie.gourmand.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.artie.gourmand.R;
import com.artie.gourmand.fragment.CameraFragment;
import com.artie.gourmand.fragment.GalleryFragment;

public class TakePhotoActivity extends AppCompatActivity {

    ViewPager mViewPager;
    TabLayout mTabLayout;

    static final int NUMBER_TAKE_PHOTO_FRAGMENT_PAGE = 2;
    static final int FRAGMENT_CAMERA = 0;
    static final int FRAGMENT_GALLERY = 1;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, TakePhotoActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        initInstances();
    }

    private void initInstances() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(fragmentStatePagerAdapter);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    FragmentStatePagerAdapter fragmentStatePagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case FRAGMENT_CAMERA:
                    return CameraFragment.newInstance();
                case FRAGMENT_GALLERY:
                    return GalleryFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUMBER_TAKE_PHOTO_FRAGMENT_PAGE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case FRAGMENT_CAMERA:
                    return "Photo";
                case FRAGMENT_GALLERY:
                    return "Gallery";
                default:
                    return "";
            }
        }
    };

}
