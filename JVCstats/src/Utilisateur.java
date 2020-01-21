import java.util.ArrayList;
import java.util.Iterator;

public class Utilisateur implements Comparable {
    public int nbrepost = 1;
    public int occurencemot = 0;
    String mot ="Lukaku";
    String nom ;

    public Utilisateur(String u) {
        if (u == null) {
            System.out.println("pas possible d'ajouter cet utilisateur");
        }
        this.nom = u;
    }

    public void ajouterNbrePost(ArrayList A) {
        Iterator it = A.iterator();
        while (it.hasNext()) {

            String provisoire = (String) it.next();

            if (provisoire.equalsIgnoreCase(this.nom)) {
                nbrepost++;


            }
        }

    }


    public void ajouterOccurence(ArrayList A) {

        Iterator it = A.iterator();

        while (it.hasNext()) {

            String provisoire = (String) it.next();
    System.out.println(provisoire +" 13333333333333");
          if(provisoire.equalsIgnoreCase(this.nom)){
              occurencemot++;


            }
        }


    }

    public String toString() {
        String Newligne = System.getProperty("line.separator");
        String s =Newligne + " l'utilisateur " + this.nom +
                "a posté " + this.nbrepost+
                " post et il aura parlé de  " + mot + " dans "+occurencemot + " posts différents <br>";
        return s;
    }

    @Override
    public int hashCode() {
        return nom.hashCode();
    }

    public boolean equals(Object u) {
        return ((((Utilisateur) u).nom).hashCode() == (this.nom).hashCode());
    }

    @Override
    public int compareTo(Object o) {
        Utilisateur temp = (Utilisateur) o;
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


    public int compare(Utilisateur o1, Utilisateur o2) {

        if (o1.nbrepost == o2.nbrepost) {
            return 0;
        } else if (o1.nbrepost > o2.nbrepost) {
            return -1;
        } else {
            return 1;
        }

    }

}
