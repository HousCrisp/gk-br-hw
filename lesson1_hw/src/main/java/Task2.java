import java.util.ArrayList;

public class Task2 {
    //Написать метод, который преобразует массив в ArrayList;
    public static <T> ArrayList<T> arrayToArrayList(T[] a){
        ArrayList<T> out = new ArrayList<T>();
        for (int i=0;i<a.length;i++) {
            out.add(a[i]);
        }
        return out;
    }

    public static void main(String[] args) {
        String[] s = {"a", "b", "c", "d"};
        ArrayList<String> newS= arrayToArrayList(s);
        for (int i = 0; i< newS.size();i++){
            System.out.printf("%1$s   ", newS.get(i));
        }
    }
}
