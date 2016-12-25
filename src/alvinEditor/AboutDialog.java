package alvinEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class AboutDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel label1,label2,label3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14;
	JButton button;
	JTextArea textField;
	AboutDialog(JFrame owner)
	{
	    setLayout(new GridLayout(14,1));
		
	    button = new JButton("OK");
	    
    	setTitle("About EditNote");
    	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/EditNote.png")));
    	label1 = new JLabel("        EditNote");
        Font font = new Font("Courier", Font.BOLD,24);
        label1.setFont(font);
        label1.setForeground(Color.BLUE);
        label2 = new JLabel(""); 
        label3 = new JLabel("");
        
        l4 = new JLabel("EditNote");        
        l5 = new JLabel("Version  1.0.0");        
        l6 = new JLabel("© 2016 CBIT Corporation ® All Rights Reserved.");//®
        l7 = new JLabel("The EditNote 1.0 Home application and its user interface are");
        l8 = new JLabel("protected by trademark and other pending or existing");
        l9 = new JLabel(" intellectual property rights in India and other ");
        l10 = new JLabel("countries/regions.");
        l11 = new JLabel("");
        l12 = new JLabel("This product is licensed under the CBIT License Terms to:");
        l13 = new JLabel("Rohith Vutnoor");
        Font f = new Font("Courier", Font.BOLD,14);
        l13.setFont(f);
        
        
        
        add(label1);
       // add(label2);
        add(label3);
        add(new JSeparator(SwingConstants.HORIZONTAL));
        add(l4);add(l5);add(l6);add(l7);add(l8);add(l9);add(l10);add(l11);add(l12);add(l13);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.EAST);
        add(panel);

					
		setSize(350,350);
		setResizable(false);
		 setLocationRelativeTo(owner);
		
		button.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent ae)
			{
					setVisible(false);
			}
			
		});
	}

}
