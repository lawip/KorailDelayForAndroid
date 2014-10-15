package jcsla.korail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class StationJsonParser extends AsyncTask<Void, Void, Void>
{
	String url;
	
	public StationJsonParser()
	{
		url = "http://221.166.154.113:8080/searchStations/";
	}

	@Override
	protected Void doInBackground(Void... params) {
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
		JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);
		if (jsonArray == null)
		{
			return null;
		}
		
		try
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String stn_cd = jsonObject.getString("stn_cd");
				String stn_nm = jsonObject.getString("stn_nm");
				String longitude = jsonObject.getString("longitude");
				String latitude = jsonObject.getString("latitude");
				
				Station s = new Station(stn_cd, stn_nm, longitude, latitude);
				Variable.stationList.add(s);
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
		// progress dialog?
	}
}
