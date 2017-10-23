package chapter13;

import java.io.FileInputStream;
import java.io.IOException;

public class Sec04_01_PrintWriterDemo {

	private static final int EOF = -1;

	public static void main(String[] args) throws IOException {

		FileInputStream fs = null;
		int i = ' ';
		String s = "";

		if (args.length == 1) 
			System.out.println("File Name: " + args[0]);
		else {
			System.out.println("Entered more than one file name");
			return;
		}

		fs = new FileInputStream("resources/" + args[0]);

		do {
			s += (char) i;
			i = fs.read();
		} while (i != EOF);

		fs.close();

		System.out.println(s.trim().toUpperCase());
	}

}
