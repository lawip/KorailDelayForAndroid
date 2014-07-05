package jcsla.korail;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity
{
	private static final String TYPEFACE_NAME = "BM-HANNA.ttf";
	public static Typeface typeface = null;
	private TextView trainResultTitle;
	private ListView trainListView;
	
	private AdView adView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		loadTypeface();
		setContentView(R.layout.activity_result);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0493aa")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_result);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		trainResultTitle = (TextView) findViewById(R.id.trainResultTitle);
		trainResultTitle.setTypeface(typeface);
		
		TextView titleTrainType = (TextView) findViewById(R.id.titleTrainType);
		TextView titleDepInfo = (TextView) findViewById(R.id.titleDepInfo);
		TextView titleArrInfo = (TextView) findViewById(R.id.titleArrInfo);
		TextView titleStatus = (TextView) findViewById(R.id.titleStatus);
		TextView titleDelayInfo = (TextView) findViewById(R.id.titleDelayInfo);
		
		titleTrainType.setTypeface(typeface);
		titleDepInfo.setTypeface(typeface);
		titleArrInfo.setTypeface(typeface);
		titleStatus.setTypeface(typeface);
		titleDelayInfo.setTypeface(typeface);

		TrainAdapter trainAdapter = new TrainAdapter(this, R.layout.result_row, TrainList.trainList);
		trainListView = (ListView) findViewById(R.id.trainListView);
		trainListView.setAdapter(trainAdapter);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.findViewById(R.id.result_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}

	private void loadTypeface()
	{
		if (typeface == null)
			typeface = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME);
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
				((TextView) v).setTypeface(typeface);
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

	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
