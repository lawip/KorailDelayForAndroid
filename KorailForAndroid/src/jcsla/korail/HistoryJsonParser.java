package jcsla.korail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class HistoryJsonParser extends AsyncTask<Void, Void, Train>
{
	String date;
	String train;
	
	Train t;
	
	String url = null;
	
	public HistoryJsonParser(String date, String train, Train t)
	{
		this.date = date;
		this.train = train;
		
		this.t = t;
		
		url = "http://115.71.236.224:8082/searchDelay/?date=" + date + "&" + "train=" + train;
	}

	@Override
	protected Train doInBackground(Void... params) {
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
		JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);
			
		// ���ΰ�ħ�̸� progressDialog ����ְ� onPostExecute���� ���� �����ð� �ѷ��ֱ�
		try
		{
			for (int i = 0; i < jsonArray.length(); )
			{
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				String trainLocation = jsonObject.getString("train_location");
				String trainDelayTime = jsonObject.getString("train_delay_time");
				
				t.setTrainStatus(trainLocation);
				t.setTrainDelayStatus(trainDelayTime);
				
				return t;
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Train result) {
		
		
		super.onPostExecute(result);
	}
}
