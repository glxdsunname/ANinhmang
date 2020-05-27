package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Data;

public class Home extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		Data.resetData();
		initComponent();
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton EncryptBtn = new JButton("Encrypt");
		EncryptBtn.setBounds(52, 151, 123, 35);
		EncryptBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		encryptButtonActionPerformed(arg0);
        	}
        });
		contentPane.setLayout(null);
		contentPane.add(EncryptBtn);
		
		JButton DecryptBtn = new JButton("Decrypt");
		DecryptBtn.setBounds(237, 151, 123, 35);
		DecryptBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		decryptButtonActionPerformed(arg0);
        	}
        });
		contentPane.add(DecryptBtn);
	}
	
	private void encryptButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
		ShowFilesAndKey A = new ShowFilesAndKey("ENCRYPT");
		A.setVisible(true);
        setVisible(false);
        dispose();
       
    }

    private void decryptButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
    	ShowFilesAndKey A = new ShowFilesAndKey("DECRYPT");
		A.setVisible(true);
        setVisible(false);
        dispose();
    }

}
