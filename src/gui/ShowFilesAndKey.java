package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Data;
import core.EncryptorAndDecryptor;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ShowFilesAndKey extends JFrame {
	private FileInputStream fin;
	private JPanel contentPane;
	private String encryptOrDecrypt;
	
	private JScrollPane scrollPane;
	private JTextArea textArea, textArea_1;
	private JButton selectFilesBtn;
	private JButton resetFilesBtn;
	private JButton uploadKeyBtn;
	private JButton EnDecryptBtn;


	
	
	
	public ShowFilesAndKey(String encryptOrDecrypt) {
		this.encryptOrDecrypt = encryptOrDecrypt;
		initComponent();
		displayListOfFilesInTheTextField();
		displayKeyInTheTextField();
	}
	
	private void initComponent() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
		} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 60, 354, 110);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		scrollPane.setViewportView(textArea);
		
		selectFilesBtn = new JButton("Select Files");
		selectFilesBtn.setBounds(388, 60, 103, 39);
		selectFilesBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	selectFileBtnActionPerformed(evt);
            }
        });
		contentPane.add(selectFilesBtn);
		
		resetFilesBtn = new JButton("Reset Files");
		resetFilesBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	resetFilesBtnActionPerformed(evt);
            }
        });
		resetFilesBtn.setBounds(388, 127, 103, 39);
		contentPane.add(resetFilesBtn);
		
		uploadKeyBtn = new JButton("Upload Key");
		uploadKeyBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	uploadKeyBtnActionPerformed(evt);
            }
        });
		uploadKeyBtn.setBounds(388, 210, 103, 39);
		contentPane.add(uploadKeyBtn);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(24, 210, 354, 39);
		contentPane.add(textArea_1);
		
		EnDecryptBtn = new JButton(encryptOrDecrypt);
		EnDecryptBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	EnDecryptBtnActionPerformed(evt);
            }
        });
		EnDecryptBtn.setBounds(217, 272, 96, 36);
		contentPane.add(EnDecryptBtn);
	}
	
	private void selectFileBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        new FileChooser(encryptOrDecrypt , false).setVisible(true);
        setVisible(false);
        dispose();
    }
	
	private void resetFilesBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        Data.resetFiles();
        displayListOfFilesInTheTextField();
    }
	
	private void uploadKeyBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        new FileChooser(encryptOrDecrypt , true).setVisible(true);
        setVisible(false);
        dispose();
    }
	
	private void EnDecryptBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        if(Data.FilesAndFolders == null) {
        	
        	return;
        }
        if(Data.keyFile == null) {
        	return;
        }
        if(Data.keyFile != null) {
			try {
				fin = new FileInputStream(Data.keyFile[0].getAbsolutePath());
				byte[] a = new byte[fin.available()];
				fin.read(a);
				Data.Key = a;
			}catch(Exception e) {}
			//System.out.print(Data.Key);
		}
        new EncryptorAndDecryptor(encryptOrDecrypt);
        setVisible(false);
        dispose();
        
    }
	
	private void displayListOfFilesInTheTextField()
    {
		File[] listOfFilesAndFolders = Data.FilesAndFolders;
    	if(listOfFilesAndFolders == null) {
    		textArea.setText("");
    		return;
    	}
    	
        for(Integer i=0, j=1; i < listOfFilesAndFolders.length; i++, j++)
        {
        	textArea.append(j.toString()+". "+listOfFilesAndFolders[i].getName()+"\n");
        	textArea.append(listOfFilesAndFolders[i].getAbsolutePath()+"\n\n");
        }
        
    }
	
	private void displayKeyInTheTextField()
    {
		
    	if(Data.keyFile == null) {
    		textArea_1.setText("");
    		return;
    	}
    	textArea_1.append(Data.keyFile[0].getName());
        
    }
}
