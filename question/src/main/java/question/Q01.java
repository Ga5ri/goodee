package question;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Q01 {
	private static final String FILENAME = "c:\\test\\q01.text";
	private static FileInputStream fis = null;
	public static void main(String[] args) {
		try {
			fis = new FileInputStream(FILENAME);
			do {
				System.out.print((char) fis.read());
			} while (fis.read() != -1);
	} catch (FileNotFoundException e) {
		System.out.println("FileNotFoundException 발생");
	} catch (IOException e) {
		System.out.println("IOException 발생");
	} finally {
		try {
			fis.close();
		} catch (IOException e) {
			System.out.println("file.close():예외발생");
			}
		}
	}
}
