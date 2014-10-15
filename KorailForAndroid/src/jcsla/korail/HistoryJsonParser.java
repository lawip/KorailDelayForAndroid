package jcsla.korail;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

public class HistoryJsonParser extends AsyncTask<Void, Void, ArrayList<Train>>
{
//	Fragment historyFragment;
//	ProgressDialog progressDialog;
	
	ArrayList<Train> historyList;
	
	String url = null;
	
	public HistoryJsonParser(ArrayList<Train> historyList)
	{
//		if(historyFragment instanceof Fragment)
//		{
//			this.historyFragment = (Fragment) historyFragment;
			this.historyList = historyList;
			
			String date = this.historyList.get(0).getDepDate();
			url = "http://221.166.154.113:8080/searchDelay/?date=" + date;
			
			String temp = "";
			for(int i=0 ; i<this.historyList.size() ; i++) {
				temp = temp + "&" + "train_number=" + historyList.get(i).getNumber();
			}
			
			url = url + temp;
//			progressDialog = ProgressDialog.show(((Fragment) historyFragment).getActivity(), "", "잠시 기다려주세요...", true);
//		}
	}

	@Override
	protected ArrayList<Train> doInBackground(Void... params) {
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
		JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);
		
		try
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String location = jsonObject.getString("train_location");
				String delayTime = jsonObject.getString("train_delay_time");
				String number = jsonObject.getString("train_number");
				
				for(int j=0 ; j<historyList.size() ; j++) {
					if(number.compareTo(historyList.get(j).getNumber()) == 0) {
						historyList.get(j).setLocation(location);
						historyList.get(j).setDelayTime(delayTime);
					}
				}
			}
			return historyList;
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(ArrayList<Train> result) {
//		progressDialog.dismiss();
		super.onPostExecute(result);
	}
}