
package gui;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import static javax.swing.JFileChooser.FILES_AND_DIRECTORIES;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import data.Data;
public class FileChooser extends javax.swing.JFrame
{

   
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JFileChooser jFileChooser1;
	
    private String encryptOrDecrypt;
    private Boolean isKey = false;
    boolean addingNewFilesToExisting;
    
    public FileChooser(String encryptOrDecrypt , boolean isKey)
    {
        this.encryptOrDecrypt=encryptOrDecrypt;
        this.isKey = isKey;
        
        try
        {
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
        
        initComponents();
        jFileChooser1.setDialogTitle("Select files & those folders whose files you wish to "+encryptOrDecrypt);
        jFileChooser1.setMultiSelectionEnabled(true);
        jFileChooser1.setFileSelectionMode(FILES_AND_DIRECTORIES);
        jFileChooser1.setDialogTitle(encryptOrDecrypt);
        if(encryptOrDecrypt.equalsIgnoreCase("encrypt"))
        {
            UIManager.put("FileChooser.openDialogTitleText", "Select Files To Encrypt");
            UIManager.put("FileChooser.openButtonText", "Select Files To Encrypt");
        }
        else if(encryptOrDecrypt.equalsIgnoreCase("decrypt"))
        {
            UIManager.put("FileChooser.openDialogTitleText", "Select Files To Decrypt");
            UIManager.put("FileChooser.openButtonText", "Select Files To Decrypt");
        }
        
        UIManager.put("FileChooser.cancelButtonText", "Cancel");
        UIManager.put("FileChooser.fileNameLabelText", "FileName");
        UIManager.put("FileChooser.filesOfTypeLabelText", "TypeFiles");
        UIManager.put("FileChooser.openButtonToolTipText", "Select File");
        UIManager.put("FileChooser.cancelButtonToolTipText","Cancel");
        UIManager.put("FileChooser.fileNameHeaderText","FileName");
        UIManager.put("FileChooser.upFolderToolTipText", "UpOneLevel");
        UIManager.put("FileChooser.homeFolderToolTipText","Desktop");
        UIManager.put("FileChooser.newFolderToolTipText","Create a NewFolder");
        UIManager.put("FileChooser.listViewButtonToolTipText","List");
        UIManager.put("FileChooser.filterLabelText", "TypeFiles");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Details");
        UIManager.put("FileChooser.fileSizeHeaderText","Size");
        UIManager.put("FileChooser.fileDateHeaderText", "DateModified");
        SwingUtilities.updateComponentTreeUI(jFileChooser1);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {   
                setVisible(false);
                dispose();
                new Home().setVisible(true);
            }
        });
        
    }

    
    private boolean anyEncFileExists(File[] listOfFilesAndFolders) // true neu tat ca la file plaintext, false neu co 1 file cipher
    {
        for(File file:listOfFilesAndFolders)
        {
            String filePath=file.getAbsoluteFile().toString();
            if(!file.isDirectory() && filePath.substring(filePath.length()-4, filePath.length()).equals(".enc"))
            {
                System.out.println(file.getAbsoluteFile()+ " is already encrypted");
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "1 or more files are already encrypted !! \nPlease de-select them.");
                return true;
            } 
        }
        return false;
    }
    
    private boolean allAreEncFiles(File[] listOfFilesAndFolders) // true neu tat ca deu la cipher
    {
       for(File file:listOfFilesAndFolders)
        {
            String filePath=file.getAbsoluteFile().toString();
            if(!file.isDirectory() &&!filePath.substring(filePath.length()-4, filePath.length()).equals(".enc"))
            {
                System.out.println(file.getAbsoluteFile()+ " is not encrypted");
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "1 or more files are not encrypted !! \nPlease de-select them.");
                return false;
            } 
        }
        return true;
    }
    
    
    private void initComponents()
    {

        jFileChooser1 = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jFileChooser1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jFileChooser1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt)
    {
        
        if(evt.getActionCommand().toString().equals("CancelSelection"))
        {
        	new ShowFilesAndKey(encryptOrDecrypt).setVisible(true);
            setVisible(false);
            dispose();
        }
        
        else if(evt.getActionCommand().toString().equals("ApproveSelection"))
        {
        	if (this.isKey) {
        		File[] newFilesAndFolders=jFileChooser1.getSelectedFiles();
        		
        		//System.out.print(newFilesAndFolders.length);
        		
        		Data.keyFile = newFilesAndFolders;
        		new ShowFilesAndKey(encryptOrDecrypt).setVisible(true);
                setVisible(false);
                dispose();
                return;
        	}
            
            if(Data.FilesAndFolders == null)
            {
            	Data.FilesAndFolders=jFileChooser1.getSelectedFiles();
            	new ShowFilesAndKey(encryptOrDecrypt).setVisible(true);
                setVisible(false);
                dispose();
            }
            
            else
            {
            	File[] newFilesAndFolders=jFileChooser1.getSelectedFiles();
            	
            	if(encryptOrDecrypt.equalsIgnoreCase("encrypt") && anyEncFileExists(newFilesAndFolders))
                {
                    return;
                }
                else if(encryptOrDecrypt.equalsIgnoreCase("decrypt") && !allAreEncFiles(newFilesAndFolders))
                {
                     return;
                }
                
             
                
                int newLength= newFilesAndFolders.length + Data.FilesAndFolders.length;
        
                File[] combinedFileArray = new File[newLength];
        
                int i;
                
                for(i=0; i<Data.FilesAndFolders.length; i++)
                {
                    combinedFileArray[i]=Data.FilesAndFolders[i];
                }
                for(int j=0; j<newFilesAndFolders.length; j++)
                {
                    combinedFileArray[i++]=newFilesAndFolders[j];
                }
                
                Data.FilesAndFolders = combinedFileArray;
                new ShowFilesAndKey(encryptOrDecrypt).setVisible(true);
                setVisible(false);
                dispose();
            }
           
           
        }        
        
    }

 
}