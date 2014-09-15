package jcsla.korail;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class StationsAdapter extends ArrayAdapter<String>
{
	Context context;
	ArrayList<String> stationList;
	ArrayList<String> temp;

	public StationsAdapter(Context context, int resource, ArrayList<String> objects) {
		super(context, resource, objects);
		this.context = context;
		stationList = objects;
		temp = new ArrayList<String>();
		temp.addAll(stationList);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.station_row, null);
		}
		
		String station = stationList.get(position);
		if(station != null)
		{
			TextView stationTextView = (TextView) v.findViewById(R.id.stationItem);
			stationTextView.setText(station.toString());
			stationTextView.setTypeface(TypefaceHelper.typeface);
		}
		
		return v;
	}
	
	// SoundSearcher.matchString("검색할대상","검색어");
	// ex)
	// SoundSearcher.matchString("안녕하세요","ㅇㄴ하"); //true
	// SoundSearcher.matchString("반갑습니다","ㅂㄱ습ㄴ"); //true
	// SoundSearcher.matchString("안녕히가세요","ㅇㄴㅎㅎ"); //false
	public void filter(String charText)
	{
		stationList.clear();
		if (charText.length() == 0) {
			stationList.addAll(temp);
		}
		else {
			for (String str : temp) {
				if(SoundSearcher.matchString(str, charText))
					stationList.add(str);
			}
		}
		notifyDataSetChanged();
	}
}
