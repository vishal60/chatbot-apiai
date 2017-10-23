package chapter20;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sec07_04_FileOutputStream {

	public static void main(String[] args) throws IOException {

		String pathName = "E:/Data/Documents/Programs workspace/Java/Eclipse_neon/java_8_the_complete_reference_programs/resources";
		String fileName = "/myfile.txt";
		
		File file = new File(pathName + fileName);
		
		byte[] buffer = null;
		try (FileInputStream fis = new FileInputStream(file);) {			
			buffer = new byte[fis.available()];
			fis.read(buffer);
		} catch (Exception e1) {}
		
		String source = new String(buffer, 0, buffer.length);
		byte[] bufferOut = source.getBytes();
		
		System.out.println(source);
		
		try(FileOutputStream f1 = new FileOutputStream(pathName + "/file1.txt"); 
			FileOutputStream f2 = new FileOutputStream(pathName + "/file2.txt"); 
			FileOutputStream f3 = new FileOutputStream(pathName + "/file3.txt");){
			
			// file1
			for(int i=bufferOut.length-1; i>=0; i--)
				f1.write(bufferOut[i]);
			
			//file2
			f2.write(bufferOut);
			
			//file3
			f3.write(bufferOut, 3, 10);
			
		}catch(Exception e){}
		
	}
}
