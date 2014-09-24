package jcsla.korail;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class HistoryAdapter extends ArrayAdapter<Train>
{
	Context context;
	
	public HistoryAdapter(Context context, int resource, List<Train> objects) {
		super(context, resource, objects);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;
		
		if (v == null)
		{
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.result_row, null);
		}
		
		Train t = Variable.historyList.get(position);
		
		if (t != null)
		{
			TextView type = (TextView) v.findViewById(R.id.itemType);
			TextView number = (TextView) v.findViewById(R.id.itemNumber);
			TextView departureStation = (TextView) v.findViewById(R.id.itemDepStation);
			TextView departureTime = (TextView) v.findViewById(R.id.itemDepTime);
			TextView arrivalStation = (TextView) v.findViewById(R.id.itemArrStation);
			TextView arrivalTime = (TextView) v.findViewById(R.id.itemArrTime);
			TextView location = (TextView) v.findViewById(R.id.itemLocation);
			TextView delayTime = (TextView) v.findViewById(R.id.itemDelayTime);
			
			String delayString = t.getDelayTime();
			if(delayString.compareTo("0 Ка") == 0)
				highlightView(delayTime, R.color.green);
			else
				highlightView(delayTime, R.color.orange);
			
			type.setText(t.getName());
			number.setText(t.getNumber());
			departureStation.setText(t.getDepCode());
			departureTime.setText(t.getDepTime());
			arrivalStation.setText(t.getArrCode());
			arrivalTime.setText(t.getArrTime());
			location.setText(t.getLocation());
			delayTime.setText(delayString);
			
			type.setTypeface(Variable.typeface);
			number.setTypeface(Variable.typeface);
			departureStation.setTypeface(Variable.typeface);
			departureTime.setTypeface(Variable.typeface);
			arrivalStation.setTypeface(Variable.typeface);
			arrivalTime.setTypeface(Variable.typeface);
			location.setTypeface(Variable.typeface);
			delayTime.setTypeface(Variable.typeface);
		}
		
		return v;
	}
	
	private void highlightView(TextView v, int colorID)
	{
		int color = v.getResources().getColor(colorID);
		v.setTextColor(color);
	}
}
