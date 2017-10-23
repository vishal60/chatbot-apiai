package chapter13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Sec01_02_BufferedReaderDemo2 {

	public static void main(String[] args) throws IOException {

		String s;
		Reader r = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("enter exit to quit");

		do {
			s = ((BufferedReader) r).readLine();
			System.out.println(s);
		} while (!s.equals("exit"));
	}
}
