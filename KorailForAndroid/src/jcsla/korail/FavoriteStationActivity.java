package jcsla.korail;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.TextView;

public class FavoriteStationActivity extends ActionBarActivity {
	
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
		favoriteStationTitle.setTypeface(TypefaceHelper.typeface);
		
		Button enrollButton = (Button) findViewById(R.id.enrollButton);
		enrollButton.setTypeface(TypefaceHelper.typeface);
	}

}
