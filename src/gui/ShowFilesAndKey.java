package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Data;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShowFilesAndKey extends JFrame {

	private JPanel contentPane;
	private String encryptOrDecrypt;
	
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JButton selectFilesBtn;
	private JButton resetFilesBtn;
	private JButton btnNewButton_2;


	/**
	 * Create the frame.
	 */
	public ShowFilesAndKey(String encryptOrDecrypt) {
		this.encryptOrDecrypt = encryptOrDecrypt;
		initComponent();
		displayListOfFilesInTheTextField();
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
		
		btnNewButton_2 = new JButton("Upload Key");
		btnNewButton_2.setBounds(388, 210, 103, 39);
		contentPane.add(btnNewButton_2);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(24, 210, 354, 39);
		contentPane.add(textArea_1);
		
		JButton btnNewButton = new JButton(encryptOrDecrypt);
		btnNewButton.setBounds(217, 272, 96, 36);
		contentPane.add(btnNewButton);
	}
	
	private void selectFileBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        new FileChooser(encryptOrDecrypt).setVisible(true);
        setVisible(false);
        dispose();
    }
	
	private void resetFilesBtnActionPerformed(java.awt.event.ActionEvent evt)
    {   
        Data.resetFiles();
        displayListOfFilesInTheTextField();
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
}
