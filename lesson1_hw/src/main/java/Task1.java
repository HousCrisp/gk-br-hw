import java.util.ArrayList;

public class Task1 {
    //1.	Написать метод, который меняет два элемента массива местами
    // (массив может быть любого ссылочного типа);
    public static class ArrayT <T> {
        ArrayList<T> array;
        public ArrayT() {
            array = new ArrayList();
        }
        public void putElement (T element){
            array.add(element);
        }
        public T getElement(int index) {
            return array.get(index);
        }
        public void exchangeElements(int index1, int index2){
            T buffer = array.get(index1);
            array.set(index1, array.get(index2));
            array.set(index2,buffer);
        }
    }
    public static void main(String[] args) {
        int size = 5;
        ArrayT<Integer> testArray = new ArrayT<Integer>();
        for (int i=0;i<size;i++) {
            testArray.putElement(i+5);
        }
        System.out.println("\nBefore exchange:");
        for ( int i=0;i<size;i++){
            System.out.printf("%1$d ",testArray.getElement(i));
        }
        testArray.exchangeElements(2,3);
        System.out.println("\nAfter exchange:");
        for ( int i=0;i<size;i++){
            System.out.printf("%1$d ",testArray.getElement(i));
        }

    }
}
