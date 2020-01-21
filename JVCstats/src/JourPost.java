import java.util.ArrayList;
import java.util.Iterator;

public class JourPost implements Comparable {
    String jour, mois, annee, jourcomplet;
    int nbrepost = 1;

	public JourPost(String j, String m, String a) {
        this.jour = j;
        this.mois = m;
        this.annee = a;
        this.jourcomplet = j + m + a;

    }

    public void ajouterunpost(ArrayList<String> a) {
        Iterator it = a.iterator();
        while (it.hasNext()) {

            String provisoire = (String) it.next();

            if (provisoire.equalsIgnoreCase(this.jourcomplet)) {
                nbrepost++;


            }
        }
    }

    public String toString() {
        return "le " + jour + " " + mois + " " + annee + " " + " il y a eu " + nbrepost + " post";
    }

    @Override
    public int compareTo(Object o) {
        JourPost temp = (JourPost) o;
        if (temp.hashCode() > this.hashCode()) {
            return -1;

        }
        if (temp.hashCode() < this.hashCode()) {
            return 1;

        }
        if (temp.hashCode() == this.hashCode()) {
            return 0;

        }

        return 1; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return jour.hashCode() + mois.hashCode() + annee.hashCode();
    }

    public boolean equals(Object u) {
        return ((((JourPost) u).jourcomplet).hashCode() == (this.jourcomplet).hashCode());
    }

}
