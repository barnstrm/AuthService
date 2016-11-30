package com.cognetyx.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	// Simple static method to read the text of the key file and use for signing JWT
	public static String getTheKey()
	{
		BufferedReader br = null;
		String everything = null;
		try {
			br = new BufferedReader(new FileReader("key.txt"));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} catch (FileNotFoundException fne) {
			System.out.println("Could not file the key file.....");
		} catch (IOException ioe) {
			System.out.println("Encountered IO Exception reading key file.....");
		} finally {
		    try { br.close(); } catch(IOException i) {}
		}
		return everything;
	}


}
