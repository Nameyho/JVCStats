import java.util.Comparator;

public class JourPostComp implements Comparator<JourPost>{

    @Override
    public int compare(JourPost o1, JourPost o2) {
        if(o1.nbrepost> o2.nbrepost){
            return -1;
        }
        if(o1.nbrepost<o2.nbrepost){
            return 1;
        }
        return 0;
    }}
    