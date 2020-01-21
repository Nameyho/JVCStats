import java.util.ArrayList;
import java.util.Iterator;

public class MoisPost implements Comparable {
	String mois,annee,moiscomplet;
	int nbrepost= 1;;

	public MoisPost(String m , String a) {
		
		this.mois=m;
		this.annee=a;
		this.moiscomplet=m+a;
		
	}
	public void ajouterunpost(ArrayList<String> a) {
		 Iterator it = a.iterator();
	        while(it.hasNext()){
	                
	            String provisoire = (String)it.next();
	           
	            if(provisoire.equalsIgnoreCase(this.moiscomplet)){
	                nbrepost++;
	            
	                
	            }
	        }
	}
	
	public String toString(){
		return mois+ " " + annee+ " " + " il y a eu " + nbrepost + " post";
	}
	@Override
	public int compareTo(Object o) {
		 MoisPost temp= (MoisPost) o;
	        if(temp.hashCode()>this.hashCode()){
	            return -1;
	            
	        }if(temp.hashCode()<this.hashCode()){
	            return 1;
	            
	        }if(temp.hashCode()==this.hashCode()){
	            return 0;
	            
	        }
	        
	        return 1; //To change body of generated methods, choose Tools | Templates.
	    }
	@Override public int hashCode(){
		   return mois.hashCode()+annee.hashCode();
		}
	
	 
	 }
