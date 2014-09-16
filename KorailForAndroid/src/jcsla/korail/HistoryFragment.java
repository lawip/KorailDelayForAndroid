package jcsla.korail;

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
		
		trainTypeHistoryTitle.setTypeface(Variable.typeface);
		depDateHistoryTitle.setTypeface(Variable.typeface);
		depTimeHistoryTitle.setTypeface(Variable.typeface);
		depInfoHistoryTitle.setTypeface(Variable.typeface);
		arrInfoHistoryTitle.setTypeface(Variable.typeface);
		refreshButton.setTypeface(Variable.typeface);
		
		// ���� �ε� �Լ� ȣ��.
		
		return v;
	}
	
	// ���ΰ�ħ ��ư Ŭ�� �̺�Ʈ
}
