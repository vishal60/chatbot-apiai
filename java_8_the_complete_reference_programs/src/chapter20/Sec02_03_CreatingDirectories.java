/**
 * CHAPTER 20 - FILE - DIRECTORIES
 * */

package chapter20;

import java.io.File;
import java.io.FileNotFoundException;

public class Sec02_03_CreatingDirectories {

	public static void main(String[] args) throws FileNotFoundException {

		String dirPath = "/Data/Pictures/c/core/collections";

		File dir = new File(dirPath);
		
		dir.mkdirs();	
	}

}
