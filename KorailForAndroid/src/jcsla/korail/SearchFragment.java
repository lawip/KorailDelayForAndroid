package jcsla.korail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
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
import android.widget.Toast;

import com.google.android.gms.ads.*;

public class SearchFragment extends Fragment
{
	private static final String TYPEFACE_NAME = "BM-HANNA.ttf";
	private Typeface typeface = null;
	private static final int StationSearchActivity = 0;
	private static final int ArrivalStationSearchActivity = 1;
	
	private View v;
	
	private LinearLayout trainTypeContainer;
	private LinearLayout depDateContainer;
	private LinearLayout depTimeContainer;
	private LinearLayout depStationContainer;
	private LinearLayout arrStationContainer;
	
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
	
	private ProgressDialog progressDialog;
	
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
		loadTypeface();
		
		v = inflater.inflate(R.layout.fragment_search, container, false);
		
		trainTypeContainer	 = (LinearLayout) v.findViewById(R.id.trainTypeContainer);
		depDateContainer	 = (LinearLayout) v.findViewById(R.id.depDateContainer);
		depTimeContainer	 = (LinearLayout) v.findViewById(R.id.depTimeContainer);
		depStationContainer	 = (LinearLayout) v.findViewById(R.id.depStationContainer);
		arrStationContainer	 = (LinearLayout) v.findViewById(R.id.arrStaionContainer);
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
		
		trainTypeTitle.setTypeface(typeface);
		depDateTitle.setTypeface(typeface);
		depTimeTitle.setTypeface(typeface);
		depInfoTitle.setTypeface(typeface);
		arrInfoTitle.setTypeface(typeface);
		favoriteStationSearch.setTypeface(typeface);
		
		trainTypeEdit.setTypeface(typeface);
		depDateEdit.setTypeface(typeface);
		depTimeEdit.setTypeface(typeface);
		depStationEdit.setTypeface(typeface);
		arrStationEdit.setTypeface(typeface);
		searchButton.setTypeface(typeface);
		
		String date = getDate();
		String time = getTime();
		
		trainTypeEdit.setText("전체");	//추가됨 기본값을 전체열차 검색으로
		depDateEdit.setText(date);
		depTimeEdit.setText(time);
		depStationEdit.setText("서울");
		arrStationEdit.setText("부산");
		
		trainTypeContainer.setOnClickListener(layoutClickEye);
		depDateContainer.setOnClickListener(layoutClickEye);
		depTimeContainer.setOnClickListener(layoutClickEye);
		depStationContainer.setOnClickListener(layoutClickEye);
		arrStationContainer.setOnClickListener(layoutClickEye);
		searchButton.setOnClickListener(layoutClickEye);
		favoriteStationSearch.setOnClickListener(layoutClickEye);
		
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
			case R.id.favoriteStaionContainer:
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
				depIntent.putExtra("title", "출발역검색");
				startActivityForResult(depIntent, StationSearchActivity);
				break;
				
			case R.id.arrStaionContainer:
				Intent arrIntent = new Intent(getActivity().getApplicationContext(), StationSearchActivity.class);
				arrIntent.putExtra("title", "도착역검색");
				startActivityForResult(arrIntent, ArrivalStationSearchActivity);
				break;
			}
		}
	};
	
	private void loadTypeface()
	{
		if (typeface == null)
			typeface = Typeface.createFromAsset(getActivity().getAssets(), TYPEFACE_NAME);
	}

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
	 * 아주간단한 요일 문자열을 리턴하는 메소드
	 * @param year		년
	 * @param month		월
	 * @param day		일
	 * @return	요일문자열
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
			return "일요일";
		case 2:
			return "월요일";
		case 3:
			return "화요일";
		case 4:
			return "수요일";
		case 5:
			return "목요일";
		case 6:
			return "금요일";
		default:
			return "토요일";
		}
	}
	
	/**
	 * 아주간단한 요일 문자열을 리턴하는 메소드
	 * @param dayOfWeek	요일 값
	 * @return	요일문자열
	 */
	private String getDayOfWeek(int dayOfWeek)
	{
		switch (dayOfWeek)
		{
		case 1:
			return "일요일";
		case 2:
			return "월요일";
		case 3:
			return "화요일";
		case 4:
			return "수요일";
		case 5:
			return "목요일";
		case 6:
			return "금요일";
		default:
			return "토요일";
		}
	}
	
	public String getTime()
	{
		Calendar c = Calendar.getInstance();
		int chour = c.get(Calendar.HOUR_OF_DAY);
		int cminute = c.get(Calendar.MINUTE);
		/*
		 * 이 부분을 주석처리한 이유는 정확한 분 단위로 검색하지 않고,
		 * 10시 30분이라면 10시로 검색하여 검색 범위를 좀더 넓히기 위함.
		 * 따라서 분 단위는 00분으로 고정
		 */
		
		String selectedTime = null;
		if (chour < 12)
		{
			if (chour == 0)
				chour = 12;
			selectedTime = "오전 " + String.valueOf(chour) + " : " + String.valueOf(cminute);
		} else
		{
			if (chour == 12)
				selectedTime = "오후 " + String.valueOf(chour) + " : " + String.valueOf(cminute);
			else
				selectedTime = "오후 " + String.valueOf(chour - 12) + " : " + String.valueOf(cminute);
		}

		return selectedTime;
	}

	public void showTrainPickerDialog()
	{
		final CharSequence[] trains = { "전체", "KTX", "새마을호", "무궁화호", "통근열차", "누리로", "공항철도", "ITX-새마을", "ITX-청춘" };

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("열차 선택");
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
		alert.setTitle("날짜 선택");
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
					selectedTime = "오전 " + String.valueOf(hour) + " : " + String.valueOf(minute);
				} else
				{
					if (hour == 12)
						selectedTime = "오후 " + String.valueOf(hour) + " : " + String.valueOf(minute);
					else
						selectedTime = "오후 " + String.valueOf(hour - 12) + " : " + String.valueOf(minute);
				}

				depTimeEdit.setText(selectedTime);
			}
		};
		TimePickerDialog alert = new TimePickerDialog(getActivity(), mTimeSetListener, chour, cminute, false);
		alert.setTitle("시간 선택");
		alert.show();
	}
	
	// 즐겨찾는구간 검색 다이얼로그 띄우기
	protected void showFavoriteStationDialog() {
		// TODO Auto-generated method stub

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
		if (train.compareTo("전체") == 0)
			trainNumber = "05";
		else if (train.compareTo("KTX") == 0)
			trainNumber = "00";
		else if (train.compareTo("새마을호") == 0)
			trainNumber = "01";
		else if (train.compareTo("무궁화호") == 0)
			trainNumber = "02";
		else if (train.compareTo("통근열차") == 0)
			trainNumber = "03";
		else if (train.compareTo("누리로") == 0)
			trainNumber = "04";
		else if (train.compareTo("공항철도") == 0)
			trainNumber = "06";
		else if (train.compareTo("ITX-새마을") == 0)
			trainNumber = "08";
		else if (train.compareTo("ITX-청춘") == 0)
			trainNumber = "09";

		return trainNumber;
	}

	public String getDate(String date)
	{
		StringTokenizer st = new StringTokenizer(date, "/");
		String year = st.nextToken();
		String month = st.nextToken();
		String day = st.nextToken().split(" ")[1];	//요일제거

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
		if (time.contains("오전") == true)
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
		} else if (time.contains("오후") == true)
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
			String incodedDep = null;
			String incodedArr = null;
			
			incodedDep = java.net.URLEncoder.encode(dep);
			incodedArr = java.net.URLEncoder.encode(arr);
			
			url = "http://221.166.154.113:8000/searchTrain/?train=" + train + "&date=" + date + "&time=" + time + "&dep="
					+ incodedDep + "&arr=" + incodedArr;
			progressDialog = ProgressDialog.show(getActivity(), "", "잠시 기다려주세요...", true);
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
				Toast.makeText(getActivity().getApplicationContext(), "조회 결과가 없습니다.", Toast.LENGTH_LONG).show();
				return;
			}

			Intent i = new Intent(getActivity().getApplicationContext(), ResultActivity.class);
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
				HttpGet httpPost = new HttpGet(url);
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
