package chapter13;

import java.io.PrintWriter;

public class Sec03_01_PrintWriterDemo {

	public static void main(String[] args) {
		PrintWriter wr = new PrintWriter(System.out, true);
		wr.println("tgis is a test");
		wr.println((double) 4.5e-117);
	}

}
