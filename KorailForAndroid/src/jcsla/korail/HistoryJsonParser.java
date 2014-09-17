package jcsla.korail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class HistoryJsonParser extends AsyncTask<Void, Void, Void>
{
	String date;
	String train;
	String url = null;
	
	public HistoryJsonParser(String date, String train)
	{
		this.date = date;
		this.train = train;
		
		url = "http://115.71.236.224:8082/searchDelay/?date=" + date + "&" + "train=" + train;
	}

	@Override
	protected Void doInBackground(Void... params) {
		JsonParserHelper jsonParserHelper = new JsonParserHelper();
		JSONArray jsonArray = jsonParserHelper.getJSONFromURL(url);

		// ���ΰ�ħ�̸� progressDialog ����ְ� onPostExecute���� ���� �����ð� �ѷ��ֱ�
		try
		{
			for (int i = 0; i < jsonArray.length(); i++)
			{
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String trainLocation = jsonObject.getString("train_location");
				String trainDelayTime = jsonObject.getString("train_delay_time");
				
				for(int j=0 ; j<Variable.historyList.size() ; j++)
				{
					Train t = Variable.historyList.get(j);
					if(date.compareTo(t.getDepDate())==0 && train.compareTo(t.getTrainNumber())==0) {
						t.setTrainStatus(trainLocation);
						t.setTrainDelayStatus(trainDelayTime);
					}
				}
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
		// ���⼭ ����Ʈ�� ����?
		//HistoryAdapter.setNotifyDateSetChanged();
	}
}
