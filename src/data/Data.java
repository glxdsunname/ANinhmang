package data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Data {
	public static File[] FilesAndFolders = null;
	public static File[] keyFile = null;
	public static byte[] Key = null;
	
	
	public static void resetData() {
		FilesAndFolders = null;
		Key = null;
		keyFile = null;
	}
	
	public static void resetFiles() {
		FilesAndFolders = null;
	}
	public static void setKey() throws IOException {
		if (keyFile != null) {
			return;
		}
		
		System.out.print("abc");
	}
}
