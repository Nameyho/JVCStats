import javax.swing.*;


public class FenetrePrincipale extends JFrame {
long debut = System.currentTimeMillis();
	
	private static final long serialVersionUID = 1L;

	public FenetrePrincipale() {
		
		this.setTitle("calculateur de Posts");
		this.setSize(700, 100);
	//	this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setResizable(true);
		//this.setLocationRelativeTo(null);
		add(new SousFenetreClassement());

		
		
		this.setVisible(true);
		
	}

	
	}


