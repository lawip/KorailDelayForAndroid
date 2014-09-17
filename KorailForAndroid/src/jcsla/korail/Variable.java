package jcsla.korail;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Environment;

public class Variable
{
	public static final String TYPEFACE_NAME = "BM-HANNA.ttf";
	public static Typeface typeface = null;
	
	public static String DIRECTORY_NAME = Environment.getExternalStorageDirectory() + "/KORAIL_DELAY/";
	public static String FAVORITE_STATIONS_FILE = "favorite_stations.txt";
	public static String HISTORY_FILE = "history.txt";
	
	public static ArrayList<Train> resultList = new ArrayList<Train>();
	public static ArrayList<Train> historyList = new ArrayList<Train>();
	
	public static ArrayList<String> favoriteStationsList;
	public static ArrayList<String> tempHistoryList = new ArrayList<String>();
	
	public static String refreshHistoryTime = null;
}
