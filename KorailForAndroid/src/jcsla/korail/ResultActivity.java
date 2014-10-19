package jcsla.korail;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity implements OnItemClickListener
{	
	private AdView adView;
	
	File dir;
	File historyFile;
	
	ResultAdapter trainAdapter;
	boolean lastItemVisibleFlag = false;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_result);

		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0493aa")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_result);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		TextView trainResultTitle = (TextView) findViewById(R.id.trainResultTitle);
		trainResultTitle.setTypeface(Variable.typeface);
		
		TextView resultTrainTypeTitle = (TextView) findViewById(R.id.resultTrainTypeTitle);
		TextView resultDepTitle = (TextView) findViewById(R.id.resultDepTitle);
		TextView resultArrTitle = (TextView) findViewById(R.id.resultArrTitle);
		TextView resultLocationTitle = (TextView) findViewById(R.id.resultLocationTitle);
		TextView resultDelayTimeTitle = (TextView) findViewById(R.id.resultDelayTimeTitle);
		
		resultTrainTypeTitle.setTypeface(Variable.typeface);
		resultDepTitle.setTypeface(Variable.typeface);
		resultArrTitle.setTypeface(Variable.typeface);
		resultLocationTitle.setTypeface(Variable.typeface);
		resultDelayTimeTitle.setTypeface(Variable.typeface);

		trainAdapter = new ResultAdapter(this, R.layout.result_row, Variable.resultList);
		ListView resultListView = (ListView) findViewById(R.id.resultListView);
		resultListView.setAdapter(trainAdapter);
		resultListView.setOnItemClickListener(this);
		
		
		resultListView.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// 현재 화면에 보이는 첫번째 리스트 아이템의 번호(firstVisibleItem) + 현재 화면에 보이는 리스트 아이템의 갯수(visibleItemCount)가 리스트 전체의 갯수(totalItemCount) -1 보다 크거나 같을때
				lastItemVisibleFlag = (totalItemCount > 0)
						&& (firstVisibleItem + visibleItemCount >= totalItemCount);
			}
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// OnScrollListener.SCROLL_STATE_IDLE은 스크롤이 이동하다가 멈추었을때 발생되는 스크롤 상태입니다. 즉 스크롤이 바닦에 닿아 멈춘 상태에 처리를 하겠다는 뜻
				if (scrollState == OnScrollListener.SCROLL_STATE_IDLE&& lastItemVisibleFlag) {
					// 출발 시간 바꾸기.
					int time = Integer.parseInt(Variable.resultList.get(Variable.resultList.size()-1).getDepTime());
					time = time + 1;
					Variable.time = Integer.toString(time);
					if(Variable.time.length() == 5)
						Variable.time = "0" + Variable.time;
					new ResultJsonParser(null, trainAdapter, Variable.train, Variable.date, Variable.time, Variable.departureStation, Variable.arrivalStation).execute();
				}
			}
		});
		
		
		dir = FileHandler.makeDirectory(Variable.DIRECTORY_NAME);
		String historyFilePath = Variable.DIRECTORY_NAME + Variable.HISTORY_FILE;
		historyFile = FileHandler.makeFile(dir, historyFilePath);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.findViewById(R.id.result_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
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
				((TextView) v).setTypeface(Variable.typeface);
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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		final Train t = Variable.resultList.get(position);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(t.getName() + " " + t.getNumber() + " 열차를 등록하시겠습니까?").setCancelable(false)
		.setPositiveButton("확인",
				new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
						// 'YES'
						// 오늘 날짜만 파일에 저장, 아니면 다이얼로그
				    	SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
				    	Date date = new Date();
				    	String currentDate = SimpleDateFormat.format(date);
				    	
				    	if(t.getDepDate().compareTo(currentDate) == 0) {
				    		saveHistoryFile(t);
				    		Toast.makeText(getApplicationContext(), "HISTORY에 저장되었습니다.", Toast.LENGTH_LONG).show();
				    	}
				    	else
				    		Toast.makeText(getApplicationContext(), "당일 기차만 저장할 수 있습니다.", Toast.LENGTH_LONG).show();
				    	// 끝
				    	finish();
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
	
	public void saveHistoryFile(Train t)
	{
		String file_content = t.getDepDate() + "/" + t.getType() + "/" + t.getNumber() + "/" + 
				t.getDepName() + "/" + t.getProcessedDepTime() + "/" + t.getArrName() + "/" + t.getArrDate() + "/" + t.getProcessedArrTime() + "\n";
		FileHandler.writeFile(historyFile, file_content.getBytes());
		
		Variable.tempHistoryList.add(file_content.trim());
	}
}