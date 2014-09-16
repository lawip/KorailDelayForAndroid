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
	public static File makeDirectory(String dir_path)
	{
		File dir = new File(dir_path);
		if (!dir.exists())
			dir.mkdirs();

		return dir;
	}
	
	public static File makeFile(File dir, String file_path)
	{
		File file = null;
		
		if (dir.isDirectory())
		{
			file = new File(file_path);
			if (file != null && !file.exists())
			{
				try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
		else {
			result = false;
		}
		
		return result;
	}
	
	public static boolean writeFile(File file , byte[] file_content)
	{
		FileOutputStream fos;
        boolean result;
        
        if(file!=null && file.exists() && file_content!=null) {
            try {
                fos = new FileOutputStream(file, true);
                try {
                    fos.write(file_content);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            result = true;
        }
        else {
            result = false;
        }
        
        return result;
    }
	
	public static ArrayList<String> readFile(File file)
	{
		ArrayList<String> resultArrayList = null;
		
        if(file!=null && file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(fis));
                resultArrayList = new ArrayList<String>();
                String str = null;
                while( (str = bufferReader.readLine()) != null ) {
                	resultArrayList.add(str);
                }
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return resultArrayList;
    }
}
