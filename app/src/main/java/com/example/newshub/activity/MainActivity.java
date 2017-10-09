package com.example.newshub.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.newshub.*;
import com.example.newshub.adapter.NewsCategoryPagerAdapter;
import com.example.newshub.fragment.AFragment;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    CoordinatorLayout rootLayout;
    ViewPager viewPager;
    NewsCategoryPagerAdapter adapter;
    int currentPosition;
    String[] news;
    MenuItem menuSetting1;
    MenuItem menuSetting2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initTabBar();
        initInstances();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.contentContainer, AFragment.newInstance())
                .commit();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initTabBar() {
        Resources res = getResources();
        news = res.getStringArray(R.array.news_agency);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new NewsCategoryPagerAdapter(getSupportFragmentManager(), news);
        currentPosition = tabLayout.getSelectedTabPosition();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initInstances() {
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menuSetting1 = menu.findItem(R.id.action_settings_1);
        menuSetting2 = menu.findItem(R.id.action_settings_2);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intentAbout = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intentAbout);
            return true;
        } else {
            switch(Locale.getDefault().getDisplayLanguage()) {
                case "English":
                    switch (item.getItemId()) {
                        case R.id.action_settings_1:
                            setLocale("th");
                            return true;
                        case R.id.action_settings_2:
                            setLocale("vi");
                            return true;
                    }
                break;
                case "ไทย":
                    switch (item.getItemId()) {
                        case R.id.action_settings_1:
                            setLocale("en");
                            return true;
                        case R.id.action_settings_2:
                            setLocale("vi");
                            return true;
                    }
                    break;
                case "Tiếng Việt":
                    switch (item.getItemId()) {
                        case R.id.action_settings_1:
                            setLocale("en");
                            return true;
                        case R.id.action_settings_2:
                            setLocale("th");
                            return true;
                    }
                    break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String langCode = sharedPreferences.getString("PREF_LIST", "th");
        Configuration configuration = new Configuration();
        configuration.locale = new Locale(langCode);
        getResources().updateConfiguration(configuration, null);
    }

    public static String POSITION = "POSITION";

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        finish();
    }
}
