package jcsla.korail;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FavoriteStationsAdapter extends ArrayAdapter<String>
{
	Context context;
	
	ArrayList<String> favoriteStationList;

	public FavoriteStationsAdapter(Context context, int resource, ArrayList<String> objects) {
		super(context, resource, objects);
		this.context = context;
		favoriteStationList = objects;
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
		
		String favoriteStation = favoriteStationList.get(position);
		if(favoriteStation != null)
		{
			TextView stationTextView = (TextView) v.findViewById(R.id.stationItem);
			stationTextView.setText(favoriteStation.toString());
			stationTextView.setTypeface(Variable.typeface);
		}
		
		return v;
	}
}
