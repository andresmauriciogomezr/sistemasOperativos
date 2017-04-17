package manual;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Manual {
	
	public void abrir(){
		try {
		     File path = new File ("src/manual/Manual.pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}
	}

}
