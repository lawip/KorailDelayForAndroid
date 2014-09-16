package jcsla.korail;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnItemClickListener
{
	private TextView trainResultTitle;
	private ListView trainListView;
	
	private AdView adView;

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
		
		trainResultTitle = (TextView) findViewById(R.id.trainResultTitle);
		trainResultTitle.setTypeface(Variable.typeface);
		
		TextView titleTrainType = (TextView) findViewById(R.id.titleTrainType);
		TextView titleDepInfo = (TextView) findViewById(R.id.titleDepInfo);
		TextView titleArrInfo = (TextView) findViewById(R.id.titleArrInfo);
		TextView titleStatus = (TextView) findViewById(R.id.titleStatus);
		TextView titleDelayInfo = (TextView) findViewById(R.id.titleDelayInfo);
		
		titleTrainType.setTypeface(Variable.typeface);
		titleDepInfo.setTypeface(Variable.typeface);
		titleArrInfo.setTypeface(Variable.typeface);
		titleStatus.setTypeface(Variable.typeface);
		titleDelayInfo.setTypeface(Variable.typeface);

		TrainAdapter trainAdapter = new TrainAdapter(this, R.layout.result_row, TrainList.trainList);
		trainListView = (ListView) findViewById(R.id.trainListView);
		trainListView.setAdapter(trainAdapter);
		trainListView.setOnItemClickListener(this);
		
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
		Train t = TrainList.trainList.get(position);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(t.getTrainType() + " " + t.getTrainNumber() + "번 열차를 등록하시겠습니까?").setCancelable(false)
		.setPositiveButton("확인",
				new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        // 'YES'
				    	// 파일에 저장
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
}
