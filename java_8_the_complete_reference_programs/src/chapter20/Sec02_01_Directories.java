/**
 * CHAPTER 20 - FILE - DIRECTORIES
 * */

package chapter20;

import java.io.File;
import java.io.FileNotFoundException;

public class Sec02_01_Directories {

	public static void main(String[] args) throws FileNotFoundException {

		String dirPath = "/Data/Pictures/";

		File dir = new File(dirPath);
		String[] list = dir.list();

		for (String s : list) {
			File f = new File(dirPath + s);
			if (f.isDirectory())
				System.out.println(s + " is a directory");
			else if (f.isFile())
				System.out.println(s + " is a file");
		}

	}

}
