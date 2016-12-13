package alvinEditor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyCode 
{		
		MyCode(int c)
		{
			if(c==1)
			{
				try {
	            Process p = Runtime.getRuntime().exec("cmd.exe /C start javac mine.java");
	            
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
	        else
	        {
		        try 
		        {
		            Process p = Runtime.getRuntime().exec("cmd.exe /C start java mine");
		        
		        } 
		        catch (IOException e) 
		        {
		            e.printStackTrace();
		        }
	        }
		}	
}