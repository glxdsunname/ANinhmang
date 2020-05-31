package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;


public class ShowFilesAndKey extends JFrame {
	private FileInputStream fin;
	private JPanel contentPane;
	private String encryptOrDecrypt;
	
	private JScrollPane scrollPane;
	private JTextArea KeyText, FileTextArea;
	private JButton selectFilesBtn;
	private JButton resetFilesBtn;
	private JButton uploadKeyBtn;
	private JButton EnDecryptBtn;
	private JButton homeBtn;
	private JLabel folderIconlbl;
	private JLabel keyIconlbl;
	private JLabel keylbl;
	private JPanel keyPanel;


	
	
	
	public ShowFilesAndKey(String encryptOrDecrypt) {
		setBackground(new Color(190, 121, 223));
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
		setBounds(100, 100, 709, 522);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(190, 121, 223));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imageIcon1 = new ImageIcon(ShowFilesAndKey.class.getResource("/image/folder.png"));
		Image image1 = imageIcon1.getImage(); 
		Image newimg1 = image1.getScaledInstance(60, 45,  java.awt.Image.SCALE_SMOOTH);
		imageIcon1 = new ImageIcon(newimg1);
		
		ImageIcon imageIcon = new ImageIcon(Home.class.getResource("/image/home.png"));
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(45, 25,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		homeBtn = new JButton(imageIcon);
		homeBtn.setBounds(515, 11, 137, 48);
		contentPane.add(homeBtn);
		homeBtn.setFont(new Font("Arial", Font.BOLD, 15));
		homeBtn.setText("Home");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomeBtnActionPerformed(arg0);
			}
		});
		
		
		
		
		JPanel folderPanel = new JPanel();
		folderPanel.setBackground(new Color(207, 241, 239));
		folderPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		folderPanel.setBounds(23, 62, 631, 189);
		contentPane.add(folderPanel);
		folderPanel.setLayout(null);
		
		folderIconlbl = new JLabel("");
		folderIconlbl.setBounds(30, 12, 60, 45);
		folderPanel.add(folderIconlbl);
		folderIconlbl.setIcon(imageIcon1);
		
		JLabel folderlbl = new JLabel("Folders to be " + encryptOrDecrypt.toLowerCase());
		folderlbl.setBounds(100, 30, 215, 18);
		folderPanel.add(folderlbl);
		folderlbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 68, 483, 104);
		folderPanel.add(scrollPane);
		
		FileTextArea = new JTextArea();
		scrollPane.setViewportView(FileTextArea);
		FileTextArea.setEditable(false);
		
		selectFilesBtn = new JButton("Select Files");
		selectFilesBtn.setBounds(523, 70, 98, 40);
		folderPanel.add(selectFilesBtn);
		
		resetFilesBtn = new JButton("Reset Files");
		resetFilesBtn.setBounds(523, 127, 98, 45);
		folderPanel.add(resetFilesBtn);
		resetFilesBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	resetFilesBtnActionPerformed(evt);
            }
        });
		selectFilesBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	selectFileBtnActionPerformed(evt);
            }
        });
		
		keyPanel = new JPanel();
		keyPanel.setBackground(new Color(207, 241, 239));
		keyPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		keyPanel.setBounds(23, 278, 631, 147);
		contentPane.add(keyPanel);
		keyPanel.setLayout(null);
		
		uploadKeyBtn = new JButton("Upload Key");
		uploadKeyBtn.setBounds(518, 67, 103, 39);
		keyPanel.add(uploadKeyBtn);
		
		KeyText = new JTextArea();
		KeyText.setBounds(41, 66, 467, 46);
		keyPanel.add(KeyText);
		KeyText.setEditable(false);
		
		keyIconlbl = new JLabel("");
		ImageIcon imageIcon2 = new ImageIcon(ShowFilesAndKey.class.getResource("/image/key.png"));
		Image image2 = imageIcon2.getImage(); 
		Image newimg2 = image2.getScaledInstance(40, 30,  java.awt.Image.SCALE_SMOOTH);
		imageIcon2 = new ImageIcon(newimg2);
		keyIconlbl.setBounds(41, 16, 46, 39);
		keyPanel.add(keyIconlbl);
		keyIconlbl.setIcon(imageIcon2);
		
		keylbl = new JLabel("Key");
		keylbl.setBounds(97, 27, 103, 28);
		keyPanel.add(keylbl);
		keylbl.setFont(new Font("Arial", Font.BOLD, 15));
		
		EnDecryptBtn = new JButton(encryptOrDecrypt);
		EnDecryptBtn.setBounds(288, 436, 96, 36);
		contentPane.add(EnDecryptBtn);
		EnDecryptBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	EnDecryptBtnActionPerformed(evt);
            }
        });
		uploadKeyBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
            	uploadKeyBtnActionPerformed(evt);
            }
        });
		
		
		
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
        new EncryptorAndDecryptor(encryptOrDecrypt).execute();
        setVisible(false);
        dispose();
        
    }

	private void HomeBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        
        new Home().setVisible(true);
        setVisible(false);
        dispose(); 
    }
	
	private void displayListOfFilesInTheTextField()
    {
		File[] listOfFilesAndFolders = Data.FilesAndFolders;
    	if(listOfFilesAndFolders == null) {
    		FileTextArea.setText("");
    		return;
    	}
    	
        for(Integer i=0, j=1; i < listOfFilesAndFolders.length; i++, j++)
        {
        	FileTextArea.append(j.toString()+". "+listOfFilesAndFolders[i].getName()+"\n");
        	FileTextArea.append(listOfFilesAndFolders[i].getAbsolutePath()+"\n\n");
        }
        
    }
	
	private void displayKeyInTheTextField()
    {
		
    	if(Data.keyFile == null) {
    		KeyText.setText("");
    		return;
    	}
    	KeyText.append(Data.keyFile[0].getAbsolutePath());
        
    }
}
