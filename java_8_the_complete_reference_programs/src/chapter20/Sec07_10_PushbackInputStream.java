package chapter20;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

public class Sec07_10_PushbackInputStream {

	private static final int EOF = -1;

	public static void main(String[] args) {

		String path = "E:/Data/Documents/Programs workspace/Java/Eclipse_neon/java_8_the_complete_reference_programs/resources";
		
		Vector<String> v = new Vector<String>();
		
		v.addElement(path + "/file1.txt");
		v.addElement(path + "/file2.txt");
		v.addElement(path + "/file3.txt");
		
		InputStreamEnumerator ise = new InputStreamEnumerator(v);
		
		try(SequenceInputStream sis = new SequenceInputStream(ise);) {
			
			int c;
			while((c = sis.read()) != EOF){
				System.out.print((char) c);
			}
		} catch (IOException e) {} catch (NullPointerException e) {}
		
	}
}

class InputStreamEnumerator implements Enumeration<FileInputStream>{
	
	private Enumeration<String> files;

	public InputStreamEnumerator(Vector<String> v) {
		files = v.elements();
	}

	@Override
	public boolean hasMoreElements() {
		return files.hasMoreElements();
	}

	@Override
	public FileInputStream nextElement() {
		try {
			return new FileInputStream(files.nextElement());
		} catch (FileNotFoundException e) {}
		return null;
	}
	
}








