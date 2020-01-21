import java.io.FileNotFoundException;
import java.io.IOException;


public class ThreadComptageTopic extends Thread {

	Comptage rep ;
	
	public ThreadComptageTopic(Comptage r) {
		
		this.rep= r;
	}
	public void run() {

		try {
			rep.classement();
		} catch (NumberFormatException | IOException e) {


		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			rep.ecrireDansFichierHTML();
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
	}
	
}
