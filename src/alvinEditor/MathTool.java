package alvinEditor;

import java.awt.FlowLayout;
import java.awt.Toolkit;
//import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;;

class MathTool extends JDialog implements ActionListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel f;
    JTextField t;
    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdiv,bmul,bsub,badd,bdec,beq,bdel,bclr;
    
    static double a=0,b=0;
	double result=0;
    static int operator=0;
	JLabel statusInfo =  new JLabel();
    MathTool(JFrame owner)
    {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/IMath.png")));
        f=new JPanel();
        t=new JTextField(9);
        b1=new JButton("1");
        b2=new JButton("2");
        b3=new JButton("3");
        b4=new JButton("4");
        b5=new JButton("5");
        b6=new JButton("6");
        b7=new JButton("7");
        b8=new JButton("8");
        b9=new JButton("9");
        b0=new JButton("0");
        bdiv=new JButton("/");
        bmul=new JButton("*");
        bsub=new JButton("-");
        badd=new JButton("+");
        bdec=new JButton(".");
        beq=new JButton("=");
        bdel=new JButton("Delete");
        bclr=new JButton("Clear");
          
        getContentPane().add(t);
        getContentPane().add(badd);
        getContentPane().add(bsub);
        getContentPane().add(bmul);
        getContentPane().add(bdiv);
        getContentPane().add(bdec);
        getContentPane().add(b4);
        getContentPane().add(b5);
        getContentPane().add(b6);
        getContentPane().add(b7);
        getContentPane().add(b8);
        getContentPane().add(b9);
        getContentPane().add(b0);
        getContentPane().add(b1);
        getContentPane().add(b2);
        getContentPane().add(b3);
        getContentPane().add(bdel);
        getContentPane().add(bclr);
        getContentPane().add(beq);
        getContentPane().add(statusInfo);
        
        setLayout(new FlowLayout());
        setVisible(true);
        setSize(350,160);
        setLocationRelativeTo(owner);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        badd.addActionListener(this);
        bdiv.addActionListener(this);
        bmul.addActionListener(this);
        bsub.addActionListener(this);
        bdec.addActionListener(this);
        beq.addActionListener(this);
        bdel.addActionListener(this);
        bclr.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            t.setText(t.getText().concat("1"));
            statusInfo.setText("");
        }
        if(e.getSource()==b2)
        {
            t.setText(t.getText().concat("2"));
            statusInfo.setText("");
        }       
        if(e.getSource()==b3)
        {
            t.setText(t.getText().concat("3"));
            statusInfo.setText("");
        }       
        if(e.getSource()==b4)
        {
            t.setText(t.getText().concat("4"));
            statusInfo.setText("");
        }       
        if(e.getSource()==b5)
        {
            t.setText(t.getText().concat("5"));
            statusInfo.setText("");
        }       
        if(e.getSource()==b6)
        {
            t.setText(t.getText().concat("6"));
            statusInfo.setText("");
        }       
        if(e.getSource()==b7)
        {
            t.setText(t.getText().concat("7"));
            statusInfo.setText("");
        }       
        if(e.getSource()==b8)
        {
            t.setText(t.getText().concat("8"));
            statusInfo.setText("");
        }      
        if(e.getSource()==b9)
        {
            t.setText(t.getText().concat("9"));
            statusInfo.setText("");
        }     
        if(e.getSource()==b0)
        {
            t.setText(t.getText().concat("0"));
            statusInfo.setText("");
        }     
        if(e.getSource()==bdec)
        {
            t.setText(t.getText().concat("."));
            statusInfo.setText("");
        }    
        if(e.getSource()==badd)
        {
        	try
        	{
        		a=Double.parseDouble(t.getText());
            	operator=1;
            	t.setText("");
        	}
        	catch(NumberFormatException a)
        	{
        		statusInfo.setText("Invalid Operation");
        		t.setText("");
        	}
        } 
        
        if(e.getSource()==bsub)
        {
        	try
        	{
        		a=Double.parseDouble(t.getText());
        		operator=2;
        		t.setText("");
        	}
        	catch(NumberFormatException a)
        	{
        		statusInfo.setText("Invalid Operation");
        		t.setText("");
        	}
        }
        
        if(e.getSource()==bmul)
        {
        	try
        	{
        		a=Double.parseDouble(t.getText());
        		operator=3;
        		t.setText("");
        	}
        	catch(NumberFormatException a)
        	{
        		statusInfo.setText("Invalid Operation");
        		t.setText("");
        	}
        }
        
        if(e.getSource()==bdiv)
        {
        	try
        	{
            
        		a=Double.parseDouble(t.getText());
        		operator=4;
        		t.setText("");
        	}
        	catch(NumberFormatException a)
        	{
        		statusInfo.setText("Invalid Operation");
        		t.setText("");
        	}
        }
        
        if(e.getSource()==beq)
        {
            b=Double.parseDouble(t.getText());
        
            switch(operator)
            {
                case 1: result=a+b;
                    break;
        
                case 2: result=a-b;
                    break;
        
                case 3: result=a*b;
                    break;
        
                case 4: result=a/b;
                    break;
        
                default: result=0;
            }
        
            t.setText(""+result);
        }
        
        if(e.getSource()==bclr)
            t.setText("");
        
        
        if(e.getSource()==bdel)
        {
            String s=t.getText();
            t.setText("");
            for(int i=0;i<s.length()-1;i++)
            t.setText(t.getText()+s.charAt(i));
        }      
        
    }
    
	public int insertMath()
	{
		return (int) result;
	}
 

}
