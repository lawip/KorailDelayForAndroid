package jcsla.korail;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HistoryFragment extends Fragment
{
	private View v;
	
	private TextView trainTypeHistoryTitle;
	private TextView depDateHistoryTitle;
	private TextView depTimeHistoryTitle;
	private TextView depInfoHistoryTitle;
	private TextView arrInfoHistoryTitle;
	private Button refreshButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		v = inflater.inflate(R.layout.fragment_history, container, false);
		
		trainTypeHistoryTitle = (TextView) v.findViewById(R.id.titleHistoryTrainType);
		depDateHistoryTitle = (TextView) v.findViewById(R.id.titleHistoryDepInfo);
		depTimeHistoryTitle = (TextView) v.findViewById(R.id.titleHistoryArrInfo);
		depInfoHistoryTitle = (TextView) v.findViewById(R.id.titleHistoryStatus);
		arrInfoHistoryTitle = (TextView) v.findViewById(R.id.titleHistoryDelayInfo);
		refreshButton = (Button) v.findViewById(R.id.refreshButton);
		
		trainTypeHistoryTitle.setTypeface(TypefaceHelper.typeface);
		depDateHistoryTitle.setTypeface(TypefaceHelper.typeface);
		depTimeHistoryTitle.setTypeface(TypefaceHelper.typeface);
		depInfoHistoryTitle.setTypeface(TypefaceHelper.typeface);
		arrInfoHistoryTitle.setTypeface(TypefaceHelper.typeface);
		refreshButton.setTypeface(TypefaceHelper.typeface);
		
		// 파일 로드 함수 호출.
		
		return v;
	}
	
	// 새로고침 버튼 클릭 이벤트
}
