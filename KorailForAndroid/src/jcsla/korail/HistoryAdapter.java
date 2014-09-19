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
			String delayString = t.getTrainDelayStatus();
			
			TextView trainType = (TextView) v.findViewById(R.id.itemTrainType);
			TextView trainNumber = (TextView) v.findViewById(R.id.itemTrainNumber);
			TextView departureStation = (TextView) v.findViewById(R.id.itemDepStation);
			TextView departureTime = (TextView) v.findViewById(R.id.itemDepTime);
			TextView arrivalStation = (TextView) v.findViewById(R.id.itemArrStation);
			TextView arrivalTime = (TextView) v.findViewById(R.id.itemArrTime);
			TextView location = (TextView) v.findViewById(R.id.itemStatus);
			TextView delay = (TextView) v.findViewById(R.id.itemDelayTime);
			
			if(delayString.compareTo("0 ��") == 0)
				 highlightView(delay, R.color.green);
			else highlightView(delay, R.color.orange);
			
			trainType.setText(t.getTrainName());
			trainNumber.setText(t.getTrainNumber());
			departureStation.setText(t.getDepName());
			departureTime.setText(t.getProcessedDepTime());
			arrivalStation.setText(t.getArrName());
			arrivalTime.setText(t.getProcessedArrTime());
			location.setText(t.getTrainStatus());
			delay.setText(delayString);
			
			trainType.setTypeface(Variable.typeface);
			trainNumber.setTypeface(Variable.typeface);
			departureStation.setTypeface(Variable.typeface);
			departureTime.setTypeface(Variable.typeface);
			arrivalStation.setTypeface(Variable.typeface);
			arrivalTime.setTypeface(Variable.typeface);
			location.setTypeface(Variable.typeface);
			delay.setTypeface(Variable.typeface);
		}
		
		return v;
	}
	
	private void highlightView(TextView v, int colorID)
	{
		int color = v.getResources().getColor(colorID);
		v.setTextColor(color);
	}
	
	public static void setNotifyDateSetChanged() {
		setNotifyDateSetChanged();
	}
}
