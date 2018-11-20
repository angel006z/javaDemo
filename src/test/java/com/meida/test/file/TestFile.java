package com.meida.test.file;

import java.io.File;
import java.io.IOException;

public class TestFile {
	
public static void main(String[] args) {
	String path="D:/upload/test.txt";
	
	File file=new File(path);
	
	File parentFile=file.getParentFile();
	parentFile.mkdirs();
	
	try {
		file.createNewFile();
	} catch (IOException e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
}
}
