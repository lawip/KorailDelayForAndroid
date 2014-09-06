package jcsla.korail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.ads.*;

public class SearchActivity extends ActionBarActivity
{
	private static final String TYPEFACE_NAME = "BM-HANNA.ttf";
	private Typeface typeface = null;
	private static final int StationSearchActivity = 0;
	private static final int ArrivalStationSearchActivity = 1;
	
	private LinearLayout trainTypeContainer;
	private LinearLayout depDateContainer;
	private LinearLayout depTimeContainer;
	private LinearLayout depStationContainer;
	private LinearLayout arrStationContainer;
	private TextView trainSearchTitle;
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
	private Button searchButton;
	
	private ProgressDialog progressDialog;
	
	private AdView adView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		loadTypeface();
		setContentView(R.layout.activity_search);
		
		// Ÿ��Ʋ custom :
		// http://stackoverflow.com/questions/8607707/how-to-set-a-custom-font-in-the-actionbar-title
		ActionBar actionBar = getActionBar();
		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0493aa")));
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setCustomView(R.layout.actionbar_search);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		trainSearchTitle = (TextView) findViewById(R.id.trainSearchTitle);
		trainSearchTitle.setTypeface(typeface);
		
		trainTypeContainer	 = (LinearLayout) findViewById(R.id.trainTypeContainer);
		depDateContainer	 = (LinearLayout) findViewById(R.id.depDateContainer);
		depTimeContainer	 = (LinearLayout) findViewById(R.id.depTimeContainer);
		depStationContainer	 = (LinearLayout) findViewById(R.id.depStationContainer);
		arrStationContainer	 = (LinearLayout) findViewById(R.id.arrStaionContainer);
		searchButton		 = (Button)		  findViewById(R.id.searchButton);
		
		trainTypeTitle = (TextView) findViewById(R.id.trainTypeTitle);
		depDateTitle = (TextView) findViewById(R.id.depDateTitle);
		depTimeTitle = (TextView) findViewById(R.id.depTimeTitle);
		depInfoTitle = (TextView) findViewById(R.id.depInfoTitle);
		arrInfoTitle = (TextView) findViewById(R.id.arrInfoTitle);
		
		trainTypeEdit		 = (TextView) findViewById(R.id.editTrainType);
		depDateEdit		 = (TextView) findViewById(R.id.editDepDate);
		depTimeEdit		 = (TextView) findViewById(R.id.editDepTime);
		depStationEdit	 = (TextView) findViewById(R.id.editDepStation);
		arrStationEdit	 = (TextView) findViewById(R.id.editArrStation);
		
		trainTypeTitle.setTypeface(typeface);
		depDateTitle.setTypeface(typeface);
		depTimeTitle.setTypeface(typeface);
		depInfoTitle.setTypeface(typeface);
		arrInfoTitle.setTypeface(typeface);
		
		trainTypeEdit.setTypeface(typeface);
		depDateEdit.setTypeface(typeface);
		depTimeEdit.setTypeface(typeface);
		depStationEdit.setTypeface(typeface);
		arrStationEdit.setTypeface(typeface);
		
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
		searchButton.setOnClickListener(layoutClickEye);
		
		// Look up the AdView as a resource and load a request.
		adView = (AdView) this.findViewById(R.id.search_adView);
		AdRequest adRequest = new AdRequest.Builder().build();
		adView.loadAd(adRequest);
	}
	/*
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater menuInflater = getSupportMenuInflater();
		menuInflater.inflate(R.menu.menu_search, menu);
		
		return true;
	}
	*/
	private OnClickListener layoutClickEye = new OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			switch (v.getId())
			{
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
				Intent depIntent = new Intent(getApplicationContext(), StationSearchActivity.class);
				depIntent.putExtra("title", "��߿��˻�");
				startActivityForResult(depIntent, StationSearchActivity);
				break;
				
			case R.id.arrStaionContainer:
				Intent arrIntent = new Intent(getApplicationContext(), StationSearchActivity.class);
				arrIntent.putExtra("title", "�������˻�");
				startActivityForResult(arrIntent, ArrivalStationSearchActivity);
				break;
			}
		}
	};
	
	private void loadTypeface()
	{
		if (typeface == null)
			typeface = Typeface.createFromAsset(getAssets(), TYPEFACE_NAME);
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
				((TextView) v).setTypeface(typeface);
			}
		}
		super.setContentView(view);
	}
	
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

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
		DatePickerDialog alert = new DatePickerDialog(this, mDateSetListener, cyear, cmonth, cday);
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
		TimePickerDialog alert = new TimePickerDialog(this, mTimeSetListener, chour, cminute, false);
		alert.setTitle("�ð� ����");
		alert.show();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent i)
	{
		switch (requestCode)
		{
		case StationSearchActivity:
			if (resultCode == RESULT_OK)
			{
				String station = i.getExtras().getString("station");
				depStationEdit.setText(station);
			}
			break;
		case ArrivalStationSearchActivity:
			if (resultCode == RESULT_OK)
			{
				String station = i.getExtras().getString("station");
				arrStationEdit.setText(station);
			}
			break;
		}
	}

	public void queryTrain()
	{
		TrainList.trainList.clear();

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

		new JSONParser(train, date, time, departureStation, arrivalStation).execute();
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

	public class JSONParser extends AsyncTask<Void, Void, Void>
	{
		String url = null;
		boolean isNull = false;

		public JSONParser(String train, String date, String time, String dep, String arr)
		{
<<<<<<< HEAD
			url = "http://115.71.236.224:8082/searchTrain/?train=" + train + "&date=" + date + "&time=" + time + "&dep="
=======
			url = "http://221.166.154.113:8000/searchTrain/?train=" + train + "&date=" + date + "&time=" + time + "&dep="
>>>>>>> a915424c7bc50562fccc34baea7b4a9ae426591b
					+ dep + "&arr=" + arr;
			progressDialog = ProgressDialog.show(SearchActivity.this, "", "��� ��ٷ��ּ���...", true);
		}

		@Override
		protected Void doInBackground(Void... params)
		{
			JSONParserHelper jsonParserHelper = new JSONParserHelper();
			JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);
			if (jsonArray == null)
			{
				isNull = true;
				return null;
			}

			try
			{
				for (int i = 0; i < jsonArray.length(); i++)
				{
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String trainType = jsonObject.getString("train_type");
					String trainNumber = jsonObject.getString("train_number");
					String depCode = jsonObject.getString("dep_code");
					String depDate = jsonObject.getString("dep_date");
					String depTime = jsonObject.getString("dep_time");
					String arrCode = jsonObject.getString("arr_code");
					String arrDate = jsonObject.getString("arr_date");
					String arrTime = jsonObject.getString("arr_time");
					String trainStatus = jsonObject.getString("train_status");
					String trainDelayStatus = jsonObject.getString("train_delay_status");
					Train t = new Train(trainType, trainNumber, depCode, depDate, depTime, arrCode, arrDate, arrTime,
							trainStatus, trainDelayStatus);
					TrainList.trainList.add(t);
				}
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);

			progressDialog.dismiss();

			if (isNull == true)
			{
				Toast.makeText(getApplicationContext(), "��ȸ ����� �����ϴ�.", Toast.LENGTH_LONG).show();
				return;
			}

			Intent i = new Intent(getApplicationContext(), ResultActivity.class);
			startActivity(i);
		}
	}

	public class JSONParserHelper
	{
		JSONArray jsonArray = null;

		public JSONArray getJSONFromURL(String url)
		{
			try
			{
				DefaultHttpClient httpClient = new DefaultHttpClient();
<<<<<<< HEAD
				HttpGet httpPost = new HttpGet(url);
=======
				HttpGet httpPost = new HttpGet
						
						
						(url);
>>>>>>> a915424c7bc50562fccc34baea7b4a9ae426591b
				HttpResponse httpResponse;
				httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				InputStream inputStream = httpEntity.getContent();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				String line = null;
				while ((line = bufferedReader.readLine()) != null)
				{
					jsonArray = new JSONArray(line);
				}
				inputStream.close();
			} catch (ClientProtocolException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (JSONException e)
			{
				e.printStackTrace();
			}

			return jsonArray;
		}
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
