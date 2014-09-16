package jcsla.korail;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
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
	private TextView version;
	private TextView description;
	
	private AdView adView;
	
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
		version = (TextView) v.findViewById(R.id.version);
		description = (TextView) v.findViewById(R.id.appDescription);
		
		enrollFavoriteStationTitle.setTypeface(Variable.typeface);
		sendEmailTitle.setTypeface(Variable.typeface);
		writeReviewTitle.setTypeface(Variable.typeface);
		appInformationTitle.setTypeface(Variable.typeface);
		version.setTypeface(Variable.typeface);
		description.setTypeface(Variable.typeface);
		setDescripionText();
		
		enrollFavoriteStationContainer.setOnClickListener(layoutClickEye);
		sendEmailContainer.setOnClickListener(layoutClickEye);
		writeReviewContainer.setOnClickListener(layoutClickEye);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.v.findViewById(R.id.settings_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		return v;
	}
	
	private void setDescripionText() {
		description.setText("코레일 지연정보를 이용해주셔서 감사합니다. \n"
				+ "버그나 기타 문의사항은 상단의 메일, 리뷰를 활용해주세요.");
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
