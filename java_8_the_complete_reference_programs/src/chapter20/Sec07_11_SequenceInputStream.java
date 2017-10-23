package chapter20;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

public class Sec07_11_SequenceInputStream {

	private static final int EOF = -1;

	public static void main(String[] args) {

		String source = "if (a == 4) a = 0;\n";
		System.out.println(source);

		byte[] buf = source.getBytes();
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);

		try (PushbackInputStream pis = new PushbackInputStream(bais);) {

			int c;
			while ((c = pis.read()) != EOF) {

				switch (c) {

				case '=':
					if ((c = pis.read()) == '=') {
						System.out.print(".equ.");
					} else {
						System.out.print("<-");
						pis.unread(c);
					}
					break;

				default:
					System.out.print((char) c);
				}

			}

		} catch (IOException e) {}

	}
}
