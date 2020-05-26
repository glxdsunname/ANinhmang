package gui;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dimension;
import java.awt.EventQueue;

import data.Data;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends javax.swing.JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Home() {
		Data.resetData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton EncryptBtn = new JButton("Encrypt");
		EncryptBtn.setBounds(52, 151, 107, 35);
		EncryptBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		encryptButtonActionPerformed(arg0);
        	}
        });
		frame.getContentPane().add(EncryptBtn);
		
		JButton DecryptBtn = new JButton("Decrypt");
		DecryptBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		decryptButtonActionPerformed(arg0);
        	}
        });
		DecryptBtn.setBounds(243, 151, 120, 35);
		frame.getContentPane().add(DecryptBtn);
	}
	
	private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
		ShowFilesAndKey A = new ShowFilesAndKey("ENCRYPT");
		A.setVisible(true);
        frame.setVisible(false);
        frame.dispose();
       
    }

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
    	ShowFilesAndKey A = new ShowFilesAndKey("DECRYPT");
		A.setVisible(true);
        frame.setVisible(false);
        frame.dispose();
    }


}
