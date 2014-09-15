package jcsla.korail;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingsFragment extends Fragment
{
	private View v;
	
	private LinearLayout enrollFavoriteStationContainer;
	private LinearLayout sendEmailContainer;
	private LinearLayout writeReviewContainer;
	
	private TextView enrollFavoriteStationTitle;
	private TextView sendEmailTitle;
	private TextView writeReviewTitle;
	private TextView appInformationTitle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		v = inflater.inflate(R.layout.fragment_settings, container, false);
		
		enrollFavoriteStationContainer = (LinearLayout) v.findViewById(R.id.enrollFavoriteStationContainer);
		sendEmailContainer = (LinearLayout) v.findViewById(R.id.sendEmailContainer);
		writeReviewContainer = (LinearLayout) v.findViewById(R.id.writeReviewContainer);
		
		enrollFavoriteStationTitle = (TextView) v.findViewById(R.id.enrollFavoriteStationTitle);
		sendEmailTitle = (TextView) v.findViewById(R.id.sendEmailTitle);
		writeReviewTitle = (TextView) v.findViewById(R.id.writeReviewTitle);
		appInformationTitle = (TextView) v.findViewById(R.id.appInformationTitle);
		
		enrollFavoriteStationTitle.setTypeface(TypefaceHelper.typeface);
		sendEmailTitle.setTypeface(TypefaceHelper.typeface);
		writeReviewTitle.setTypeface(TypefaceHelper.typeface);
		appInformationTitle.setTypeface(TypefaceHelper.typeface);
		
		enrollFavoriteStationContainer.setOnClickListener(layoutClickEye);
		sendEmailContainer.setOnClickListener(layoutClickEye);
		writeReviewContainer.setOnClickListener(layoutClickEye);
		
		return v;
	}
	
	private OnClickListener layoutClickEye = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.enrollFavoriteStationContainer:
				showEnrollFavoriteStationActivity();
				break;
				
			case R.id.sendEmailContainer:
				showSendEmailActivity();
				break;
			
			case R.id.writeReviewContainer:
				showWriteReviewActivity();
				break;
			}
		}
	};
	
	protected void showEnrollFavoriteStationActivity() {
		Intent i = new Intent(getActivity().getApplicationContext(), FavoriteStationActivity.class);
		startActivity(i);
	}
	
	protected void showSendEmailActivity() {
		Uri uri = Uri.parse("mailto:jcsla@naver.com");
		Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
		startActivity(intent);
	}

	protected void showWriteReviewActivity() {
		Uri uri = Uri.parse("market://details?id=jcsla.korail");   
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);   
		startActivity(intent); 
	}
}
