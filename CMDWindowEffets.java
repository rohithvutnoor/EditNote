import java.io.IOException;
public class CMDWindowEffets
{
	public static void main(String []args){
    public static void getch() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "pause > null").inheritIO().start().waitFor();
    }    }
}