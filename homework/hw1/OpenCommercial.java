package hw1;

import java.net.*;
import java.io.*;

/** A class that provides a main function to read five lines of a commercial web page and print them in reverse order, given the name of a company.
 * *
 * @author Justin Tian
 *
 */

public class OpenCommercial {
	public static void main(String[] args) throws Exception {
		BufferedReader keyboard;
		String inputLine;
		
		keyboard=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the name of a company (w/o spaces):");
		System.out.flush();
		
		inputLine=keyboard.readLine();
		URL u=new URL("http://www."+inputLine+".com/");
		InputStream ins=u.openStream();
		InputStreamReader isr=new InputStreamReader(ins);
		BufferedReader web=new BufferedReader(isr);
		
		String str1=web.readLine();
		String str2=web.readLine();
		String str3=web.readLine();
		String str4=web.readLine();
		String str5=web.readLine();
		
		System.out.println(str5);
		System.out.println(str4);
		System.out.println(str3);
		System.out.println(str2);
		System.out.println(str1);		
	}
}
