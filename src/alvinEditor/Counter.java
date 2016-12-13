package alvinEditor;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Counter extends JDialog{
	
	private static final long serialVersionUID = 1L;
	JPanel p1,p2,p3;
	JLabel l1,l2,l3,l4,checkLabel;
	static int s;
	static int c,l,w;

	Counter(JFrame owner,String fileString) throws IOException
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/ISize.png")));
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();

		char str[] = fileString.toCharArray();
		
		l=s=w=c=0;
		
		
			for(int i = 0;i<fileString.length();i++)
			{
				if(str[i]==' ')
					s++;
				else if(str[i]=='\n')
					l++;
				
				c++;
			}

			
		l1 = new JLabel("Word Count  		:  "+(s+l+1));
		l2 = new JLabel("Characters Count   :  "+(c-l-s));
		l3 = new JLabel("Lines Count  		:  "+(l+1));
		l4 = new JLabel("Space Count  		:  "+s);        
		 
        
			

				p1.add(l1);
		 		p1.add(l2);
		 		p1.add(l3);
		 		p1.add(l4);	
				
		getContentPane().add(p1, BorderLayout.CENTER);
		getContentPane().add(p2, BorderLayout.SOUTH);
		getContentPane().add(p3, BorderLayout.NORTH);
	
		setVisible(true);
		pack();
		setTitle("Count");
		setLocationRelativeTo(owner);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}

}
