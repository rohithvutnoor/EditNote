package alvinEditor;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.*;
public class KeyBoard 
{

    private static boolean isShiftDown;
    private static boolean isControlDown;

    static 
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyEventDispatcher() {
                public boolean dispatchKeyEvent(KeyEvent e) {
                    isShiftDown = e.isShiftDown();
                    return false;
                }
            });
    }
		
    static 
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyEventDispatcher() {
                public boolean dispatchKeyEvent(KeyEvent e) {
                    isControlDown = e.isControlDown();
                    return false;
                }
            });
    }
    	
	
	public static boolean isShiftDown() {
    		return isShiftDown;
    	}
	public static boolean isControlDown() {
		return isControlDown;
	}
	
}
