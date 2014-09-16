package jcsla.korail;

import java.io.File;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FavoriteStationActivity extends ActionBarActivity {
	
	private static final int DepartureStationSearchActivity = 0;
	private static final int ArrivalStationSearchActivity = 1;
	
	String departureStation;
	String arrivalStation;
	
	File dir;
	File file;
	
	FavoriteStationsAdapter favoriteStationsAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_station);
		
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0493aa")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_favorite_station);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		TextView favoriteStationTitle = (TextView) findViewById(R.id.favoriteStationTitle);
		favoriteStationTitle.setTypeface(Variable.typeface);
		
		Button enrollButton = (Button) findViewById(R.id.enrollButton);
		enrollButton.setTypeface(Variable.typeface);
		
		enrollButton.setOnClickListener(onClickListener);
		
		favoriteStationsAdapter = new FavoriteStationsAdapter(this, android.R.layout.simple_list_item_1, Variable.favoriteStationsList);
		ListView stationListView = (ListView) findViewById(R.id.favorite_station_list);
		stationListView.setAdapter(favoriteStationsAdapter);
	}
	
	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.enrollButton:
				showStationsActivity();
				break;
			}
		}
	};
	
	public void showStationsActivity()
	{
		Intent arrIntent = new Intent(getApplicationContext(), StationSearchActivity.class);
		arrIntent.putExtra("title", "도착역검색");
		startActivityForResult(arrIntent, ArrivalStationSearchActivity);
		
		Intent depIntent = new Intent(getApplicationContext(), StationSearchActivity.class);
		depIntent.putExtra("title", "출발역검색");
		startActivityForResult(depIntent, DepartureStationSearchActivity);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent i)
	{
		switch (requestCode)
		{
		case DepartureStationSearchActivity:
			if (resultCode == Activity.RESULT_OK)
			{
				String station = i.getExtras().getString("station");
				departureStation = station;
			}
			break;
		case ArrivalStationSearchActivity:
			if (resultCode == Activity.RESULT_OK)
			{
				String station = i.getExtras().getString("station");
				arrivalStation = station;
				
				saveFavoriteStationsFile();
			}
			break;
		}
	}
	
	public void saveFavoriteStationsFile()
	{	
		String file_content = departureStation + " - " + arrivalStation + "\n";
		FileHandler.writeFile(file, file_content.getBytes());
		
		// 리스트뷰 갱신.
		Variable.favoriteStationsList.add(file_content);
		favoriteStationsAdapter.notifyDataSetChanged();
	}
}
