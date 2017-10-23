package chapter20;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

public class Sec02_02_UsingFilenameFilter {
	private static final String EXTENSION = "jpg";

	public static void main(String[] args) throws FileNotFoundException {

		String dirPath = "/Data/Pictures/";
		File dir = new File(dirPath);
		String[] list = dir.list(new OnlyExt(EXTENSION));
		
		System.out.println("Number of files with the extention '"
				+ EXTENSION + "': " + list.length + "\n");
		
		for(String s: list)
			System.out.println(s);
	
	}
}

class OnlyExt implements FilenameFilter{

	private String ext;

	OnlyExt(String ext){
		this.ext = "." + ext;
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}
	
}
