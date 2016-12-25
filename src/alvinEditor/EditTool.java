package alvinEditor;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EditTool extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField textFind,replaceText;
	JCheckBox cbCase,cbWhole;
	JRadioButton down,up;
	JLabel statusInfo;
	JFrame owner;
	JPanel north,center,south;
	boolean foundOne,isReplace;
	
	public EditTool(JFrame owner,boolean isReplace)
	{
		super(owner,true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/IFind.png")));
		this.isReplace = isReplace;
		north = new JPanel();
		center = new JPanel();
		south = new JPanel();
		
		if(isReplace)
		{
			setTitle(" Find And Replace");
			setReplacePanel(north);
		}	
		else
		{			
			setTitle(" Find ");
			setFindPanel(north);
		}
		addComponent(center);
		statusInfo = new JLabel("Status Info : ");
		south.add(statusInfo);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		
		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(center, BorderLayout.CENTER);
		getContentPane().add(south, BorderLayout.SOUTH);
		pack();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setSize(500,200);
		//int x = (owner.getWidth()*3/5)-(getWidth()/2);
		//int y = (owner.getHeight()*3/5)-(getHeight()/2);
		//setLocation(x,y);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	private void addComponent(JPanel center)
	{
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		center.setLayout(new GridLayout(1,2));
		east.setLayout(new GridLayout(2,1));
		west.setLayout(new GridLayout(2,1));
		cbCase = new JCheckBox("Match Case",true);
		cbWhole = new JCheckBox("Match Word",true);
		ButtonGroup group = new ButtonGroup();
		up = new JRadioButton("Search Up ",false);
		down = new JRadioButton("Search Down ",true);
		group.add(up);
		group.add(down);
		east.add(cbCase);
		east.add(cbWhole);
		east.setBorder(BorderFactory.createTitledBorder("Search Option "));
		
		west.add(up);
		west.add(down);
		
		west.setBorder(BorderFactory.createTitledBorder("Search Direction "));
		
		center.add(east);
		center.add(west);
	}
	private void setFindPanel(JPanel north)
	{
		final JButton NEXT = new JButton("Find Next ");
		NEXT.addActionListener(this);
		NEXT.setEnabled(false);
		textFind = new JTextField(20);
		textFind.addActionListener(this);
		textFind.addKeyListener(new KeyAdapter(){
			
			@Override
			public void keyReleased(KeyEvent e){
				boolean state = (textFind.getDocument().getLength()>0);
				NEXT.setEnabled(state);
				foundOne = false;
			}
		});
		if(textFind.getText().length()>0)
		{
			NEXT.setEnabled(true);
		}
		north.add(new JLabel("Find Word :  "));
		north.add(textFind);
		north.add(NEXT);
	}
	
	private void setReplacePanel(JPanel north)
	{
		GridBagLayout grid = new GridBagLayout();
		north.setLayout(new GridLayout());
		GridBagConstraints con = new GridBagConstraints();
		con.fill = GridBagConstraints.HORIZONTAL;
		JLabel lblFword = new JLabel(" Find Word : ");
		JLabel lblRword = new JLabel(" Replace Word : ");
		final JButton NEXT = new JButton("Replace");
		NEXT.addActionListener(this);
		NEXT.setEnabled(false);
		
		final JButton REPLACE = new JButton("ReplaceAll");
		REPLACE.addActionListener(this);
		REPLACE.setEnabled(false);
		textFind = new JTextField(20);
		replaceText = new JTextField(20);
		replaceText.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{			
				boolean state = (replaceText.getDocument().getLength()>0);
				NEXT.setEnabled(state);
				REPLACE.setEnabled(state);
				foundOne = false;		
			}
			
		});
		con.gridx = 0;
		con.gridy = 0;
		grid.setConstraints(lblFword,con);
		north.add(lblFword);
		con.gridx = 1;
		con.gridy = 0;
		grid.setConstraints(textFind,con);
		north.add(textFind);
		con.gridx = 2;
		con.gridy = 0;
		grid.setConstraints(NEXT,con);
		north.add(NEXT);
		con.gridx = 0;
		con.gridy = 1;
		grid.setConstraints(lblRword,con);
		north.add(lblRword);
		con.gridx = 1;
		con.gridy = 1;
		grid.setConstraints(replaceText,con);
		north.add(replaceText);
		con.gridx = 2;
		con.gridy = 1;
		grid.setConstraints(REPLACE,con);
		north.add(REPLACE);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(textFind)||e.getSource().equals(replaceText))
		{
			validate();
		}
		process();
		if(e.getActionCommand().equals("Replace All"))
		{
			replaceAll();
		}
	}
	
	private void process()
	{
		if(isReplace)
		{
			statusInfo.setText("Replacing "+textFind.getText());
		}
		else
		{
			statusInfo.setText("Searching For "+textFind.getText());
		}
		int caret = editorJake.getArea().getCaretPosition();
		String word = getWord();
		String text = getAllText();
		caret = search(text,word,caret);
		if(caret<0)
		{
			endResult(false,0);
		}
	}
	private void endResult(boolean isReplaceAll,int tally)
	{
		String message = "";
		if(isReplaceAll)
		{
			if(tally==0)
			{
				message = textFind.getText()+" not found";
			}
			else if(tally == 1)
			{
				message = "One change made t "+textFind.getText();
			}
			else
			{
				message = "" + tally + " changes were made to "+textFind.getText();
			}
		}
		else
		{
			String str = "";
			if(isScarchDown())
			{
				str = "Search Down ";
			}
			else
			{
				str = "Search Up ";
			}
			if(foundOne && !isReplace)
			{
				message = "End of "+ str+" for "+textFind.getText();
			}
			else if(foundOne && isReplace)
			{
				message = "End of Replace "+textFind.getText()+" with "+replaceText.getText();
			}
		}
		statusInfo.setText(message);
	}
	
	private int search(String text,String word,int caret)
	{
		boolean found = false;
		int all = text.length();
		int check = word.length();
		if(isScarchDown())
		{
			int add = 0;
			for(int i = caret+1; i < (all-check); i++)
			{
				String temp = text.substring(i,(i+check));
				if(temp.equals(word))
				{
					if(wholeWordIsSelected())
					{
						if(checkForWholeWord(check,text,add,caret))
						{
							caret = i;
							found = true;
							break;
						}
					}
					else
					{
						caret = i;
						found = true;
						break;
					}
				}
			}
		}
		else
		{
			int add = caret;
			for(int i = caret-1; i>=check;i--)
			{
				add--;
				String temp = text.substring((i-check),i);
				if(temp.equals(word))
				{
					if(wholeWordIsSelected())
					{
						if (checkForWholeWord(check,text,add,caret))
						{
							caret = i;
							found = true;
							break;
						}
					}
					else
					{
						caret = i;
						found = true;
						break;
					}
				}
			}
		}
		editorJake.getArea().setCaretPosition(0);
		if (found)
		{
			editorJake.getArea().requestFocus();
			if(isScarchDown())
			{
				editorJake.getArea().select(caret, caret+check);
			}
			else
			{
				editorJake.getArea().select(caret-check, caret);
			}
			if(isReplace)
			{
				String replace = replaceText.getText();
				editorJake.getArea().replaceSelection(replace);
				if(isScarchDown())
				{
					editorJake.getArea().select(caret, check+replace.length());
				}
				else
				{
					editorJake.getArea().select(caret-replace.length(), caret);
				}
			}
			foundOne = true;
			return caret;
		}

		return -1;
	}
	private String getAllText()
	{
		if(caseNotSelected())
		{
			return editorJake.getArea().getText().toLowerCase();
		}
		return editorJake.getArea().getText();
	}
	private String getWord()
	{
		if(caseNotSelected())
		{
			return textFind.getText().toLowerCase();
		}
		return textFind.getText();
	}
	
	private boolean caseNotSelected()
	{
		return !cbCase.isSelected();
	}
	private boolean isScarchDown()
	{
		return down.isSelected();
	}
	private boolean wholeWordIsSelected()
	{
		return cbWhole.isSelected();
	}
	private boolean checkForWholeWord(int check,String text,int add,int caret)
	{
		int offsetLeft = (caret+add)-1;
		int offsetRight = (caret+add)+check;
		if((offsetLeft<0)||(offsetRight>text.length()))
		{
			return true;
		}
		
		return ((!Character.isLetterOrDigit(text.charAt(offsetLeft)))&&(!Character.isLetterOrDigit(text.charAt(offsetRight))));
	}
	
	private void replaceAll()
	{
		String word = textFind.getText();
		String text = editorJake.getArea().getText();
		String insert = replaceText.getText();
		StringBuffer sb = new StringBuffer(text);
		int diff = insert.length()-word.length();
		int offset = 0;
		int tally = 0;
		for(int i = 0;i<text.length()-word.length();i++)
		{
			String temp = text.substring(i,i+word.length());
			if((temp.equals(word)&&checkForWholeWord(word.length(),text,0,i)))
			{
				tally++;
				sb.replace(i+offset, i+offset+word.length(), insert);
				offset+=diff;
				
			}
		}
		editorJake.getArea().setText(sb.toString());
		endResult(true,tally);
		editorJake.getArea().setCaretPosition(0);
	}
}
