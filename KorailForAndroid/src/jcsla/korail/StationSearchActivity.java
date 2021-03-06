package jcsla.korail;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class StationSearchActivity extends ActionBarActivity
{
	private ArrayList<String> stationList;
	StationsAdapter stationListAdapter;
	Bundle b;
	Intent i;
	
	private String title;
	EditText searchEditText;
	
	private AdView adView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station_search);
		
		//인텐트에서 title이라는 이름으로 액션바 이름넘겨받음.
		title = getIntent().getStringExtra("title");
		
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0493aa")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_station_search);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		TextView stationSearchTitle = (TextView) findViewById(R.id.stationSearchTitle);
		stationSearchTitle.setText(title);
		stationSearchTitle.setTypeface(Variable.typeface);
		
		searchEditText = (EditText) findViewById(R.id.search_edit_text);
		searchEditText.setHint(title);
		searchEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				stationListAdapter.filter(s.toString());
			}

			@Override
			public void afterTextChanged(Editable s) {
				
			}
			
		});

		stationList = new ArrayList<String>();
		for(int i=0 ; i<Variable.stationList.size() ; i++)
			stationList.add(Variable.stationList.get(i).getStnNm());
//		Iterator<String> i = Station.t_name_number_stations.keySet().iterator();
//		while (i.hasNext())
//		{
//			String key = (String) i.next();
//			stationList.add(key);
//		}
		
		stationListAdapter = new StationsAdapter(this, android.R.layout.simple_list_item_1, stationList);
		
		ListView stationListView = (ListView) findViewById(R.id.station_list);
		stationListView.setAdapter(stationListAdapter);
		stationListView.setTextFilterEnabled(true);
		stationListView.setOnItemClickListener(mItemClickListener);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.findViewById(R.id.station_search_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	@Override
	public void setContentView(int viewId)
	{
		View view = LayoutInflater.from(this).inflate(viewId, null);
		ViewGroup group = (ViewGroup) view;
		int childCnt = group.getChildCount();
		for (int i = 0; i < childCnt; i++)
		{
			View v = group.getChildAt(i);
			if (v instanceof TextView)
			{
				((TextView) v).setTypeface(Variable.typeface);
			}
		}
		super.setContentView(view);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		if (adView != null) {
			adView.resume();
		}
	}

	@Override
	public void onPause() {
		if (adView != null) {
			adView.pause();
		}
		super.onPause();
	}

	/** Called before the activity is destroyed. */
	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
	
	private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener()
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id)
		{
			b = new Bundle();
			i = new Intent();

			b.putString("station", ((TextView) view).getText().toString());
			i.putExtras(b);
			setResult(RESULT_OK, i);
			finish();
		}
	};
}