package chapter13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sec04_02_PrintWriterDemo2 {

	private static final int EOF = -1;

	public static void main(String[] args) {

		FileInputStream fis = null;
		FileOutputStream fos = null;
		int i = ' ';

		if (args.length == 2) {
			System.out.println("File Name: " + args[0]);
			System.out.println("File Name: " + args[1]);
		} else {
			System.out.println("Entered more or less file names");
			return;
		}

		try {
			fis = new FileInputStream("resources/"+args[0]);
			fos = new FileOutputStream("resources/"+args[1]);
		} catch (FileNotFoundException e) {}

		try {
			do {
				i = fis.read();
				if (i != EOF)fos.write(i);
			} while (i != EOF);
		} catch (IOException e) {}
		
		finally{
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {}
			try {
				if(fos != null) fos.close();
			} catch (IOException e) {}
		}

	}

}
