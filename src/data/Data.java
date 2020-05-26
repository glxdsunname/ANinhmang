package data;
import java.io.File;
public class Data {
	public static File[] FilesAndFolders = null;
	public static byte[] Key = null;
	
	public static void resetData() {
		FilesAndFolders = null;
		Key = null;
	}
	
	public static void resetFiles() {
		FilesAndFolders = null;
	}
}
