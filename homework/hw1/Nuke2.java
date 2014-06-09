package hw1;

import java.io.*;

public class Nuke2 {
	public static void main(String[] args) throws Exception{
		BufferedReader br;
		String inputLine;
				
		br=new BufferedReader(new InputStreamReader(System.in));
		inputLine=br.readLine();
		
		String rear=inputLine.substring(2);
		String newstr=inputLine.substring(0,1)+rear;
		System.out.println(newstr);
	}
}
