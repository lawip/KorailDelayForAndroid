package jcsla.korail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class ResultJsonParser extends AsyncTask<Void, Void, Void>
{
	Fragment searchFragment;
	ProgressDialog progressDialog;
	
	String url = null;
	boolean isNull = false;

	public ResultJsonParser(Fragment searchFragment, String train, String date, String time, String dep, String arr)
	{
		this.searchFragment = searchFragment;
		
		String incodedDep = null;
		String incodedArr = null;
		
		incodedDep = java.net.URLEncoder.encode(dep);
		incodedArr = java.net.URLEncoder.encode(arr);
		
		url = "http://221.166.154.113:8080/searchTrain/?train=" + train + "&date=" + date + "&time=" + time + "&dep=" + incodedDep + "&arr=" + incodedArr;
		progressDialog = ProgressDialog.show(searchFragment.getActivity(), "", "잠시 기다려주세요...", true);
	}

	@Override
	protected Void doInBackground(Void... params)
	{
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
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
				String type = jsonObject.getString("train_type");
				String number = jsonObject.getString("train_number");
				String depCode = jsonObject.getString("dep_code");
				String depDate = jsonObject.getString("dep_date");
				String depTime = jsonObject.getString("dep_time");
				String arrCode = jsonObject.getString("arr_code");
				String arrDate = jsonObject.getString("arr_date");
				String arrTime = jsonObject.getString("arr_time");
				String location = jsonObject.getString("train_location");
				String delayTime = jsonObject.getString("train_delay_time");
				Train t = new Train(type, number, depCode, depDate, depTime, arrCode, arrDate, arrTime, location, delayTime);
				Variable.resultList.add(t);
			}
		}
		catch (JSONException e)
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
			Toast.makeText(searchFragment.getActivity().getApplicationContext(), "조회 결과가 없습니다.", Toast.LENGTH_LONG).show();
			return;
		}

		Intent i = new Intent(searchFragment.getActivity().getApplicationContext(), ResultActivity.class);
		searchFragment.startActivity(i);
	}
}