package chapter20;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Sec07_05_ByteArrayInputStream {

	private static final int EOF = -1;

	public static void main(String[] args) throws IOException {

		String pathName = "E:/Data/Documents/Programs workspace/Java/Eclipse_neon/java_8_the_complete_reference_programs/resources";
		String fileName = "/myfile.txt";

		File file = new File(pathName + fileName);

		byte[] bufferIn = null;
		try (FileInputStream fis = new FileInputStream(file);) {
			bufferIn = new byte[fis.available()];
			if (fis.available() != fis.read(bufferIn)){
				System.out.println("error reading file");
				return;
			}
		} catch (Exception e) {
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(bufferIn);

		int c;
		while ((c = bais.read()) != EOF) {
			System.out.print((char) c);
		}
		System.out.println();

		bais.reset();
		while ((c = bais.read()) != EOF) {
			System.out.print((char) Character.toUpperCase(c));
		}
	}
}
