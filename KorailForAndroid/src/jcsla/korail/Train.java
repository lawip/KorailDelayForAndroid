package jcsla.korail;

public class Train 
{
	private String type;
	private String number;
	private String depCode;
	private String depDate;
	private String depTime;
	private String arrCode;
	private String arrDate;
	private String arrTime;
	private String location;
	private String delayTime;
	
	public Train(String type, String number, String depCode, String depDate, String depTime, String arrCode, String arrDate, String arrTime, String location, String delayTime)
	{
		setType(type);
		setNumber(number);
		setDepCode(depCode);
		setDepDate(depDate);
		setDepTime(depTime);
		setArrCode(arrCode);
		setArrDate(arrDate);
		setArrTime(arrTime);
		setLocation(location);
		setDelayTime(delayTime);
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	public String getType()
	{
		return type;
	}
	public String getName()
	{
		String result = "";
		
		if(type.compareTo("00") == 0 || type.compareTo("07") == 0)
			result = "KTX";
		else if(type.compareTo("01") == 0)
			result = "새마을호";
		else if(type.compareTo("02") == 0)
			result = "무궁화호";
		else if(type.compareTo("03") == 0)
			result = "통근열차";
		else if(type.compareTo("04") == 0)
			result = "누리로";
		else if(type.compareTo("06") == 0)
			result = "공항철도";
		else if(type.compareTo("08") == 0)
			result = "ITX-새마을";
		else if(type.compareTo("09") == 0)
			result = "ITX-청춘";
		
		return result.trim();
	}
	
	public void setNumber(String number)
	{
		this.number = number;
	}
	public String getNumber()
	{
		return number.trim();
	}
	
	public void setDepCode(String depCode)
	{
		this.depCode = depCode;
	}
	public String getDepCode()
	{
		return depCode;
	}
	public String getDepName()
	{
		String result = null;
		
		result = Stations.number_name_stations.get(depCode);
		
		return result.trim();
	}
	
	
	public void setDepDate(String depDate)
	{
		this.depDate = depDate;
	}
	public String getDepDate()
	{
		return depDate;
	}
	
	public void setDepTime(String depTime)
	{
		this.depTime = depTime;
	}
	public String getDepTime()
	{
		return depTime;
	}
	public String getProcessedDepTime()
	{
		String result;
		
		String exceptSeconds = depTime.substring(0, 4);
		StringBuffer sb = new StringBuffer(exceptSeconds);
		sb.insert(2, ":");
		result = sb.toString();
		
		return result.trim();
	}
	
	
	public void setArrCode(String arrCode)
	{
		this.arrCode = arrCode;
	}
	public String getArrCode()
	{
		return arrCode;
	}
	public String getArrName()
	{
		String result = null;
		
		result = Stations.number_name_stations.get(arrCode);
		
		return result.trim();
	}
	
	
	public void setArrDate(String arrDate)
	{
		this.arrDate = arrDate;
	}
	public String getArrDate()
	{
		return arrDate;
	}
	
	public void setArrTime(String arrTime)
	{
		this.arrTime = arrTime;
	}
	public String getArrTime()
	{
		return arrTime;
	}
	public String getProcessedArrTime()
	{
		String result;
		
		String exceptSeconds = arrTime.substring(0, 4);
		StringBuffer sb = new StringBuffer(exceptSeconds);
		sb.insert(2, ":");
		result = sb.toString();
		
		return result.trim();
	}
	
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	public String getLocation()
	{
		return location.trim();
	}
	
	public void setDelayTime(String delayTime)
	{
		this.delayTime = delayTime;
	}
	public String getDelayTime()
	{
		return delayTime.trim();
	}
}
