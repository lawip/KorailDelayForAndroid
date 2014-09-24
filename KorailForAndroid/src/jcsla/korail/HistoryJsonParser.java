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
		
		url = "http://192.168.25.3:8080/searchDelay/?date=" + date + "&" + "train=" + train;
	}

	@Override
	protected Train doInBackground(Void... params) {
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
		JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);
		
		try
		{
			for (int i = 0; i < jsonArray.length(); )
			{
				JSONObject jsonObject = jsonArray.getJSONObject(0);
				String trainLocation = jsonObject.getString("train_location");
				String trainDelayTime = jsonObject.getString("train_delay_time");
				
				t.setLocation(trainLocation);
				t.setDelayTime(trainDelayTime);
				
				return t;
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
