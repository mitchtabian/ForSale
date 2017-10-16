package codingwithmitch.com.forsale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.SectionIndexer;
import android.widget.TableLayout;

import codingwithmitch.com.forsale.util.SectionsPagerAdapter;


public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";

    //widgets
    private TabLayout mTabLayout;
    public ViewPager mViewPager;

    //vars
    public SectionsPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mViewPager  = (ViewPager) findViewById(R.id.viewpager_container);

        setupViewPager();
    }

    private void setupViewPager(){
        mPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}















