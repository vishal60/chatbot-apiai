package chapter20;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sec07_08_BufferedByteStreams {

	private static final int EOF = -1;

	public static void main(String[] args) {

		String pathName = "E:/Data/Documents/Programs workspace/Java/Eclipse_neon/java_8_the_complete_reference_programs/resources";
		String fileName = "/myFileOutput.txt";
		
		File file = new File(pathName + fileName);
		
		String source = "This is a &copy; copyright symbol but this is &copy not";
		
		byte[] buf = source.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);
		
		
		try(BufferedInputStream bis = new BufferedInputStream(bais);
			FileOutputStream fos = new FileOutputStream(file);
			BufferedOutputStream bos = new BufferedOutputStream(fos)) {
			
			int c;
			boolean marked = false;
			while((c = bis.read()) != EOF){
				switch(c) {
				
				case '&':
					if (!marked) {
						marked = true;
						bis.mark(32);
					} else 
						System.out.print((char) c);
					break;
					
				case ';':
					if(marked){
						marked = false;
						System.out.print("(C)");
					} else 
						System.out.print((char) c);
					break;
					
				case ' ':
					if(marked){
						marked = false;
						bis.reset();
						System.out.print("&");
					} else 
						System.out.print((char) c);
					break;
					
				default:
					if (!marked)
						System.out.print((char) c);
							
				}
			}
			
			//write source string to myFileOutput
			byte[] buf2 = source.getBytes();
			bos.write(buf2);
			
		} catch (IOException e) {}
		
		
		
	}
}
