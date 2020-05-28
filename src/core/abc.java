package core;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException; 

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class abc {
	public static void main(String args[]) throws IOException, NoSuchAlgorithmException,
	NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException
    { 
        // attach the file to FileInputStream
		File myFile = new File("file1.txt");
		if(!myFile.exists()) {
			myFile.createNewFile();
		}
		File myFile2 = new File("file3.txt");
		if(!myFile2.exists()) {
			myFile2.createNewFile();
		}
		
		File myFile3 = new File("text2.txt");
		if(!myFile3.exists()) {
			myFile3.createNewFile();
		}
		
        FileInputStream fin = new FileInputStream(myFile.getAbsolutePath()); 
        FileOutputStream fout = new FileOutputStream(myFile2.getAbsolutePath());
        
  
        
        byte[] a = new byte[fin.available()];
        int num = fin.read(a);
        //System.out.print("\n" + num + "\n");
        
        String rawkey = "12345678";
        SecretKeySpec desKeySpec = new SecretKeySpec(rawkey.getBytes(), "DES");
        //System.out.print(rawkey.getBytes());
        
        
        		
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashValue =  md.digest(a);
        //System.out.print(hashValue.length);
        
        byte[] combineArr = new byte[hashValue.length+a.length];
        int i;
        for( i = 0; i < a.length;i++) {
        	combineArr[i] = a[i];
        }
        for(int j = 0 ; j < hashValue.length;j++) {
        	combineArr[i++] = hashValue[j];
        }
        
        
        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, desKeySpec);
        
        byte[] EncyptByte = cipher.doFinal(combineArr);
        fout.write(EncyptByte);
        fout.close();
        fin.close(); 
        
        
        fout = new FileOutputStream(myFile3.getAbsolutePath());
        fin = new FileInputStream(myFile2.getAbsolutePath());
        
        byte[] byteRead = new byte[fin.available()];
        
        fin.read(byteRead);
        
        cipher.init(Cipher.DECRYPT_MODE, desKeySpec);
        byte[] DecryptByte = cipher.doFinal(byteRead);
        
        byte[] hasValueRecive = Arrays.copyOfRange(DecryptByte, DecryptByte.length-32, DecryptByte.length);
        byte[] originalText = Arrays.copyOfRange(DecryptByte, 0 ,DecryptByte.length-32);
        byte[] hashOriginalText =  md.digest(a);
        
        for(int k = 0; k < 32; k++) {
        	if (hasValueRecive[k] != hashOriginalText[k]) {
        		System.out.print("no no");
        		return;
        	}
        }
        
        
        fout.write(originalText);
        
        fout.close();
        fin.close(); 
        
    } 
}

