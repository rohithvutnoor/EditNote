import java.io.IOException;
public class OpenCMD
{
	public static void main(String []args){
		public static void getch() throws IOException, InterruptedException{
        		new ProcessBuilder("cmd", "/c", "pause > null").inheritIO().start().waitFor();
		}    
	}
}
