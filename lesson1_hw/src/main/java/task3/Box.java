package task3;
import java.util.ArrayList;
import java.lang.Math;

public class Box<T extends Fruit>  {

    ArrayList <T> storage;
    int numOfFruits;
    public Box(){
        storage = new ArrayList<T>();
        numOfFruits=0;
    }
    public void putIn (T f){
       storage.add(f);
       numOfFruits++;
    }
    public Double getWeight(){
        double boxWeight = 0;
        for (int i = 0; i<storage.size();i++){
            boxWeight+=storage.get(i).weight;
        }
        return boxWeight;
    }
    public T takeOut(int i) {
        T f = storage.get(i);
        storage.remove(i);
        numOfFruits--;
        return f;
    }
    public  Box<T> addFromAnotherBox (Box<T> b2){
        for (int i=b2.numOfFruits-1;i>=0;i--){
            putIn(b2.takeOut(i));
        }
        return b2;
    }
    public <E extends Fruit> Boolean compare(Box<E> b2) {
        double e = 0.5;
        if (Math.abs(getWeight()-b2.getWeight())<e) return Boolean.TRUE;
        else return Boolean.FALSE;
    }

}
