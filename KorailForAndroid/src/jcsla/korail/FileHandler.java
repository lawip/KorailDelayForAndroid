package jcsla.korail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FileHandler
{
	public static File makeDirectory(String dirPath)
	{
		File dir = new File(dirPath);
		if (!dir.exists())
			dir.mkdirs();

		return dir;
	}
	
	public static File makeFile(File dir, String filePath)
	{
		File file = null;
		
		if (dir.isDirectory())
		{
			file = new File(filePath);
			if (file != null && !file.exists())
			{
				try
				{
					file.createNewFile();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return file;
	}
	
	public static boolean deleteFile(File file)
	{
		boolean result;
		
		if (file != null && file.exists())
		{
			file.delete();
			result = true;
		}
		else
			result = false;
		
		return result;
	}
	
	public static boolean writeFile(File file , byte[] fileContent)
	{
		FileOutputStream fos;
		boolean result;

		if (file != null && file.exists() && fileContent != null)
		{
			try
			{
				fos = new FileOutputStream(file, true);
				try
				{
					fos.write(fileContent);
					fos.flush();
					fos.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			result = true;
		}
		else
			result = false;

		return result;
    }
	
	public static ArrayList<String> readFile(File file)
	{
		ArrayList<String> resultArrayList = null;

		if (file != null && file.exists())
		{
			try
			{
				FileInputStream fis = new FileInputStream(file);
				BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis));
				resultArrayList = new ArrayList<String>();
				String str = null;
				while ((str = bufferReader.readLine()) != null)
					resultArrayList.add(str);
				
				fis.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		return resultArrayList;
    }
}
