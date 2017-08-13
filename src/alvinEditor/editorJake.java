package alvinEditor;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import java.util.*;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URI;
import java.io.*;

public class editorJake extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;	
	JMenuBar menuBar;
	public static JTextArea textArea;
	JFileChooser fileChooser;
	String sizeFileName;
	JMenu file,edit,format,insert,tools,help,tLook,fColor,about;
	JMenuItem  fSave,fSaveAs,fOpen,fNew,fExit,fPrint;
	JMenuItem  eCopy,eCut,ePaste;
	JMenuItem tFind ,tReplace,tSize; 
	JCheckBoxMenuItem fWordWrap,tCodeNow;
	JMenuItem fFont,ffColor,fbColor;
	JMenuItem iTimeDate,idictionary,iMathRiposte;
	JMenuItem hViewHelp,hAboutNoteBook,EditNote,tCount;
	JRadioButtonMenuItem rb1,rb2,rb3,rb4,rb5;
	JTextField searchField;
	JLabel  searchLabel,sizeLabel;
	String fileName = null;
	JFileChooser jFC;
	JToolBar toolBar;
	JButton bOpen,bNew,bSave,bFont,bColor,bDictionary,bMath;
	JPanel statusPanel;
	String fileContent;
	 JTextArea textArea2; 
	UndoManager undo;
	UndoAction undoAction;
	RedoAction redoAction;
	
	JButton compile,run, techPC;
	
	String lf1,lf2,lf3,lf4,lf5;
	String tempResult;

	boolean codeReturn;
	
	String findTextM;
	int fnext = 1;
	
	public static editorJake frameMain = new editorJake();

	FontStore fontStore;

	public editorJake()	//Constructor
	{
		initComponent();
		
		fSave.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				save();
			}
		});
		
		bSave.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				save();
			}
		
				});
		
		fSaveAs.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				saveAs();
			}

		});
		
		fOpen.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				open();
			}
		
		});
		
		bOpen.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				open();
			}
		
				});
		
		fNew.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				openNew();
				
			}
		});		
		
		bNew.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				openNew();
			}
		
				});
		
		fPrint.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				PrintFile p = new PrintFile(textArea.getText());
				
		         PrinterJob job = PrinterJob.getPrinterJob();
		         job.setPrintable(p);
		         boolean ok = job.printDialog();
		         if (ok) {
		             try
		             {
		                  job.print();
		             } catch (PrinterException ex)
		             {
		              /* The job did not successfully complete */
		             }
		         }
			}
		
		});
		
		fExit.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				System.exit(0);
				
			}
		});
		
		ePaste.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				textArea.paste();
				
			}
		});
		
		eCut.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				textArea.cut();
				
			}
		});
		
		eCopy.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				textArea.copy();
				
			}
		});
		
		textArea.getDocument().addUndoableEditListener(new UndoableEditListener(){

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				undo.addEdit(e.getEdit());
				undoAction.update();
				redoAction.update();
				
			}
			
		});	
		
		
		fWordWrap.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
			
				if(fWordWrap.isSelected())
				{
					textArea.setLineWrap(true);
					textArea.setWrapStyleWord(true);
				}
				else if(!(fWordWrap.isSelected()))
				{
					textArea.setLineWrap(false);
					textArea.setWrapStyleWord(false);
				}
			}
		});
		
		ffColor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color c = JColorChooser.showDialog(rootPane,"Choose Font Color",Color.GREEN);
				textArea.setForeground(c);
			}
			
		});
		
		bColor.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				Color c = JColorChooser.showDialog(rootPane,"Choose Font Color",Color.GREEN);
				textArea.setForeground(c);
			}
		
				});
		
		fbColor.addActionListener(new ActionListener (){		
			@Override
			public void actionPerformed(ActionEvent e){
			Color c = JColorChooser.showDialog(rootPane,"Choose Background Color",Color.WHITE);
			textArea.setBackground(c);	
			}
			
		});
		
		
		
		iTimeDate.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				timeDate();
			}
		});
		

		iMathRiposte.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				//MathTool m = 
						new MathTool(frameMain);
			}
		});
		
		bMath.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				//MathTool m = 
				new MathTool(frameMain);		
			}
		});
		
		idictionary.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					//Dictionary d = 
					new Dictionary(frameMain);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		bDictionary.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				try {
					//Dictionary d = 
					new Dictionary(frameMain);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		tFind.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				
				new EditTool(frameMain,false);
			}
		});
		
		
		tReplace.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				new EditTool(frameMain,true);
				
			}
		});
		
		fFont.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				fontStore.setVisible(true);
				
			}
		});
		
		bFont.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				fontStore.setVisible(true);
				
			}
		});

		
		fontStore.getOk().addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				textArea.setFont(fontStore.font());
				fontStore.setVisible(false);
				
			}
		});
		
		fontStore.getCancel().addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){
				fontStore.setVisible(false);
				
			}
		});	
		
		searchField.addKeyListener(new KeyListener(){
			
		    @Override
		    public void keyPressed(KeyEvent e){
		    	
		      if(e.getKeyCode()==KeyEvent.VK_ENTER)
		      {
		    	  String search = searchField.getText();
		    	  SearchGoogle(search);
		      }
		        
		    }
			@Override
			public void keyReleased(KeyEvent arg0) {	
			}
			@Override
			public void keyTyped(KeyEvent arg0) {	
			}
		});
		
		tCount.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){				
				try {
					String str = textArea.getText();
					new Counter(frameMain,str);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});	

		textArea.addKeyListener(new KeyListener(){
			
		    @Override
		    public void keyPressed(KeyEvent e){
		       	//KeyBoard k = 
		    	new KeyBoard();
		    	
		      if(codeNowReturn())
		      {
		       	if(KeyBoard.isShiftDown())
		    	{
		    		
		    		if(e.getKeyCode() == KeyEvent.VK_0){
		    			codeNow("(\n");
		    		}		    		
		    		if(e.getKeyCode() == KeyEvent.VK_CLOSE_BRACKET){
		    			codeNow("{\n");
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_PERIOD)
		    		{		    			
		    			codeNow("<\n");		    			
		    		}
		    		
		    	}
		       	if(KeyBoard.isControlDown())
		    	{
		    		
		    		if(e.getKeyCode() == KeyEvent.VK_SPACE){
		    			codeNow("System.out.println();\n");		//link();
		    		}		    		
		    		if(e.getKeyCode() == KeyEvent.VK_M)
		    		{		    			
		    			codeNow("int main()\n{\n\treturn 0;\n}\n");		    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_I)
		    		{		    			
		    			codeNow("\ninterface\n{\n\n}\n");		    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_L)
		    		{		    			
		    			codeNow("for(int i = 0; i < n; i++)\n{\n\n}");		    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_E)
		    		{		    			
		    			codeNow("try\n{\n\n}\ncatch(Exception e)\n{\n\n}");		    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_J)
		    		{		    			
		    			codeNow("import java.util.*;\nimport java.awt.*;\nimport javax.swing.*;\nimport java.io.*;\n");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_W)
		    		{		    			
		    			codeNow(".addActionListener(new ActionListener(){\n@Override\n\tpublic void actionPerformed(ActionEvent ae)\n\t{\n\n\t}\n});\n");    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_B)
		    		{		    			
		    			codeNow("class {\n\tpublic static void main(String args[ ])\n\t{\n\n\t}\n}");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_Q)
		    		{		    			
		    			codeNow("Queue q = new LinkedList();\t//Empty queue\n");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_G)
		    		{		    			
		    			codeNow("class Gen<T>\n{\n\tT ob;\n\tGen(T o)\n\t{\n\t\t ob = o;\n\t}\n}");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_H)
		    		{		    			
		    			codeNow("HashMap < String,Double > hm = new HashMap < String,Double > ();\n\nSet < Map.Entry < String,Double > > set = hm.entrySet();\n");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_K)
		    		{		    			
		    			codeNow("public void keyPressed(KeyEvent ke) {\n\n}\npublic void keyReleased(KeyEvent ke) {\n\n}\npublic void keyTyped(KeyEvent ke) {\n\n}\n");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_U)
		    		{		    			
		    			codeNow("URL link = new URL();\n");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_Y)
		    		{		    			
		    			codeNow("YamlReader reader = new YamlReader(new FileReader());\nYamlWriter writer = new YamlWriter(new FileWriter());\n");	    			
		    		}
		    		if(e.getKeyCode() == KeyEvent.VK_Z)
		    		{		    			
		    			codeNow("ZipFile zipFile = new ZipFile();\n");	    			
		    		}
		    		
		    	}
		       	
		      }
		        
		    }
		   
			@Override
			public void keyReleased(KeyEvent arg0) {

				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
	tCodeNow.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e){				
				if(tCodeNow.isSelected())
				{
					codeReturn = true;
				}
				else
				{
					codeReturn = false;
				}
			}
		});	
	
	tSize.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				
			callFileSize();
		}
	});	
	
	rb1.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				

		    try 
		    {    
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frameMain);
		    } 
		    catch(Exception ae){ 
		    }
		}
	});
	rb2.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				

		    try 
		    {    
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frameMain); 
		    } 
		    catch(Exception ae){ 
		    }
		}
	});
	rb3.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				

		    try 
		    {    
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.metal.MetalLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frameMain); 	    
		    } 
		    catch(Exception ae){ 
		    }
		}
	});
	rb4.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				

		    try 
		    {    
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frameMain);
		    
		    } 
		    catch(Exception ae){ 
		    }
		}
	});
	rb5.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				

		    try 
		    {    
		        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		        SwingUtilities.updateComponentTreeUI(frameMain);		    
		    } 
		    catch(Exception ae){ 
		    }
		}
	});
	EditNote.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				
			AboutDialog ab = new AboutDialog(frameMain);
			ab.setVisible(true);
		}
	});
	
	compile.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){	
			if(fileName!=null){
				save();
				try {
					//new MyCode(fileName);
					consoleFrame cf = new consoleFrame(frameMain);
					cf.setVisible(true);
					cf.consoleFunction(fileName);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	});
	
	techPC.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				
			try {
				GUIProgram.createAndShowGUI(frameMain);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//ab.setVisible(true);
		}
	});
	
	/*run.addActionListener(new ActionListener (){
		@Override
		public void actionPerformed(ActionEvent e){				
			new MyCode(2);
		}
	});*/
	
	}
	
	
private void initComponent()
{
    try
    {
    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
        SwingUtilities.updateComponentTreeUI(frameMain);
    }
    catch(Exception ae)
    {}

    
	jFC = new JFileChooser(".");
	jFC.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
	
	setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/EditNote.png")));
	
	setTitle("EditNote");
	setSize(558, 500);		//1358, 730
	setLocation(410,100);

	undo = new UndoManager();
	
	fontStore = new FontStore(frameMain);
	
	menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	
	toolBar = new JToolBar();
	getContentPane().add(toolBar, BorderLayout.NORTH);
	
	
	file = new JMenu("File");
	ImageIcon iNew = new ImageIcon(getClass().getResource("/image/INew.png"));
	fNew = new JMenuItem("New",iNew);
	fNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
	file.add(fNew);
	ImageIcon iOpen = new ImageIcon(getClass().getResource("/image/IOpen.png"));
	fOpen = new JMenuItem("Open",iOpen);
	fOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
	file.add(fOpen);
	ImageIcon iSave = new ImageIcon(getClass().getResource("/image/ISave.png"));
	fSave = new JMenuItem("Save",iSave);
	fSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
	file.add(fSave);
	ImageIcon iSaveAs = new ImageIcon(getClass().getResource("/image/ISaveAs.png"));
	fSaveAs = new JMenuItem("Save As",iSaveAs);
	file.add(fSaveAs);
	file.addSeparator();
	ImageIcon iPrint = new ImageIcon(getClass().getResource("/image/IPrint.png"));
	fPrint = new JMenuItem("Printer",iPrint);
	fPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
	file.add(fPrint);
	ImageIcon iExit = new ImageIcon(getClass().getResource("/image/IExit.png"));
	fExit = new JMenuItem("Exit",iExit);
	fExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,ActionEvent.ALT_MASK));
	file.add(fExit);
	menuBar.add(file);
	
	bOpen = new JButton(iOpen);
	bNew = new JButton(iNew);
	bSave = new JButton(iSave);
	toolBar.add(bOpen);
	toolBar.add(bNew);	
	toolBar.add(bSave);
	
	edit = new JMenu("Edit");
	ImageIcon iCut = new ImageIcon(getClass().getResource("/image/ICut.png"));
	eCut = new JMenuItem("Cut",iCut);
	eCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
	edit.add(eCut);
	ImageIcon iCopy = new ImageIcon(getClass().getResource("/image/ICopy.png"));
	eCopy = new JMenuItem("Copy",iCopy);
	eCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
	edit.add(eCopy);
	ImageIcon iPaste = new ImageIcon(getClass().getResource("/image/IPaste.png"));
	ePaste = new JMenuItem("Paste",iPaste);
	ePaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
	edit.add(ePaste);
	
	ImageIcon iRedo = new ImageIcon(getClass().getResource("/image/IRedo.png"));
	undoAction = new UndoAction(iRedo);

	
	ImageIcon iUndo = new ImageIcon(getClass().getResource("/image/IUndo.png"));
	redoAction = new RedoAction(iUndo);

	edit.add(redoAction);
	edit.add(undoAction);	

	menuBar.add(edit);
	
	
	format = new JMenu("Format");
	ImageIcon iFont = new ImageIcon(getClass().getResource("/image/IFont.png"));
	fFont = new JMenuItem("Font...",iFont);
	format.add(fFont);
	//ImageIcon iColor = new ImageIcon(getClass().getResource("/image/IColor.png"));
	fColor = new JMenu("Color");
	ImageIcon iForeground = new ImageIcon(getClass().getResource("/image/IForeground.png"));
	ImageIcon iBackground = new ImageIcon(getClass().getResource("/image/IBackground.png"));		
	ffColor = new JMenuItem("Foreground",iForeground);
	fColor.add(ffColor);
	fbColor= new JMenuItem("Background",iBackground);
	fColor.add(fbColor);
	
	format.add(fColor);
	ImageIcon iWrap = new ImageIcon(getClass().getResource("/image/IWrap.png"));
	fWordWrap = new JCheckBoxMenuItem("Word Wrap",iWrap);
	format.add(fWordWrap);
	menuBar.add(format);
	
	bFont = new JButton(iFont);
	bColor = new JButton(iForeground);
	toolBar.add(bFont);	
	toolBar.add(bColor);
	
	insert = new JMenu("Insert");
	ImageIcon iDictionary = new ImageIcon(getClass().getResource("/image/IDictionary.png"));
	idictionary = new JMenuItem("Dictionary",iDictionary);
	idictionary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
	insert.add(idictionary);
	ImageIcon iCalender = new ImageIcon(getClass().getResource("/image/ICalender.png"));
	iTimeDate = new JMenuItem("Time/Date",iCalender);
	iTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
	insert.add(iTimeDate);
	ImageIcon iMath = new ImageIcon(getClass().getResource("/image/IMath.png"));
	iMathRiposte = new JMenuItem("Math Riposte",iMath);
	insert.add(iMathRiposte);
	menuBar.add(insert);
	
	bMath = new JButton(iMath);
	toolBar.add(bMath);
	
	bDictionary = new JButton(iDictionary);
	toolBar.add(bDictionary);
	
	
	tools = new JMenu("Tools");
	ImageIcon iFind = new ImageIcon(getClass().getResource("/image/IFind.png"));
	tFind = new JMenuItem("Find",iFind);
	tFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
	tools.add(tFind);
	ImageIcon iReplace = new ImageIcon(getClass().getResource("/image/IReplace.png"));
	tReplace = new JMenuItem("Replace",iReplace);
	tReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
	tools.add(tReplace);
	ImageIcon iCode = new ImageIcon(getClass().getResource("/image/ICode.png"));
	tCodeNow = new JCheckBoxMenuItem("Code Now",iCode);
	tools.add(tCodeNow);
	ImageIcon iSize = new ImageIcon(getClass().getResource("/image/ISize.png"));
	tSize = new JMenuItem("File Size",iSize);
	tools.add(tSize);
	ImageIcon iCount = new ImageIcon(getClass().getResource("/image/ICount.png"));
	tCount = new JMenuItem("Count",iCount);
	tools.add(tCount);
	
	tLook = new JMenu("Look and Feel");
	rb1 = new JRadioButtonMenuItem("Windows");
	rb2 = new JRadioButtonMenuItem("WindowsClassic");
	rb3 = new JRadioButtonMenuItem("Metal");
	rb4 = new JRadioButtonMenuItem("Motif");
	rb5 = new JRadioButtonMenuItem("Nimbus");
	
	ButtonGroup g = new ButtonGroup();
	g.add(rb1);g.add(rb2);g.add(rb3);g.add(rb4);g.add(rb5);
	tLook.add(rb1);tLook.add(rb2);tLook.add(rb3);tLook.add(rb4);tLook.add(rb5);
	tools.add(tLook);
	menuBar.add(tools);

	
	
	about = new JMenu("About");
	EditNote = new JMenuItem("EditNote");
	ImageIcon iAbout = new ImageIcon(getClass().getResource("/image/IAbout.png"));
	EditNote = new JMenuItem("About EditNote",iAbout);
	about.add(EditNote);
	menuBar.add(about);
	
	
	help = new JMenu("Help");	
	help.setText("<html> <a href=\"\">Help</a></html>");
	help.setCursor(new Cursor(Cursor.HAND_CURSOR));
	email(help,"http://windows.microsoft.com/en-in/windows/notepad-faq#1TC=windows-7");
	menuBar.add(help);

	ImageIcon iCompile = new ImageIcon(getClass().getResource("/image/ICompile.png"));
	compile = new JButton(iCompile);
	toolBar.add(compile);
	
	ImageIcon itechPC = new ImageIcon(getClass().getResource("/image/ICompile.png"));
	techPC = new JButton(itechPC);
	toolBar.add(techPC);
	
	//ImageIcon iCompile = new ImageIcon(getClass().getResource("/image/ICompile.png"));
	//run = new JButton(iCompile);
	//toolBar.add(run);
	
	statusPanel = new JPanel();
	statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
	statusPanel.setPreferredSize(new Dimension(	getContentPane().getWidth(), 20));
	statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
	JLabel statusLabel = new JLabel("Statu");
	statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
	statusPanel.add(statusLabel);
	
	sizeLabel = new JLabel("");
	
	
	searchLabel = new JLabel("s                                         Ask Google : "); 
	searchField = new JTextField(10);


	statusPanel.add(sizeLabel);
	statusPanel.add(searchLabel);
	statusPanel.add(searchField);
	

	getContentPane().add(statusPanel, BorderLayout.SOUTH);

	
	
	textArea = new JTextArea();
	
	textArea.setFont(new Font("Calibri", Font.PLAIN, 14));
	
	getContentPane().add(textArea);
	getContentPane().add(new JScrollPane(textArea) , BorderLayout.CENTER);
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
///Functions  


	private void save()
	{
		PrintWriter fout = null;
		try{
			if(fileName==null){
				
				saveAs();
			}
			
			else
			{
				fout = new PrintWriter(new FileWriter(fileName));
				String s = textArea.getText();
				StringTokenizer st = new StringTokenizer(s,System.getProperty("line.separator"));
				while(st.hasMoreElements()){
				
					fout.println(st.nextToken());
				}
				JOptionPane.showMessageDialog(rootPane, "File Saved");
				
			}
		}
		catch (IOException e){
			
		}
		
		finally{
			if(fout!=null)
			fout.close();
		}
	}
	
	private void saveAs()
	{
		PrintWriter fout = null;
		int returnValue = -1;
		try{
			returnValue = jFC.showSaveDialog(this);
			
			//System.out.println(returnValue);
		if(returnValue==JFileChooser.APPROVE_OPTION)
		{
			
			if(jFC.getSelectedFile().exists())
			{
				//System.out.println(jFC.getSelectedFile());
				int option = JOptionPane.showConfirmDialog(rootPane, "Do you want to replace this file","Confirmation",JOptionPane.OK_CANCEL_OPTION,JOptionPane.OK_CANCEL_OPTION);
				
				if(option == 0)
				{
					fout = new PrintWriter(new FileWriter(jFC.getSelectedFile()));
					String s = textArea.getText();
					StringTokenizer st = new StringTokenizer(s,System.getProperty("line.separator"));
					while(st.hasMoreElements())
					{
						fout.println(st.nextToken());
					}
					JOptionPane.showMessageDialog(rootPane, "File Saved");
					fileContent = textArea.getText();
					fileName = jFC.getSelectedFile().getName();
				//	System.out.println(fileName);
					setTitle(fileName = jFC.getSelectedFile().getName());
					sizeFileName=fileName;
				}
				else
				{
					saveAs();
			/*		fout = new PrintWriter(new FileWriter(jFC.getSelectedFile()));
					String s = textArea.getText();
					StringTokenizer st = new StringTokenizer(s,System.getProperty("line.separator"));
					while(st.hasMoreElements()){
					
						fout.println(st.nextToken());
					}
					JOptionPane.showMessageDialog(rootPane, "File Saved");
					fileContent = textArea.getText();
					fileName = jFC.getSelectedFile().getName();
					setTitle(fileName = jFC.getSelectedFile().getName());
				*/
				}

			}
			else
			{
				fout = new PrintWriter(new FileWriter(jFC.getSelectedFile()));
				String s = textArea.getText();
				StringTokenizer st = new StringTokenizer(s,System.getProperty("line.separator"));
				while(st.hasMoreElements()){
				
					fout.println(st.nextToken());
				}
				JOptionPane.showMessageDialog(rootPane, "File Saved");
				fileContent = textArea.getText();
				fileName = jFC.getSelectedFile().getName();
				setTitle(fileName = jFC.getSelectedFile().getName());
				sizeFileName=fileName;
			}
			
		}
		
		}
		
		catch (IOException e){
			e.printStackTrace();
		}
		finally
		{
			if(fout!=null)
			fout.close();
		}
	}
	
	private void open()
	{
		try
		{
			int returnValue = jFC.showOpenDialog(this);
			if(returnValue==JFileChooser.APPROVE_OPTION)
			{
				textArea.setText(null);
				Reader in = null;
				in = new FileReader(jFC.getSelectedFile());
				
				char[] buff = new char[1000000];
				
				int nch;
				while((nch=in.read(buff, 0 ,buff.length))!=-1)
				{
					textArea.append(new String (buff,0,nch));
				}
				fileName = jFC.getSelectedFile().getName();
				setTitle(fileName = jFC.getSelectedFile().getName());
				sizeFileName=fileName;
				in.close();
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	private void openNew()
	{
		if(!textArea.getText().equals("")&&!textArea.getText().equals(fileContent))
		{
			if(fileName==null)
			{
				int op = JOptionPane.showConfirmDialog(rootPane,"Do you want to save the changes ?");
				if(op==0)
				{
					saveAs();
					clear();
				}
				else if(op==2)
				{clear();}
				else
				{
					clear();
				}
			}
			else
			{
				int op = JOptionPane.showConfirmDialog(rootPane,"Do you want to save the changes ?");
				if(op==0)
				{
					save();
					clear();
				}
				else if(op==2)
				{
					clear();
				}
				else{
					clear();
				}
			}
		}
		else
		{
			clear();
		}
	}
	
	private void clear()
	{
		textArea.setText(null);
		setTitle("Untitled Note");
		fileName=null;
		fileContent=null;
	}
	
	class UndoAction extends AbstractAction
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public UndoAction(ImageIcon iUndo)
		{
			super("Undo",iUndo);
			setEnabled(false);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				undo.undo();
			}
			catch(CannotUndoException ex)
			{
				ex.printStackTrace();
			}
			update();
			redoAction.update();
		}
		protected void update()
		{
			if(undo.canUndo())
			{
				setEnabled(true);
				putValue(Action.NAME,"Undo");
			}
			else
			{
				setEnabled(false);
				putValue(Action.NAME,"Undo");
			}
		}
	}
	
	class RedoAction extends AbstractAction
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public RedoAction(ImageIcon iRedo)
		{
			super("Redo",iRedo);
			setEnabled(false);
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				undo.redo();
			}
			catch(CannotRedoException ex)
			{
				ex.printStackTrace();
			}
			update();
			undoAction.update();
		}
		protected void update()
		{
			if(undo.canRedo())
			{
				setEnabled(true);
				putValue(Action.NAME,"Redo");
			}
			else
			{
				setEnabled(false);
				putValue(Action.NAME,"Redo");
			}
		}
	}
	
	private void timeDate()
	{
		int currentCaretPosition = textArea.getCaretPosition();
		Date date = new Date();
		String str = date.toString();
		textArea.insert(str, currentCaretPosition);
	}
	
	private void codeNow(String str)
	{
		int currentCaretPosition = textArea.getCaretPosition();
		
		textArea.insert(str, currentCaretPosition);
	}
	private boolean codeNowReturn()
	{
		return codeReturn;	
	}
	
	public void email(JMenu help2,String link)
	{
		help.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent me)
			{
				try
				{
					//Desktop.getDesktop().browse(new URI("http://windows.microsoft.com/en-in/windows/notepad-faq#1TC=windows-7"));
					Desktop.getDesktop().browse(new URI(link));
					//https://www.google.co.in/search?q=
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
		});
	}
	public void callFileSize()
	{
		String text = textArea.getText();
		char txt[] = text.toCharArray();
		int bytes = text.length();
		int l;
		l=0;
		for(int i= 0;i<text.length();i++)
		{
			if(txt[i]=='\n')
				l++;
		}
		sizeLabel.setText("s                         File Size : "+(bytes+l)+" Byte");
	}
	
	public void SearchGoogle(String keyword)
	{
		String finalKeyWords = null;
		StringBuffer sb = new StringBuffer("https://www.google.co.in/search?q=");
		
		StringTokenizer st = new StringTokenizer(keyword);
		
		while(st.hasMoreTokens())
		{
			sb.append("+").append(st.nextElement());
		}
		
		finalKeyWords = sb.toString();
		
			try
			{			
				Desktop.getDesktop().browse(new URI(finalKeyWords));
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
	}
	
	public static void main(String args[]) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		
		//editorJake frameMain = new editorJake();
	}
	
	public static JTextArea getArea()
	{
		return textArea;
	}
}
