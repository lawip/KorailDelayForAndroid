package jcsla.korail;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class HistoryFragment extends Fragment
{
	private View v;
	
	HistoryAdapter historyAdapter;
	
	private AdView adView;
	
	File dir;
	File historyFile;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		v = inflater.inflate(R.layout.fragment_history, container, false);
		
		TextView historyTrainTypeTitle = (TextView) v.findViewById(R.id.historyTrainTypeTitle);
		TextView historyDepTitle = (TextView) v.findViewById(R.id.historyDepTitle);
		TextView historyArrTitle = (TextView) v.findViewById(R.id.historyArrTitle);
		TextView historyLocationTitle = (TextView) v.findViewById(R.id.historyLocationTitle);
		TextView historyDelayTimeTitle = (TextView) v.findViewById(R.id.historyDelayTimeTitle);
		Button refreshButton = (Button) v.findViewById(R.id.refreshButton);
		
		historyTrainTypeTitle.setTypeface(Variable.typeface);
		historyDepTitle.setTypeface(Variable.typeface);
		historyArrTitle.setTypeface(Variable.typeface);
		historyLocationTitle.setTypeface(Variable.typeface);
		historyDelayTimeTitle.setTypeface(Variable.typeface);
		refreshButton.setTypeface(Variable.typeface);
		
		refreshButton.setOnClickListener(onClickListener);
		
		// adpater
		historyAdapter = new HistoryAdapter(this.getActivity(), R.layout.result_row, Variable.historyList);
		ListView historyListView = (ListView) v.findViewById(R.id.trainHistoryListView);
		historyListView.setAdapter(historyAdapter);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.v.findViewById(R.id.history_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		return v;
	}
	
	// 새로고침 버튼 클릭 이벤트
	private OnClickListener onClickListener = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.refreshButton:
				refreshHistory();
				break;
			}
		}
	};

	protected void refreshHistory() {
		dir = FileHandler.makeDirectory(Variable.DIRECTORY_NAME);
		String historyFilePath = Variable.DIRECTORY_NAME + Variable.HISTORY_FILE;
		historyFile = FileHandler.makeFile(dir, historyFilePath);
		Variable.tempHistoryList = FileHandler.readFile(historyFile);
		
		insertHistoryList();

		historyAdapter.notifyDataSetChanged();
	}
	
	private void insertHistoryList() {
		ArrayList<Train> temp = new ArrayList<Train>();
		
		Variable.historyList.clear();

		for (int i = 0; i < Variable.tempHistoryList.size(); i++) {
			StringTokenizer st = new StringTokenizer(Variable.tempHistoryList.get(i), "/");

			String depDate = st.nextToken().trim();
			String type = st.nextToken().trim();
			String number = st.nextToken().trim();
			String depCode = st.nextToken().trim();
			String depTime = st.nextToken().trim();
			String arrCode = st.nextToken().trim();
			String arrDate = st.nextToken().trim();
			String arrTime = st.nextToken().trim();
			
			Train t = new Train(type, number, depCode, depDate, depTime, arrCode, arrDate, arrTime, "", "");
			temp.add(t);
		}
		
		if(temp.size() != 0) {
			try {
				ArrayList<Train> result = new HistoryJsonParser(temp).execute().get();
				Variable.historyList.addAll(result);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}