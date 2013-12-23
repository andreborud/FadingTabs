package com.andreborud.fadingtabs;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	SectionsPagerAdapter mSectionsPagerAdapter;
    private CustomTabView tab0, tab1, tab2, tab3;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		
		tab0 = new CustomTabView(this, mSectionsPagerAdapter.getPageTitle(0).toString(), getResources().getDrawable(R.drawable.tab));
		tab1 = new CustomTabView(this, mSectionsPagerAdapter.getPageTitle(1).toString(), getResources().getDrawable(R.drawable.tab));
		tab2 = new CustomTabView(this, mSectionsPagerAdapter.getPageTitle(2).toString(), getResources().getDrawable(R.drawable.tab));
		tab3 = new CustomTabView(this, mSectionsPagerAdapter.getPageTitle(3).toString(), getResources().getDrawable(R.drawable.tab));
		
		tab0.setTheAlpha(255);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				int alphaCurrent = (int) (255 - (128*Math.abs(positionOffset)));
				int alphaNext = (int) (128 + (128*Math.abs(positionOffset)));
				if (positionOffset != 0) {
					switch(position) {
						case 0: 
							tab0.setTheAlpha(alphaCurrent);
							tab1.setTheAlpha(alphaNext);
							break;
						case 1:
							tab1.setTheAlpha(alphaCurrent);
							tab2.setTheAlpha(alphaNext);
							break;
						case 2:
							tab2.setTheAlpha(alphaCurrent);
							tab3.setTheAlpha(alphaNext);
							break;
					}
				}
			}
		});

		actionBar.addTab(actionBar.newTab().setCustomView(tab0).setTabListener(this).setTag(0));
		actionBar.addTab(actionBar.newTab().setCustomView(tab1).setTabListener(this).setTag(1));
		actionBar.addTab(actionBar.newTab().setCustomView(tab2).setTabListener(this).setTag(2));
		actionBar.addTab(actionBar.newTab().setCustomView(tab3).setTabListener(this).setTag(3));
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
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
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getString(R.string.title_section1);
			case 1:
				return getString(R.string.title_section2);
			case 2:
				return getString(R.string.title_section3);
			case 3:
				return getString(R.string.title_section4);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
