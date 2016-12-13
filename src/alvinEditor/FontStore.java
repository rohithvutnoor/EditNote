package alvinEditor;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontStore extends JDialog implements ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pan1,pan2,pan3;
	JLabel fontLabel,sizeLabel,typeLabel,previewLabel;
	JTextField  label,fontText,typeText,sizeText;
	JScrollPane fontScroll,typeScroll,sizeScroll;
	JList<String> fontList,sizeList,typeList;
	JButton ok,cancel;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	
	
	public FontStore(JFrame owner)
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/IFont.png")));
		setTitle("Choose Font");
		setSize(300,400);
		setResizable(false);
		setLocationRelativeTo(owner);
		gbl = new GridBagLayout();
		setLayout(gbl);
		gbc = new GridBagConstraints();
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fontLabel = new JLabel("Fonts : ");
		getContentPane().add(fontLabel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		sizeLabel = new JLabel("Sizes : ");
		getContentPane().add(sizeLabel, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		typeLabel = new JLabel("Types : ");
		getContentPane().add(typeLabel, gbc);
		
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		fontText = new JTextField("Arial",12);
		getContentPane().add(fontText, gbc);

	
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		sizeText = new JTextField("8",4);
		getContentPane().add(sizeText, gbc);
		
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		typeText = new JTextField("Regular",6);
		getContentPane().add(typeText, gbc);
	
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontList = new JList<String>(fonts);
		fontList.setFixedCellWidth(110);
		fontList.addListSelectionListener(this);
		fontList.setSelectedIndex(0);
		fontScroll = new JScrollPane(fontList);
		getContentPane().add(fontScroll, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		String[] sizes = {"8","10","12","16","18","20","24","28","32","48","72"};
		sizeList = new JList<String>(sizes);
		sizeList.setFixedCellWidth(20);
		sizeList.addListSelectionListener(this);
		sizeList.setSelectedIndex(0);
		sizeScroll = new JScrollPane(sizeList);
		getContentPane().add(sizeScroll, gbc);
		
		gbc.gridx = 3;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.WEST;
		String[] types = {"Regular","Bold","Italic","Bold Italic"};
		typeList = new JList<String>(types);
		typeList.setFixedCellWidth(60);
		typeList.addListSelectionListener(this);
		typeList.setSelectedIndex(0);
		typeScroll = new JScrollPane(typeList);
		getContentPane().add(typeScroll, gbc);
		
		
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		pan1 = new JPanel();
		pan1.setLayout(new FlowLayout());
		previewLabel = new JLabel("Preview  : ");
		pan1.add(previewLabel);
		getContentPane().add(pan1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		pan2 = new JPanel();
		pan2.setLayout(new FlowLayout());
		label = new JTextField("AaBbCcDdEeFfGgHhIiJj");
		label.setEditable(false);
		label.setBorder(BorderFactory.createEtchedBorder());
		label.setFont(new Font("Arial",Font.PLAIN,20));
		pan2.add(label);
		getContentPane().add(pan2, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		pan3 = new JPanel();
		pan3.setLayout(new FlowLayout());
		ok = new JButton("OK");
		cancel = new JButton("Cancel");
		pan3.add(ok);
		pan3.add(cancel);
		getContentPane().add(pan3, gbc);
		
		
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		try
		{
			if(e.getSource()==fontList)
			{
				Font f1 = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
				fontText.setText(String.valueOf(fontList.getSelectedValue()));
				label.setFont(f1);
			}
			else if(e.getSource()==sizeList)
			{
				Font f2 = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
				sizeText.setText(String.valueOf(sizeList.getSelectedValue()));
				label.setFont(f2);
			}
			else 
			{
				Font f3 = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
				sizeText.setText(String.valueOf(typeList.getSelectedValue()));
				label.setFont(f3);
			}
		}
		catch(Exception ea)
		{
			
		}
	}
	
	public Font font()
	{
		Font font = new Font(String.valueOf(fontList.getSelectedValue()),typeList.getSelectedIndex(),Integer.parseInt(String.valueOf(sizeList.getSelectedValue())));
		return font;
	}
	
	public JButton getOk()
	{
		return ok;
	}

	public JButton getCancel()
	{
		return cancel;
	}
}
