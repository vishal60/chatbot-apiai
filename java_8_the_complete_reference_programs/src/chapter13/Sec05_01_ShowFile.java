/**
 * Chapter 13 - I/O Basics - Automatically Closing a File
 * */

package chapter13;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Sec05_01_ShowFile {

	private static final int EOF = -1;

	public static void main(String[] args) throws IOException {

		if (args.length == 2) {
			System.out.println("File Name: " + args[0]);
			System.out.println("File Name: " + args[1]);
		} else {
			System.out.println("Entered more or less file names");
			return;
		}

		try (FileInputStream fis = new FileInputStream("resources/" + args[0]);
				FileOutputStream fos = new FileOutputStream("resources/" + args[1])) {

			int i;

			do {
				i = fis.read();
				if (i != EOF)
					fos.write(i);
			} while (i != EOF);

		} catch (Exception e) {
		}

	}

}
