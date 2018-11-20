package com.meida.test.file;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestFile3 {
	public static void main(String[] args) {
		String path = "D:/upload/test.txt";
		DataInputStream dis = null;
		try {
			FileInputStream fis = new FileInputStream(path);
			BufferedInputStream bis = new BufferedInputStream(fis);
			dis = new DataInputStream(bis);
			byte[] bytes = new byte[1024];

			while (true) {
				int i = dis.read(bytes);
				if (i == -1) {
					break;
				}
				String str = new String(bytes, 0, i, "utf-8");
				System.out.println(str);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dis != null) {
				try {
					dis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
