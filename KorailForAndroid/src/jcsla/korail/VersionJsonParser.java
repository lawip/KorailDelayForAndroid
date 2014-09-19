package jcsla.korail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class VersionJsonParser extends AsyncTask<Void, Void, String>
{
	String url;
	
	public VersionJsonParser()
	{
		url = "http://115.71.236.224:8082/version/";
	}
	
	@Override
	protected String doInBackground(Void... params) {
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
		JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);

		// ���ΰ�ħ�̸� progressDialog ����ְ� onPostExecute���� ���� �����ð� �ѷ��ֱ�
		try
		{
			for (int i = 0; i < jsonArray.length(); )
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String version = jsonObject.getString("version");
				
				return version.trim();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
