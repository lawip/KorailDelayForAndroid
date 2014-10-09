package jcsla.korail;

import com.urqa.clientinterface.URQAController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	File dir;
	File favoriteStationsFile;
	File historyFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		URQAController.InitializeAndStartSession(getApplicationContext(), "7ADED139");

		setContentView(R.layout.activity_main);
		loadTypeface();

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0493aa")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_main);
		getActionBar().setDisplayShowHomeEnabled(false);
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color.parseColor("#31333c")));

		TextView appTitle = (TextView) findViewById(R.id.appTitle);
		appTitle.setTypeface(Variable.typeface);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		
		new StationJsonParser().execute();
		/*
		dir = FileHandler.makeDirectory(Variable.DIRECTORY_NAME);
		String favoriteStationsFilePath = Variable.DIRECTORY_NAME + Variable.FAVORITE_STATIONS_FILE;
		favoriteStationsFile = FileHandler.makeFile(dir, favoriteStationsFilePath);

		String historyFilePath = Variable.DIRECTORY_NAME + Variable.HISTORY_FILE;
		historyFile = FileHandler.makeFile(dir, historyFilePath);

		Variable.favoriteStationList = FileHandler.readFile(favoriteStationsFile);
		Variable.tempHistoryList = FileHandler.readFile(historyFile);
		deleteNotTodayHistory(); // 오늘 날짜 아니면 삭제

		insertHistoryList();
		*/
	}

	private void deleteNotTodayHistory() {
		SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyyMMdd",
				Locale.KOREA);
		Date date = new Date();
		String currentDate = SimpleDateFormat.format(date);

		for (int i = 0; i < Variable.tempHistoryList.size(); i++) {
			String line = Variable.tempHistoryList.get(i);
			StringTokenizer st = new StringTokenizer(line, "/");
			String historyDate = st.nextToken().trim();

			if (historyDate.compareTo(currentDate) != 0) {
				Variable.tempHistoryList.remove(i);
				remakeHistoryFile();
			}
		}
	}

	private void remakeHistoryFile() {
		FileHandler.deleteFile(historyFile);
		String historyFilePath = Variable.DIRECTORY_NAME
				+ Variable.HISTORY_FILE;
		historyFile = FileHandler.makeFile(dir, historyFilePath);

		for (int i = 0; i < Variable.tempHistoryList.size(); i++) {
			String file_content = Variable.tempHistoryList.get(i) + "\n";
			FileHandler.writeFile(historyFile, file_content.getBytes());
		}
	}

	private void insertHistoryList() {
		ArrayList<Train> temp = new ArrayList<Train>();
		
		Variable.historyList.clear();

		for (int i = 0; i < Variable.tempHistoryList.size(); i++) {
			StringTokenizer st = new StringTokenizer(Variable.tempHistoryList.get(i), "/");

			String depDate = st.nextToken().trim();
			String type = st.nextToken().trim();
			String number = st.nextToken().trim();
			String depCode = st.nextToken().trim();
			String depTime = st.nextToken().trim();
			String arrCode = st.nextToken().trim();
			String arrDate = st.nextToken().trim();
			String arrTime = st.nextToken().trim();
			
			Train t = new Train(type, number, depCode, depDate, depTime, arrCode, arrDate, arrTime, "", "");
			temp.add(t);
		}
		
		if(temp.size() != 0) {
			// 열차번호 모으다가 한번에 쿼리 날리기.
			try {
				ArrayList<Train> result = new HistoryJsonParser(temp).execute().get();
				Variable.historyList.addAll(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {

	}

	private void loadTypeface() {
		if (Variable.typeface == null)
			Variable.typeface = Typeface.createFromAsset(getAssets(),
					Variable.TYPEFACE_NAME);
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			// return PlaceholderFragment.newInstance(position + 1);
			Fragment fragment = null;
			switch (position) {
			case 0:
				fragment = new SearchFragment();
				break;
			case 1:
				fragment = new HistoryFragment();
				break;
			case 2:
				fragment = new SettingsFragment();
				break;
			}

			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

}
