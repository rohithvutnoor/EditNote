package alvinEditor;
import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
public class PrintFile extends JDialog implements Printable{

	private static final long serialVersionUID = 1L;

		public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

	        if (page > 0)
	        { /* We have only one page, and 'page' is zero-based */
	            return NO_SUCH_PAGE;
	        }

	        Graphics2D g2d = (Graphics2D)g;
	        g2d.translate(pf.getImageableX(), pf.getImageableY());

	        /* Now we perform our rendering */
	        g.drawString("Hello world!", 100, 100);
	        
	        return PAGE_EXISTS;
	    }
}