package alvinEditor;
import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import java.util.StringTokenizer;
public class PrintFile extends JDialog implements Printable{

	String text = null;
	PrintFile(String textArea){
		text = textArea;
	}
	private static final long serialVersionUID = 1L;

		public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

	        if (page > 0)
	        { /* We have only one page, and 'page' is zero-based */
	            return NO_SUCH_PAGE;
	        }

	        Graphics2D g2d = (Graphics2D)g;
	        g2d.translate(pf.getImageableX(), pf.getImageableY());

	        /* Now we perform our rendering */
	        int i = 85;
	        StringTokenizer st = new StringTokenizer(text,System.getProperty("line.separator"));
			while(st.hasMoreElements()){
				g.drawString(st.nextToken(), 70, i);
				i = i+20;
			}
	        
	        
	        return PAGE_EXISTS;
	    }
}