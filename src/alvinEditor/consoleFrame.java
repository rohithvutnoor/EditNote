package alvinEditor;

import java.awt.Color;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class consoleFrame extends JDialog{

	
	public void printLines(String name, InputStream ins, int flag) throws Exception {
		    String line = null;
		    BufferedReader in = new BufferedReader(
		        new InputStreamReader(ins));
		    while ((line = in.readLine()) != null) {	    	
		    		textArea.setForeground(Color.RED);
		    		textArea.append(fileName+": "+line+"\n");
		    }
	}
	  
	private static final long serialVersionUID = 1L;
	JTextArea textArea;
	String fileName = null;
	public void consoleFunction(String file) throws Exception{
		
		fileName = file.substring(0, file.length()-5);
			try {
				Process p = Runtime.getRuntime().exec("javac "+fileName+".java");
			    printLines(fileName +  " stderr:",p.getErrorStream(), -1);
				p.waitFor();			
				if(p.exitValue()==0){
					//p = Runtime.getRuntime().exec("java "+fileName);
				    //printLines(fileName + " stdout:",p.getInputStream(), 0);
					textArea.setForeground(Color.BLUE);
		    		textArea.append(fileName + " stdout: Compiled Succesfully");
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	}
	public consoleFrame(JFrame owner) throws Exception
	{	
	    //setLayout(new GridLayout(14,1));
    	setTitle("Console");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/EditNote.png")));

        //Font f = new Font("Courier", Font.BOLD,14);
        //l13.setFont(f);

    	textArea = new JTextArea();
    	textArea.setText("");
    	textArea.setEditable(false);
        //panel.setLayout(new BorderLayout());
        //panel.
        add(new JScrollPane(textArea));
        //add(panel);
        int x = owner.getWidth();
        int y = owner.getHeight();
        //System.out.println(" "+x+" "+y+" ");
		
		setResizable(true);
		setSize(x-12,y-350);
		//setMaximizedBounds(new java.awt.Rectangle(0, 0, x-15, 1200));
		//setMaximumSize(new java.awt.Dimension(x-15, y));
		setLocationRelativeTo(owner);
	}

}
