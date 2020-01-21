import java.util.Comparator;

public class MoisPostcomp implements Comparator<MoisPost>{

    @Override
    public int compare(MoisPost o1, MoisPost o2) {
        if(o1.nbrepost> o2.nbrepost){
            return -1;
        }
        if(o1.nbrepost<o2.nbrepost){
            return 1;
        }
        return 0;
    }}
    