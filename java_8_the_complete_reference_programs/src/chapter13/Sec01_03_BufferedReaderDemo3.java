package chapter13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Sec01_03_BufferedReaderDemo3 {

	public static void main(String[] args) throws IOException {

		String[] sArr = new String[100];
		Reader r = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("type exit to quit");

		for (int i = 0; i < sArr.length; i++) {
			sArr[i] = ((BufferedReader) r).readLine();
			if(sArr[i].equalsIgnoreCase("exit"))break;
		}
		
		for(String s: sArr){
			if(s.equalsIgnoreCase("exit")) break;
			System.out.print(s + " ");
		}

	}
}
