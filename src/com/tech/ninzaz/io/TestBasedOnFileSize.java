package com.tech.ninzaz.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class TestBasedOnFileSize {

	public static void main(String... args) throws IOException {
	    for (int mb : new int[]{50, 100, 250, 500})
	        testFileSize(mb);
	}

	private static void testFileSize(int mb) throws IOException {
	    File file = File.createTempFile("test", ".txt");
	    file.deleteOnExit();
	    char[] chars = new char[1024];
	    Arrays.fill(chars, 'A');
	    String longLine = new String(chars);
	    long start1 = System.nanoTime();
	    PrintWriter pw = new PrintWriter(new FileWriter(file));
	    for (int i = 0; i < mb * 1024; i++)
	        pw.println(longLine);
	    pw.close();
	    long time1 = System.nanoTime() - start1;
	    System.out.printf("Took %.3f seconds to write to a %d MB, file rate: %.1f MB/s%n",
	            time1 / 1e9, file.length() >> 20, file.length() * 1000.0 / time1);

	    long start2 = System.nanoTime();
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String line;
	    while( (line = br.readLine()) != null ) {
	    }
	    br.close();
	    long time2 = System.nanoTime() - start2;
	    System.out.printf("Took %.3f seconds to read to a %d MB file, rate: %.1f MB/s%n",
	            time2 / 1e9, file.length() >> 20, file.length() * 1000.0 / time2);
	    
	    long start = System.nanoTime();
	    byte[] bytes = new byte[32*1024];
	    FileInputStream fis = new FileInputStream(file);
	    int len;
	    while((len = fis.read(bytes)) > 0);
	    long time3 = System.nanoTime() - start;
	    System.out.printf("Took %.3f seconds to read using FIS to a %d MB file, rate: %.1f MB/s%n", 
	    		time3/1e9, file.length() >> 20, file.length() * 1000.0 / time2);
	    
	    file.delete();
	}
	
}
