package jcsla.korail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class SearchJsonParser extends AsyncTask<Void, Void, Void>
{
	Fragment searchFragment;
	ProgressDialog progressDialog;
	
	String url = null;
	boolean isNull = false;

	public SearchJsonParser(Fragment searchFragment, String train, String date, String time, String dep, String arr)
	{
		this.searchFragment = searchFragment;
		
		String incodedDep = null;
		String incodedArr = null;
		
		incodedDep = java.net.URLEncoder.encode(dep);
		incodedArr = java.net.URLEncoder.encode(arr);
		
		url = "http://115.71.236.224:8082/searchTrain/?train=" + train + "&date=" + date + "&time=" + time + "&dep="
				+ incodedDep + "&arr=" + incodedArr;
		progressDialog = ProgressDialog.show(searchFragment.getActivity(), "", "��� ��ٷ��ּ���...", true);
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
			Toast.makeText(searchFragment.getActivity().getApplicationContext(), "��ȸ ����� �����ϴ�.", Toast.LENGTH_LONG).show();
			return;
		}

		Intent i = new Intent(searchFragment.getActivity().getApplicationContext(), ResultActivity.class);
		searchFragment.startActivity(i);
	}
}