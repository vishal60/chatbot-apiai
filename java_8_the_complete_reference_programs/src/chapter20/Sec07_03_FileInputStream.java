package chapter20;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sec07_03_FileInputStream {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		String path = "E:/Data/Documents/Programs workspace/Java/Eclipse_neon/java_8_the_complete_reference_programs/src/chapter20";
		String fileName = "/Sec7_3_FileInputStrem.java";
		
		int size;
		
		try (FileInputStream fis = new FileInputStream(path + fileName)) {
			
			System.out.println("Total available bytes: " + (size = fis.available()));
			
			int n = size/10;
			
			System.out.println("First " + n + " bytes of the file one read() at a time");
			
			for(int i=0; i<n; i++)
				System.out.print((char) fis.read());
					
			System.out.println("\nstill available: " + fis.available());
			
			System.out.println("Reading the next " + n + " with one read(b[])");
			
			byte[] buffer = new byte[n];
			
			if(fis.read(buffer) != n) System.out.println("error reading the file");
			
			String s = new String(buffer, 0, n);
			System.out.println(s);
			
			System.out.println("still available: " + fis.available());
			
			System.out.println("Skipping half of remaining bytes with skip()");
			
			System.out.println("bytes skipped: " + fis.skip(size/2));
			
			System.out.println("Reading " + n + " into the end of array");
			
			byte[] b = new byte[n];
			if(fis.read(b, n/2, n/2) != n/2) System.out.println("error reading the file");
			
			System.out.println(new String(b, 0, b.length));
			
			System.out.println("still available: " + fis.available());
			
		}catch(Exception e) {}

	}
}
