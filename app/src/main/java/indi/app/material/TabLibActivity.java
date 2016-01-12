package indi.app.material;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//import indi.app.material.MainActivity;
//import indi.app.material.R;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class TabLibActivity extends AppCompatActivity implements MaterialTabListener {
    private Toolbar toolbar;
    private MaterialTabHost materialTabHost;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_lib);
        toolbar = (Toolbar) findViewById(R.id.app_bar);

        if (null != toolbar) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0); // or other
        }
        materialTabHost = (MaterialTabHost) findViewById(R.id.materialTabHost);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                              @Override
                                              public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                                  Log.d("gaj", "onPageSelected" +position);
                                                  materialTabHost.setSelectedNavigationItem(position);
                                              }

                                              @Override
                                              public void onPageSelected(int position) {
                                                  // This space for rent
                                                  Log.d("gaj", "onPageSelected" +position);
                                                  materialTabHost.setSelectedNavigationItem(position);
                                              }

                                              @Override
                                              public void onPageScrollStateChanged(int state) {

                                              }

                                          }
        );
        for (int cnt = 0;cnt<adapter.getCount();cnt++)
        {
            materialTabHost.addTab(
                    materialTabHost.createInteractiveTab(adapter.getPageTitle(cnt)).setTabListener(this));

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.home)
        {
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }

    class ViewPageAdapter extends FragmentStatePagerAdapter {
        String[] tabs;
        int icons[] = {R.mipmap.ic_action_dsc_0645, R.mipmap.ic_action_notification_drive_eta, R.mipmap.ic_action_social_domain};

        public ViewPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //  MainActivity.MyFragment myFragment = MainActivity.MyFragment.getInstanceOf(position);

            return MyFragment.getInstanceOf(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
//        Drawable drawable = getResources().getDrawable(icons[position]);
//        drawable.setBounds(0, 0, 36, 36);
//        ImageSpan imageSpan = new ImageSpan(drawable);
//        SpannableString spannableString = new SpannableString("GAJA");
//        spannableString.setSpan(imageSpan, 0, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return spannableString;
            Log.d("gaj", "TabLib" +position);
            return getResources().getStringArray(R.array.tabs)[position];
        }
        private Drawable getIcon(int position)
        {
            return getResources().getDrawable(icons[position]);
        }
        @Override
        public int getCount() {
            return 6;
        }
    }
}


