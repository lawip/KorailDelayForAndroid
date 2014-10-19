package jcsla.korail;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultAdapter extends ArrayAdapter<Train>
{
	Context context;

	public ResultAdapter(Context context, int resource, ArrayList<Train> objects)
	{
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
		
		Train t = Variable.resultList.get(position);
		
		if (t != null)
		{
			TextView trainType = (TextView) v.findViewById(R.id.itemType);
			TextView trainNumber = (TextView) v.findViewById(R.id.itemNumber);
			TextView departureStation = (TextView) v.findViewById(R.id.itemDepStation);
			TextView departureTime = (TextView) v.findViewById(R.id.itemDepTime);
			TextView arrivalStation = (TextView) v.findViewById(R.id.itemArrStation);
			TextView arrivalTime = (TextView) v.findViewById(R.id.itemArrTime);
			TextView location = (TextView) v.findViewById(R.id.itemLocation);
			TextView delay = (TextView) v.findViewById(R.id.itemDelayTime);
			
			String delayString = t.getDelayTime();
			if(delayString.compareTo("0 Ка") == 0)
				highlightView(delay, R.color.green);
			else
				highlightView(delay, R.color.orange);

			trainType.setText(t.getName());
			trainNumber.setText(t.getNumber());
			departureStation.setText(t.getDepName());
			departureTime.setText(t.getProcessedDepTime());
			arrivalStation.setText(t.getArrName());
			arrivalTime.setText(t.getProcessedArrTime());
			location.setText(t.getLocation());
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
	
	public void notifyOnChange()
	{
		this.notifyDataSetChanged();
	}
}
