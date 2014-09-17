package jcsla.korail;

import java.util.Calendar;
import java.util.StringTokenizer;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.ads.*;

public class SearchFragment extends Fragment
{
	private static final int StationSearchActivity = 0;
	private static final int ArrivalStationSearchActivity = 1;
	
	private View v;
	
	private LinearLayout trainTypeContainer;
	private LinearLayout depDateContainer;
	private LinearLayout depTimeContainer;
	private LinearLayout depStationContainer;
	private LinearLayout arrStationContainer;
	private LinearLayout favoriteStationsContainer;
	
	private TextView trainTypeTitle;
	private TextView depDateTitle;
	private TextView depTimeTitle;
	private TextView depInfoTitle;
	private TextView arrInfoTitle;
	private TextView trainTypeEdit;
	private TextView depDateEdit;
	private TextView depTimeEdit;
	private TextView depStationEdit;
	private TextView arrStationEdit;
	private TextView favoriteStationSearch;
	
	private Button searchButton;
	
	private AdView adView;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		v = inflater.inflate(R.layout.fragment_search, container, false);
		
		trainTypeContainer	 = (LinearLayout) v.findViewById(R.id.trainTypeContainer);
		depDateContainer	 = (LinearLayout) v.findViewById(R.id.depDateContainer);
		depTimeContainer	 = (LinearLayout) v.findViewById(R.id.depTimeContainer);
		depStationContainer	 = (LinearLayout) v.findViewById(R.id.depStationContainer);
		arrStationContainer	 = (LinearLayout) v.findViewById(R.id.arrStaionContainer);
		favoriteStationsContainer	 = (LinearLayout) v.findViewById(R.id.favoriteStationsContainer);
		searchButton		 = (Button)		  v.findViewById(R.id.searchButton);
		
		trainTypeTitle = (TextView) v.findViewById(R.id.trainTypeTitle);
		depDateTitle = (TextView) v.findViewById(R.id.depDateTitle);
		depTimeTitle = (TextView) v.findViewById(R.id.depTimeTitle);
		depInfoTitle = (TextView) v.findViewById(R.id.depInfoTitle);
		arrInfoTitle = (TextView) v.findViewById(R.id.arrInfoTitle);
		favoriteStationSearch = (TextView) v.findViewById(R.id.FavoriteStationSearchTitle);
		
		trainTypeEdit		 = (TextView) v.findViewById(R.id.editTrainType);
		depDateEdit		 = (TextView) v.findViewById(R.id.editDepDate);
		depTimeEdit		 = (TextView) v.findViewById(R.id.editDepTime);
		depStationEdit	 = (TextView) v.findViewById(R.id.editDepStation);
		arrStationEdit	 = (TextView) v.findViewById(R.id.editArrStation);
		
		trainTypeTitle.setTypeface(Variable.typeface);
		depDateTitle.setTypeface(Variable.typeface);
		depTimeTitle.setTypeface(Variable.typeface);
		depInfoTitle.setTypeface(Variable.typeface);
		arrInfoTitle.setTypeface(Variable.typeface);
		favoriteStationSearch.setTypeface(Variable.typeface);
		
		trainTypeEdit.setTypeface(Variable.typeface);
		depDateEdit.setTypeface(Variable.typeface);
		depTimeEdit.setTypeface(Variable.typeface);
		depStationEdit.setTypeface(Variable.typeface);
		arrStationEdit.setTypeface(Variable.typeface);
		searchButton.setTypeface(Variable.typeface);
		
		String date = getDate();
		String time = getTime();
		
		trainTypeEdit.setText("��ü");	//�߰��� �⺻���� ��ü���� �˻�����
		depDateEdit.setText(date);
		depTimeEdit.setText(time);
		depStationEdit.setText("����");
		arrStationEdit.setText("�λ�");
		
		trainTypeContainer.setOnClickListener(layoutClickEye);
		depDateContainer.setOnClickListener(layoutClickEye);
		depTimeContainer.setOnClickListener(layoutClickEye);
		depStationContainer.setOnClickListener(layoutClickEye);
		arrStationContainer.setOnClickListener(layoutClickEye);
		favoriteStationsContainer.setOnClickListener(layoutClickEye);
		searchButton.setOnClickListener(layoutClickEye);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.v.findViewById(R.id.search_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
		
		return v;
	}

	private OnClickListener layoutClickEye = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
			case R.id.favoriteStationsContainer:
				showFavoriteStationDialog();
				break;
				
			case R.id.searchButton:
				queryTrain();
				break;
			
			case R.id.trainTypeContainer:
				showTrainPickerDialog();
				break;
				
			case R.id.depDateContainer:
				showDatePickerDialog();
				break;
				
			case R.id.depTimeContainer:
				showTimePickerDialog();
				break;
				
			case R.id.depStationContainer:
				Intent depIntent = new Intent(getActivity().getApplicationContext(), StationSearchActivity.class);
				depIntent.putExtra("title", "��߿��˻�");
				startActivityForResult(depIntent, StationSearchActivity);
				break;
				
			case R.id.arrStaionContainer:
				Intent arrIntent = new Intent(getActivity().getApplicationContext(), StationSearchActivity.class);
				arrIntent.putExtra("title", "�������˻�");
				startActivityForResult(arrIntent, ArrivalStationSearchActivity);
				break;
			}
		}
	};

	/*
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
*/
	public String getDate()
	{
		Calendar c = Calendar.getInstance();
		int cyear = c.get(Calendar.YEAR);
		int cmonth = c.get(Calendar.MONTH) + 1;
		int cday = c.get(Calendar.DAY_OF_MONTH);
		int cDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		return String.format("%d / %d / %d %s", cyear, cmonth, cday, getDayOfWeek(cDayOfWeek));
	}

	/**
	 * ���ְ����� ���� ���ڿ��� �����ϴ� �޼ҵ�
	 * @param year		��
	 * @param month		��
	 * @param day		��
	 * @return	���Ϲ��ڿ�
	 */
	private String getDayOfWeek(int year, int month, int day)
	{
		int value;
		
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		
		value = cal.get(Calendar.DAY_OF_WEEK);

		switch (value)
		{
		case 1:
			return "�Ͽ���";
		case 2:
			return "������";
		case 3:
			return "ȭ����";
		case 4:
			return "������";
		case 5:
			return "�����";
		case 6:
			return "�ݿ���";
		default:
			return "�����";
		}
	}
	
	/**
	 * ���ְ����� ���� ���ڿ��� �����ϴ� �޼ҵ�
	 * @param dayOfWeek	���� ��
	 * @return	���Ϲ��ڿ�
	 */
	private String getDayOfWeek(int dayOfWeek)
	{
		switch (dayOfWeek)
		{
		case 1:
			return "�Ͽ���";
		case 2:
			return "������";
		case 3:
			return "ȭ����";
		case 4:
			return "������";
		case 5:
			return "�����";
		case 6:
			return "�ݿ���";
		default:
			return "�����";
		}
	}
	
	public String getTime()
	{
		Calendar c = Calendar.getInstance();
		int chour = c.get(Calendar.HOUR_OF_DAY);
		int cminute = c.get(Calendar.MINUTE);
		/*
		 * �� �κ��� �ּ�ó���� ������ ��Ȯ�� �� ������ �˻����� �ʰ�,
		 * 10�� 30���̶�� 10�÷� �˻��Ͽ� �˻� ������ ���� ������ ����.
		 * ���� �� ������ 00������ ����
		 */
		
		String selectedTime = null;
		if (chour < 12)
		{
			if (chour == 0)
				chour = 12;
			selectedTime = "���� " + String.valueOf(chour) + " : " + String.valueOf(cminute);
		} else
		{
			if (chour == 12)
				selectedTime = "���� " + String.valueOf(chour) + " : " + String.valueOf(cminute);
			else
				selectedTime = "���� " + String.valueOf(chour - 12) + " : " + String.valueOf(cminute);
		}

		return selectedTime;
	}

	public void showTrainPickerDialog()
	{
		final CharSequence[] trains = { "��ü", "KTX", "������ȣ", "����ȭȣ", "��ٿ���", "������", "����ö��", "ITX-������", "ITX-û��" };

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("���� ����");
		builder.setItems(trains, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				trainTypeEdit.setText(trains[item]);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void showDatePickerDialog()
	{
		Calendar c = Calendar.getInstance();
		int cyear = c.get(Calendar.YEAR);
		int cmonth = c.get(Calendar.MONTH);
		int cday = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener()
		{
			public void onDateSet(DatePicker view, int year, int month, int day)
			{
				String selectedDate = String.format("%d / %d / %d %s", year, month+1, day, getDayOfWeek(year, month, day));
				
				depDateEdit.setText(selectedDate);
			}
		};
		DatePickerDialog alert = new DatePickerDialog(getActivity(), mDateSetListener, cyear, cmonth, cday);
		alert.setTitle("��¥ ����");
		alert.show();
	}

	public void showTimePickerDialog()
	{
		Calendar c = Calendar.getInstance();
		int chour = c.get(Calendar.HOUR_OF_DAY);
		int cminute = c.get(Calendar.MINUTE);

		TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener()
		{
			String selectedTime;

			@Override
			public void onTimeSet(TimePicker view, int hour, int minute)
			{
				if (hour < 12)
				{
					if (hour == 0)
						hour = 12;
					selectedTime = "���� " + String.valueOf(hour) + " : " + String.valueOf(minute);
				} else
				{
					if (hour == 12)
						selectedTime = "���� " + String.valueOf(hour) + " : " + String.valueOf(minute);
					else
						selectedTime = "���� " + String.valueOf(hour - 12) + " : " + String.valueOf(minute);
				}

				depTimeEdit.setText(selectedTime);
			}
		};
		TimePickerDialog alert = new TimePickerDialog(getActivity(), mTimeSetListener, chour, cminute, false);
		alert.setTitle("�ð� ����");
		alert.show();
	}
	
	// ���ã�±��� �˻� ���̾�α� ����
	protected void showFavoriteStationDialog() {
		int length = Variable.favoriteStationsList.size();
		CharSequence[] favoriteStations = new CharSequence[length];
		
		for(int i=0 ; i<length ; i++)
			favoriteStations[i] = Variable.favoriteStationsList.get(i);

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("���ã�±��� ����");
		builder.setItems(favoriteStations, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				// Ŭ�� �� ��߿� - ������ �ٲٱ�
				String selectedItem = Variable.favoriteStationsList.get(item);
				
				StringTokenizer st = new StringTokenizer(selectedItem, "-");

				String departureStation = st.nextToken().trim();
				String arrivalStation = st.nextToken().trim();
				
				depStationEdit.setText(departureStation);
				arrStationEdit.setText(arrivalStation);
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent i)
	{
		switch (requestCode)
		{
		case StationSearchActivity:
			if (resultCode == Activity.RESULT_OK)
			{
				String station = i.getExtras().getString("station");
				depStationEdit.setText(station);
			}
			break;
		case ArrivalStationSearchActivity:
			if (resultCode == Activity.RESULT_OK)
			{
				String station = i.getExtras().getString("station");
				arrStationEdit.setText(station);
			}
			break;
		}
	}

	public void queryTrain()
	{
		Variable.resultList.clear();

		String sTrain = trainTypeEdit.getText().toString();
		String sDate = depDateEdit.getText().toString();
		String sTime = depTimeEdit.getText().toString();
		String sDepartureStation = depStationEdit.getText().toString();
		String sArrivalStation = arrStationEdit.getText().toString();

		String train = getTrain(sTrain);
		String date = getDate(sDate);
		String time = getTime(sTime);
		String departureStation = sDepartureStation;
		String arrivalStation = sArrivalStation;

		new SearchJsonParser(this, train, date, time, departureStation, arrivalStation).execute();
	}

	public String getTrain(String train)
	{
		String trainNumber = null;
		if (train.compareTo("��ü") == 0)
			trainNumber = "05";
		else if (train.compareTo("KTX") == 0)
			trainNumber = "00";
		else if (train.compareTo("������ȣ") == 0)
			trainNumber = "01";
		else if (train.compareTo("����ȭȣ") == 0)
			trainNumber = "02";
		else if (train.compareTo("��ٿ���") == 0)
			trainNumber = "03";
		else if (train.compareTo("������") == 0)
			trainNumber = "04";
		else if (train.compareTo("����ö��") == 0)
			trainNumber = "06";
		else if (train.compareTo("ITX-������") == 0)
			trainNumber = "08";
		else if (train.compareTo("ITX-û��") == 0)
			trainNumber = "09";

		return trainNumber;
	}

	public String getDate(String date)
	{
		StringTokenizer st = new StringTokenizer(date, "/");
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken().split(" ")[1];	//��������

		if (Integer.parseInt(month.trim()) < 10)
			month = "0" + month.trim();

		if (Integer.parseInt(day.trim()) < 10)
			day = "0" + day.trim();

		String result = year.trim() + month.trim() + day.trim();

		return result.trim();
	}

	public String getTime(String time)
	{
		String hour = null;
		String minute = null;
		if (time.contains("����") == true)
		{
			String sTime = time.substring(2); // 2 : 18
			StringTokenizer st = new StringTokenizer(sTime, ":");
			hour = st.nextToken().trim();
			minute = st.nextToken().trim();
			if (Integer.parseInt(hour) < 10)
				hour = "0" + hour;
			if (Integer.parseInt(minute) < 10)
				minute = "0" + minute;
			if (Integer.parseInt(hour) == 12)
				hour = "00";

			return hour + minute + "00";
		} else if (time.contains("����") == true)
		{
			String sTime = time.substring(2); // 2 : 18
			StringTokenizer st = new StringTokenizer(sTime, ":");
			hour = st.nextToken().trim();
			minute = st.nextToken().trim();
			hour = Integer.toString((Integer.parseInt(hour) + 12));
			if (Integer.parseInt(minute) < 10)
				minute = "0" + minute;
			if (Integer.parseInt(hour) == 24)
				hour = "12";
		}
		return hour + minute + "00";
	}

	public String getStation(String station)
	{
		return Stations.name_number_stations.get(station);
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

	/** Called before the activity is destroyed. */
	@Override
	public void onDestroy() {
		// Destroy the AdView.
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
