package alvinEditor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
//import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.print.DocFlavor.URL;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Dictionary extends JDialog
{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		JPanel panel;
		JLabel label;
		JTextArea meaningArea;
		JPanel north,south,center;
		JTextField field;
		String strLine;
		String find;
		JButton button;
		
		Dictionary(JFrame owner) throws IOException
		{
			setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/IDictionary.png")));
			
			panel = new JPanel();;
			label = new JLabel("Find Meaning : ");
			meaningArea = new JTextArea(5,50);
			field = new JTextField(10);
			button = new JButton("Find");
			
			north = new JPanel();
			south = new JPanel();
			center = new JPanel();
			
			
			north.add(label);
			north.add(field);
			north.add(button);
			center.add(meaningArea);
			
			setVisible(true);
			getContentPane().add(north, BorderLayout.NORTH);
			getContentPane().add(south, BorderLayout.SOUTH);
			getContentPane().add(center, BorderLayout.CENTER);
			setSize(600,200);
			//pack();
			setTitle("Dictionary");
			setLocationRelativeTo(owner);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setResizable(false);
			meaningArea.setText("");
			meaningArea.setEditable(false);
			
			button.addActionListener(new ActionListener(){
				   public void actionPerformed(ActionEvent ae){
					   
				      String textFieldValue = field.getText();
				      find = textFieldValue;
				      
				      	InputStream is = this.getClass().getResourceAsStream("/file/dictionary.txt");
				      
						BufferedReader br = new BufferedReader(new InputStreamReader(is));

						strLine = null;
						find = find.toLowerCase();
						meaningArea.setText("");

						try
						{			
							while (((strLine = br.readLine()) != null))  
							{
								if(strLine.contains(find))
								{
									int l = find.length();
									
									String s1 = find.substring(0,l-1);
								//	String s2 = find.substring(l-1);
									
									char[] s3 = strLine.toCharArray();
									char s4 = s3[l+1];
									
									if(strLine.startsWith(s1)&&s4=='(')
									{
											meaningArea.setText(strLine);
											meaningArea.setLineWrap(true);
											meaningArea.setWrapStyleWord(true);										
									}
									
								}
							}
						}
						catch(IOException e)
						{
							System.out.println("Error");
						}
						finally
						{
						
							try {
								br.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
				   }
				});
			
		}	
}
