package jcsla.korail;

import java.util.concurrent.ExecutionException;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsFragment extends Fragment
{
	private View v;
	private TextView description;
	
	private AdView adView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		v = inflater.inflate(R.layout.fragment_settings, container, false);
		
		LinearLayout enrollFavoriteStationContainer = (LinearLayout) v.findViewById(R.id.enrollFavoriteStationContainer);
		LinearLayout sendEmailContainer = (LinearLayout) v.findViewById(R.id.sendEmailContainer);
		LinearLayout writeReviewContainer = (LinearLayout) v.findViewById(R.id.writeReviewContainer);
		LinearLayout appInformationContainer = (LinearLayout) v.findViewById(R.id.appInformationContainer);
		
		TextView enrollFavoriteStationTitle = (TextView) v.findViewById(R.id.enrollFavoriteStationTitle);
		TextView sendEmailTitle = (TextView) v.findViewById(R.id.sendEmailTitle);
		TextView writeReviewTitle = (TextView) v.findViewById(R.id.writeReviewTitle);
		TextView appInformationTitle = (TextView) v.findViewById(R.id.appInformationTitle);
		TextView version = (TextView) v.findViewById(R.id.version);
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
		appInformationContainer.setOnClickListener(layoutClickEye);
		
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
				
			case R.id.appInformationContainer:
				compareVersionInformation();
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
	
	protected void compareVersionInformation() {
		try {
			PackageInfo i = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0);
			String appVersion = i.versionName; // 어플리케이션 버전
			String webVersion = new VersionJsonParser().execute().get(); // 웹 페이지에서 뜨는 버전(최신버전)
			
			if(appVersion.compareTo(webVersion) == 0)
				Toast.makeText(getActivity().getApplicationContext(), "최신 버전입니다.", Toast.LENGTH_LONG).show();
			else {
				Toast.makeText(getActivity().getApplicationContext(), "업데이트가 필요합니다.\n플레이스토어로 이동합니다.", Toast.LENGTH_LONG).show();
				Uri uri = Uri.parse("market://details?id=jcsla.korail");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}
