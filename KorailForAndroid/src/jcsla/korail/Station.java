package jcsla.korail;

public class Station
{
	private String stn_cd;
	private String stn_nm;
	private String longitude;
	private String latitude;
	
	public Station(String stn_cd, String stn_nm, String longitude, String latitude)
	{
		setStnCd(stn_cd);
		setStnNm(stn_nm);
		setLongitude(longitude);
		setLatitude(latitude);
	}

	public void setStnCd(String stn_cd)
	{
		this.stn_cd = stn_cd;
	}
	public String getStnCd()
	{
		return stn_cd;
	}

	public void setStnNm(String stn_nm)
	{
		this.stn_nm = stn_nm;
	}
	public String getStnNm()
	{
		return stn_nm;
	}

	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	public String getLongitude()
	{
		return longitude;
	}

	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	public String getLatitude()
	{
		return latitude;
	}
}
