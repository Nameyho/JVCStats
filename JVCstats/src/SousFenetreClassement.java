import javax.swing.*;
import javax.swing.text.DefaultEditorKit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SousFenetreClassement extends JPanel {


	private JProgressBar bar;

	public SousFenetreClassement() {

		JButton button = new JButton("ok");
		// JLabel label =new JLabel("appuyez pour valider");
		JTextField TexteSaisie = new JTextField(50);
		bar = new JProgressBar();

		JPopupMenu popup = new JPopupMenu();
		JMenuItem item = new JMenuItem(new DefaultEditorKit.CutAction());
		item.setText("Couper");
		popup.add(item);
		item = new JMenuItem(new DefaultEditorKit.CopyAction());
		item.setText("Copier");
		popup.add(item);
		item = new JMenuItem(new DefaultEditorKit.PasteAction());
		item.setText("coller");
		popup.add(item);
		TexteSaisie.setComponentPopupMenu(popup);
		TexteSaisie.setText("Entrez votre lien");
		JTextField TexteNom = new JTextField(50);
		TexteNom.setComponentPopupMenu(popup);
		TexteNom.setText("Entrez le nom du fichier sous lequel vous voulez retrouver votre fichier");
		
		
		JTextField nbrePg = new JTextField(10);
		nbrePg.setComponentPopupMenu(popup);
		nbrePg.setText("N° derniére page");
		

		TexteSaisie.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (TexteSaisie.getText().equalsIgnoreCase("Entrez votre lien")
						|| (TexteSaisie.getText().equalsIgnoreCase(""))) {
					TexteSaisie.setText("");
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (TexteSaisie.getText().equalsIgnoreCase(" ")) {
					TexteSaisie.setText("Entrez votre lien");

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				TexteSaisie.setText("");

			}

		});

		
		TexteNom.setComponentPopupMenu(popup);
		TexteNom.setText("Entrez le nom du fichier sous lequel vous voulez retrouver votre fichier");
		TexteNom.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (TexteNom.getText()
						.equalsIgnoreCase("Entrez le nom du fichier sous lequel vous voulez retrouver votre fichier")
						|| (TexteSaisie.getText().equalsIgnoreCase(""))) {
					TexteNom.setText("");
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (TexteNom.getText().equalsIgnoreCase(" ")) {
					TexteNom.setText("Entrez le nom du fichier sous lequel vous voulez retrouver votre fichier");

				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		nbrePg.addMouseListener(new MouseListener() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (nbrePg.getText().equalsIgnoreCase("N° derniére page") || (nbrePg.getText().equalsIgnoreCase(""))) {
					nbrePg.setText("");
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (nbrePg.getText().equalsIgnoreCase(" ")) {
					nbrePg.setText("N° derniére page");

				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String lien = TexteSaisie.getText();
				String nom = TexteNom.getText();
				//String n = nbrePg.getText();
                //int page = Integer.parseInt(n);
                button.setEnabled(false);
                
				
				
				Comptage r = new Comptage(lien, /*page,*/ nom);
				ThreadComptageTopic T = new ThreadComptageTopic(r);
				T.start();
			
				
			}
		});

		add(TexteSaisie);
		//add(nbrePg);
		add(TexteNom);
		add(button);

	}
}
