package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.Data;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

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
		contentPane.setLayout(null);
		
		ImageIcon imageIcon = new ImageIcon(Home.class.getResource("/image/unlock.png"));
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(45, 25,  java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newimg);
		JButton decryptBtn = new JButton(imageIcon);
		decryptBtn.setFont(new Font("Arial", Font.BOLD, 13));
		decryptBtn.setText("Decrypt");
		decryptBtn.setBounds(242, 156, 153, 47);
		decryptBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		decryptButtonActionPerformed(arg0);
        	}
        });
		contentPane.add(decryptBtn);
		
		ImageIcon imageIcon1 = new ImageIcon(Home.class.getResource("/image/lock.png"));
		Image image1 = imageIcon1.getImage(); 
		Image newimg1 = image1.getScaledInstance(45, 25,  java.awt.Image.SCALE_SMOOTH);
		imageIcon1 = new ImageIcon(newimg1);
		JButton encyptBtn = new JButton(imageIcon1);
		encyptBtn.setFont(new Font("Arial", Font.BOLD, 13));
		encyptBtn.setText("Encrypt");
		encyptBtn.setBounds(58, 156, 153, 47);
		encyptBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		encryptButtonActionPerformed(arg0);
        	}
        });
		contentPane.add(encyptBtn);
		
		JLabel lblNewLabel = new JLabel("This Application use:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(58, 25, 178, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("- DES to protect confidentiality data");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(76, 62, 250, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblShaTo = new JLabel("- SHA-256 to protect integrity data");
		lblShaTo.setFont(new Font("Arial", Font.BOLD, 14));
		lblShaTo.setBounds(76, 95, 250, 22);
		contentPane.add(lblShaTo);
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
