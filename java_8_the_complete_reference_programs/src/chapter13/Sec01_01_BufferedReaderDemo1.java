package chapter13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Sec01_01_BufferedReaderDemo1 {

	public static void main(String[] args) throws IOException {

		int c = 'x';
		Reader r = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("press q to quit");

		do {
			c = r.read();
			System.out.println((char) c);
		} while (c != 'q');
	}
}
