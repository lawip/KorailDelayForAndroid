package jcsla.korail;

import java.io.File;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FavoriteStationActivity extends ActionBarActivity implements OnItemClickListener {
	
	private static final int DepartureStationSearchActivity = 0;
	private static final int ArrivalStationSearchActivity = 1;
	
	String departureStation;
	String arrivalStation;
	
	File dir;
	File favoriteStationsFile;
	
	FavoriteStationsAdapter favoriteStationsAdapter;
	
	private AdView adView;
	
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
		stationListView.setOnItemClickListener(this);
		
		dir = FileHandler.makeDirectory(Variable.DIRECTORY_NAME);
		String favoriteStationsFilePath = Variable.DIRECTORY_NAME + Variable.FAVORITE_STATIONS_FILE;
		favoriteStationsFile = FileHandler.makeFile(dir, favoriteStationsFilePath);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.findViewById(R.id.favorite_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
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
		FileHandler.writeFile(favoriteStationsFile, file_content.getBytes());
		
		// 리스트뷰 갱신.
		Variable.favoriteStationsList.add(file_content.trim());
		favoriteStationsAdapter.notifyDataSetChanged();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView tv = (TextView) view.findViewById(R.id.stationItem);
		final String clickedItem = tv.getText().toString();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(clickedItem + " 구간을 삭제하시겠습니까?").setCancelable(false)
		.setPositiveButton("확인",
				new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
						// 'YES'
						// 삭제
				    	// 리스트뷰 갱신
				    	// alert 띄워주기
				    	for(int i=0 ; i<Variable.favoriteStationsList.size() ; i++) {
				    		if(clickedItem.compareTo(Variable.favoriteStationsList.get(i)) == 0) {
				    			Variable.favoriteStationsList.remove(i);
				    			removeFavoriteStationsFile();
				    			favoriteStationsAdapter.notifyDataSetChanged();
				    		}
				    	}
				    	return;
				    }
				})
		.setNegativeButton("취소",
				new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        // 'No'
				    	return;
				    }
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void removeFavoriteStationsFile()
	{
		FileHandler.deleteFile(favoriteStationsFile);
		String favoriteStationsFilePath = Variable.DIRECTORY_NAME + Variable.FAVORITE_STATIONS_FILE;
		favoriteStationsFile = FileHandler.makeFile(dir, favoriteStationsFilePath);
		
		for(int i=0 ; i<Variable.favoriteStationsList.size() ; i++) {
			String file_content = Variable.favoriteStationsList.get(i) + "\n";
			FileHandler.writeFile(favoriteStationsFile, file_content.getBytes());
		}
	}
}
