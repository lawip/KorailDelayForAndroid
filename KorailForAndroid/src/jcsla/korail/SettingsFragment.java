package jcsla.korail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SettingsFragment extends Fragment
{
	private static final String TYPEFACE_NAME = "BM-HANNA.ttf";
	private Typeface typeface = null;
	
	private View v;
	
	private TextView enrollFavoriteStationTitle;
	private TextView sendEmailTitle;
	private TextView writeReviewTitle;
	private TextView appInformationTitle;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		loadTypeface();
		
		v = inflater.inflate(R.layout.fragment_settings, container, false);
		
		enrollFavoriteStationTitle = (TextView) v.findViewById(R.id.enrollFavoriteStationTitle);
		sendEmailTitle = (TextView) v.findViewById(R.id.sendEmailTitle);
		writeReviewTitle = (TextView) v.findViewById(R.id.writeReviewTitle);
		appInformationTitle = (TextView) v.findViewById(R.id.appInformationTitle);
		
		enrollFavoriteStationTitle.setTypeface(TypefaceHelper.typeface);
		sendEmailTitle.setTypeface(typeface);
		writeReviewTitle.setTypeface(typeface);
		appInformationTitle.setTypeface(typeface);
		
		return v;
	}
	
	private void loadTypeface()
	{
		if (typeface == null)
			typeface = Typeface.createFromAsset(getActivity().getAssets(), TYPEFACE_NAME);
	}
}
