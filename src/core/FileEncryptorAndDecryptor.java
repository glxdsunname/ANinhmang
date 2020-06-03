
package core;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import data.Data;

public class FileEncryptorAndDecryptor
{
    private File destinationFile;
    private double accumulator=0;
    
    
    boolean areHashesEqual(byte[] firstHashValue, byte[] secondHashValue)
    {
        if(firstHashValue.length != secondHashValue.length) return false;
        for(int i = 0; i < firstHashValue.length; i++) {
        	if(firstHashValue[i] != secondHashValue[i]) return false;
        }
        return true;
    }
    
    private byte[] hashByte(byte[] originalByte) throws NoSuchAlgorithmException
    {
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashValue =  md.digest(originalByte);
        return hashValue;
    }
    
    private byte[] mergeByte(byte[] firstHashValue, byte[] secondHashValue) throws NoSuchAlgorithmException
    {
    	byte[] mergeArray = new byte[firstHashValue.length+secondHashValue.length];
    	int i;
    	for(i = 0; i < firstHashValue.length;i++) {
    		mergeArray[i] = firstHashValue[i];
    	}
    	for(int j = 0; j < secondHashValue.length ; j++) {
    		mergeArray[i++] = secondHashValue[j];
    	}
    	
        return mergeArray;
    }
    

    
    
    public void encrypt(File file, JProgressBar progressBar, JLabel progressPercentLabel, long totalSizeOfAllFiles, JTextArea progressOfFilesTextField)
    {
       // double percentageOfFileCopied=0;
        if(!file.isDirectory())
        {
            try
            {
                destinationFile=new File(file.getAbsolutePath().concat(".enc"));
                if(destinationFile.exists())
                {
                    destinationFile.delete();
                    destinationFile=new File(file.getAbsolutePath().concat(".enc"));
                }
                
                FileInputStream fileReader = new FileInputStream(file.getAbsolutePath()); 
                FileOutputStream fileWriter= new FileOutputStream (destinationFile, true);
                
                
                
                
                byte[] key = Data.Key;
                SecretKeySpec desKeySpec = new SecretKeySpec(key, "DES");
                Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, desKeySpec);
                
                byte[] buffer = new byte[fileReader.available()];
            	fileReader.read(buffer);
            	
            	byte[] hashValue = hashByte(buffer);
            	byte[] mergeByte = mergeByte(buffer, hashValue);
            	
            	
            	byte[] ciphertext = cipher.doFinal(mergeByte);
            	
            	for(int i = 0; i < ciphertext.length; i++) {
            		fileWriter.write(ciphertext[i]);
            		showProgressOnProgressBarAndProgressPercent(progressBar, progressPercentLabel, 1, totalSizeOfAllFiles);
            	}
            	
            	fileReader.close();
                fileWriter.close();
                
                if(file.exists());
                
            }
            catch (Exception e)
            {
                 System.out.print(e);
            }
            
        }
    }
    
    public void decrypt(File file, JProgressBar progressBar, JLabel progressPercent, long totalSizeOfAllFiles, JTextArea progressOfFilesTextField)
    {

        if(!file.isDirectory())
        {
            try
            {
                if( true)
                {
                    destinationFile=new File(file.getAbsolutePath().toString().substring(0, file.getAbsolutePath().toString().length()-4));
                
                    FileInputStream fileReader=new FileInputStream(file.getAbsolutePath());
                    FileOutputStream fileWriter=new FileOutputStream (destinationFile);
                
                     //decrypting content & writing
                    byte[] buffer = new byte[fileReader.available()];
                    SecretKeySpec desKeySpec = new SecretKeySpec(Data.Key, "DES");
                    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
                    cipher.init(Cipher.DECRYPT_MODE, desKeySpec);
                    
                    fileReader.read(buffer);
                    
                    byte[] plaintext = cipher.doFinal(buffer);
                    
                    byte[] originalByte = Arrays.copyOfRange(plaintext, 0, plaintext.length-32);
                    byte[] hashValueRecived = Arrays.copyOfRange(plaintext, plaintext.length-32, plaintext.length);
                    byte[] hashOriginalByte = hashByte(originalByte);
                    
                    if(!areHashesEqual(hashOriginalByte, hashValueRecived)) {
                    	System.out.print("some one had modify data");
                    	showMessageDialog(null, "lost or some one had modified data");
                    	return;
                    }
                    
                    //fileWriter.write(originalByte);

                	for(int i = 0; i < originalByte.length; i++) {
                		fileWriter.write(originalByte[i]);
                		showProgressOnProgressBarAndProgressPercent(progressBar, progressPercent, 1, totalSizeOfAllFiles);
                	}
                    showProgressOnProgressBarAndProgressPercent(progressBar, progressPercent, buffer.length, totalSizeOfAllFiles);       
                    
                    fileReader.close();
                    fileWriter.close();
                    if(file.exists()) file.delete();
                    
                }
               
                
            }      
            catch (Exception e)
            {
                 System.out.print(e);
            }            
        }
    }
    
    private void showProgressOnProgressBarAndProgressPercent(JProgressBar progressBar, JLabel progressPercent, int bytesCopied, long totalSizeOfAllFiles)
    {
        int previousProgress=progressBar.getValue();
                    
                    accumulator+=((double)bytesCopied  /totalSizeOfAllFiles)*100 ;
                    
                    
                    if (accumulator >=1)
                    {
                            int percentage= previousProgress+ (int) accumulator;
                            accumulator=0;              
                            
                            progressBar.setValue(percentage);
                            progressPercent.setText(Integer.toString(percentage)+"%");
                    }
                    
    }
    
    private void showProgressOnprogressOfFilesTextField(JTextArea progressOfFilesTextField, double percentageOfFileCopied, int bytesCopied, long fileLength)
    {
        StringBuilder s=new StringBuilder(progressOfFilesTextField.getText().substring(0, progressOfFilesTextField.getText().length()-5));
        
        if(percentageOfFileCopied<10)
        {
            s.append("  "+Integer.toString((int)percentageOfFileCopied)+"%\n");
        }
        else if(percentageOfFileCopied <100)
        {
            s.append(" "+Integer.toString((int)percentageOfFileCopied)+"%\n");
        }
        else if(percentageOfFileCopied>=100)
        {
            s.append(Integer.toString((int)percentageOfFileCopied)+"%\n");                        
        }
                    
        progressOfFilesTextField.setText(s.toString());

    }
    
    

}
