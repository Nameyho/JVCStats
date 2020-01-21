import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Comptage extends Thread{

	private URL url;
	private InputStream is = null;
	private BufferedReader br;

	private Set<Utilisateur> arbretemp = new TreeSet<Utilisateur>();
	private ArrayList<String> arbretempString = new ArrayList<String>();
	private ArrayList<Utilisateur> tempArray = new ArrayList<Utilisateur>();


	private ArrayList<String> occurence = new ArrayList<String>();

	private Set<JourPost> arbretempjour = new TreeSet<JourPost>();
	private ArrayList<String> arbretempStringjour = new ArrayList<String>();
	private ArrayList<JourPost> tempArrayjour = new ArrayList<JourPost>();

	private Set<MoisPost> arbretempmois = new TreeSet<MoisPost>();
	private ArrayList<String> arbretempStringmois = new ArrayList<String>();
	private ArrayList<MoisPost> tempArraymois = new ArrayList<MoisPost>();

	private String lienPartie1;
	private String lienPartie2;
	private String[] lienPartie;
	private String lienPartiM = "http://m.jeuxvideo.com/forums/";
	private int nbrePage = 1;
	private String nom;
	private String line = "";

	private String mot = "Lukaku";
	int posttotal=0;

	public Comptage(String lien, /* int nbrepage, */ String n) {

		lienPartie1 = lien.substring(10, 32);
		System.out.println(lienPartie1);
		lienPartie2 = lien.substring(32);
		String[] templien = lienPartie2.split("-");
		for (int i = 0; i < templien.length; i++) {
			String string = templien[i];
			System.out.println(string);
			this.nom = n;
		}
		lienPartie = templien;

		/*
		 * String l; int z = 38; do {
		 * 
		 * this.lienPartie1 = lien.substring(10, 38); l = lien.substring(z, z++).trim();
		 * z++;
		 * 
		 * } while (l.equalsIgnoreCase("-"));
		 * 
		 * System.out.println(lienPartie1); int y = 46; do {
		 * 
		 * this.lienPartie3 = lien.substring(38, y++); l = lien.substring(y, y++); y++;
		 * 
		 * } while ((l.equalsIgnoreCase("-")));
		 * 
		 * System.out.println(lienPartie3); int x = 48; this.lienPartie2 =
		 * lien.substring(x++); l= lienPartie2.substring(0,1);
		 * if(!(l.equalsIgnoreCase("-"))) { System.out.println("l");
		 * this.lienPartie2=lien.substring(49); l= lienPartie2.substring(0,1);
		 * if(!(l.equalsIgnoreCase("-"))) { System.out.println("l");
		 * this.lienPartie2=lien.substring(50); l= lienPartie2.substring(0,1);
		 * if(!(l.equalsIgnoreCase("-"))) { System.out.println("l");
		 * this.lienPartie2=lien.substring(51); l= lienPartie2.substring(0,1);
		 * 
		 * }
		 * 
		 * } } System.out.println(lienPartie2);
		 * 
		 * System.out.println(l); x++;
		 * 
		 * 
		 * System.out.println(lienPartie2);
		 * 
		 * String lienPartietemp = lien.substring(45, 46); while
		 * (lienPartietemp.contains("-")) { int index = 46; lienPartietemp =
		 * lien.substring(index, index++); index++; }
		 * 
		 * this.nom = n; this.lien = lienPartiM + lienPartie1 + 1 + lienPartie2;
		 */}

	public BufferedReader etablirConnection(String lien) {

		try {
			url = new URL(lien);
			is = url.openStream(); // throws an IOException
			this.br = new BufferedReader(new InputStreamReader(is));
		} catch (MalformedURLException mue) {
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (is != null) {
				// is.close();
			}
		}
		return br;
	}

	public void classement() throws NumberFormatException, IOException, InterruptedException {

		String temporaire = "";
		JProgressBar bar = new JProgressBar(1, nbrePage);
		final JFrame frame = new JFrame("cr�ation des stats en cours");
		bar.setMinimum(1);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		bar.setStringPainted(true);
		bar.setIndeterminate(true);
		bar.setMaximum(nbrePage);

		frame.setLayout(new FlowLayout());
		frame.getContentPane().add(bar);
		frame.setSize(300, 150);
		frame.setVisible(true);

		for (int i = 1; i <= nbrePage; i++) {

			if(i%25 == 0){
				this.sleep(15000);
			}
			System.out.println("page " + i);
			String lien = lienPartiM;
			for (int j = 0; j < lienPartie.length - 1; j++) {
				if ((j != 3)) {

					lien += lienPartie[j] + "-";

				}
				if (j == 3) {
					lien += i + "-";
				}

			}
			lien += lienPartie[lienPartie.length - 1];
			System.out.println(lien);
			this.etablirConnection(lien);
			bar.setValue(i);
			String test = "page " + i + " / " + nbrePage;
			bar.setString(test);
			String pseudo ="" ;
			occurence.add("1");
			while ((line = br.readLine()) != null) {

				if ((line.contains("alt=")) && line.contains("class=\"user-avatar-msg\"")) {

					line = line.trim();
					String[] temp = line.split("\"");
					temporaire = temp[5] + " \r";
					pseudo = temporaire;
					System.out.println(pseudo);
					temp = temporaire.split("\"");
					byte[] test2 = temporaire.getBytes();
					if (!(temporaire.equalsIgnoreCase(""))) {
						Utilisateur utiltemp = new Utilisateur(temporaire);

						if (arbretemp.add(utiltemp)) {

						} else {
							arbretempString.add(temporaire);
						}
					}


				}
				if (line.contains("class=\"box num-i lien-pagi")) {
					String lastP = br.readLine().trim();

					if (!lastP.equalsIgnoreCase("1")) {
						System.out.println(lastP);
						Integer lastPage = Integer.parseInt(lastP);
						this.nbrePage = lastPage;
					}

				}

				if (line.contains("message  text-enrichi-fmobile  text-crop-fmobile")) {
					String lastP = br.readLine().trim();
					if((lastP.contains(" class=\"blockquote-jv"))){
						lastP = br.readLine().trim();
						String temp2[]= lastP.split("</p></blockquote>");






						if(temp2[temp2.length-1].toLowerCase().contains(mot)){
							System.out.println(temp2[temp2.length-1] + "6666" +pseudo);
							occurence.add(pseudo);
						}

					}else{

						if(lastP.contains(mot)){
							System.out.println( lastP + "66" +pseudo);
							occurence.add(pseudo);
						}

					}




				}


				if (line.contains("date-post") ) {
					String lastP = br.readLine().trim();
					if( !(lastP.contains("javascript"))){
						System.out.println(lastP);
						String[] temp = lastP.split(" ");
						String tempjour = temp[0];
						String tempmois = temp[1];
						String tempannee = temp[2];
						String tempcomplet = tempjour + tempmois + tempannee;
						String mois = tempmois + tempannee;

						if (!(tempcomplet.equalsIgnoreCase(""))) {
							JourPost utiltemp = new JourPost(tempjour, tempmois, tempannee);

							posttotal ++;
							System.out.println(posttotal);
							if (arbretempjour.add(utiltemp)) {

							} else {
								arbretempStringjour.add(tempcomplet);
							}
						}
						if (!(mois.equalsIgnoreCase(""))) {
							MoisPost utiltemp = new MoisPost(tempmois, tempannee);

							if (arbretempmois.add(utiltemp)) {

							} else {
								arbretempStringmois.add(mois);
							}
						}

					}
					}


			}

		}

		Iterator it = arbretempjour.iterator();

		for (JourPost a : arbretempjour) {

			// System.out.println(a.nom);
			a.ajouterunpost(arbretempStringjour);
		}

		TreeSet<JourPost> salComp = new TreeSet<JourPost>(new JourPostComp());
		salComp.addAll(arbretempjour);

		tempArrayjour.addAll(arbretempjour);
		tempArrayjour.sort(new JourPostComp());

		it = arbretemp.iterator();

		for (Utilisateur a : arbretemp) {

			// System.out.println(a.nom);
			a.ajouterNbrePost(arbretempString);
			a.ajouterOccurence(occurence);
		}

		TreeSet<Utilisateur> salComp2 = new TreeSet<Utilisateur>(new UtilisateurComp());
		salComp2.addAll(arbretemp);

		tempArray.addAll(arbretemp);
		tempArray.sort(new UtilisateurComp());

		it = arbretempmois.iterator();

		for (MoisPost a : arbretempmois) {

			// System.out.println(a.nom);
			a.ajouterunpost(arbretempStringmois);
		}

		TreeSet<MoisPost> salComp3 = new TreeSet<MoisPost>(new MoisPostcomp());
		salComp3.addAll(arbretempmois);

		tempArraymois.addAll(arbretempmois);
		tempArraymois.sort(new MoisPostcomp());

	}

	public void afficherrepertoire() {
		Iterator it = tempArray.iterator();
		while (it.hasNext()) {
			Utilisateur ut1 = (((Utilisateur) it.next()));
			System.out.println(ut1.toString());
		}
	}

	public void ecrireDansFichierHTML() throws FileNotFoundException, IOException {

		String Repertoire = nom + ".html";
		FileOutputStream fos = new FileOutputStream(new File(Repertoire));
		Iterator it = tempArray.iterator();
		Iterator it2 = tempArrayjour.iterator();
		Iterator it3 = tempArraymois.iterator();
		String HtmlEntete = "<!DOCTYPE html>";
		String head = "<head>";
		String headFin = "</head>";
		String meta = "<meta charset=\"utf-8\" />";

		byte[] HtmlEnteteByte = HtmlEntete.getBytes();
		byte[] headByte = head.getBytes();
		byte[] headByteF = headFin.getBytes();
		byte[] metaB = meta.getBytes();

		fos.write(HtmlEnteteByte);
		fos.write(headByte);
		fos.write(metaB);
		fos.write(headByteF);
		String Newligne = System.getProperty("line.separator");
		while (it.hasNext()) {
			Utilisateur ut1 = (((Utilisateur) it.next()));

			String tampon = ut1.toString();

			byte[] temp = tampon.getBytes();
			fos.write(temp);

		}

		String fin = "le nombre d'auteur différents est " + tempArray.size() + Newligne + Newligne + Newligne + "<br>";

		byte[] finB = fin.getBytes();
		fos.write(finB);

		String lignetiret = "-------------------------------------------------------------------------------------------------------"
				+ Newligne + "<br>";

		finB = lignetiret.getBytes();
		fos.write(finB);

		while (it2.hasNext()) {
			JourPost ut1 = (((JourPost) it2.next()));

			String tampon = ut1.toString() + Newligne + "<br>";

			byte[] temp = tampon.getBytes();
			fos.write(temp);

		}
		String totaljour = " le nombre de jours différents est " + tempArrayjour.size() + "<br>";
		finB = totaljour.getBytes();

		fos.write(finB);
		finB = lignetiret.getBytes();
		fos.write(finB);

		while (it3.hasNext()) {
			MoisPost ut1 = (((MoisPost) it3.next()));

			String tampon = ut1.toString() + Newligne + "<br>";

			byte[] temp = tampon.getBytes();
			fos.write(temp);

		}
		totaljour = " le nombre de mois différents est " + tempArraymois.size() + "<br>";

		finB = totaljour.getBytes();

		fos.write(finB);
		fos.close();
		br.close();
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		frame.setLayout(new FlowLayout());
		frame.setSize(300, 150);

		JLabel l = new JLabel();

		l.setText("cr�ation de stats finie");
		frame.getContentPane().add(l);
		frame.setVisible(true);
	}

}