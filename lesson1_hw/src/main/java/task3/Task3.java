package task3;

public class Task3 {
    public static void main(String[] args) {
        int numOfOranges1 = 6,numOfOranges2 = 4, numOfApples1 = 3,numOfApples2 = 9;
        Box boxOfOranges1 = new Box<Orange>();
        Box boxOfOranges2 = new Box<Orange>();
        Box boxOfApples1 = new Box<Apple>();
        Box boxOfApples2 = new Box<Apple>();
        double weightOfApple = 1, weightOfOrange = 1.5;
        for (int i=0; i<numOfOranges1;i++){
            boxOfOranges1.putIn(new Orange(weightOfOrange));
        }
        for (int i=0; i<numOfOranges2;i++){
            boxOfOranges2.putIn(new Orange(weightOfOrange));
        }
        for (int i=0; i<numOfApples1;i++){
            boxOfApples1.putIn(new Apple(weightOfApple));
        }
        for (int i=0; i<numOfApples2;i++){
            boxOfApples2.putIn(new Apple(weightOfApple));
        }

        // метод getWeight
        System.out.println("Взвесим коробки:");
        System.out.printf("Первая коробка с апельсинами весит %1$f, " +
                "в ней %2$d апельсин\n", boxOfOranges1.getWeight(),numOfOranges1);
        System.out.printf("Вторая коробка с апельсинами весит %1$f, " +
                "в ней %2$d апельсин\n", boxOfOranges2.getWeight(),numOfOranges2);
        System.out.printf("Первая коробка с яблоками весит %1$f, " +
                "в ней %2$d яблок\n", boxOfApples1.getWeight(),numOfApples1);
        System.out.printf("Вторая коробка с яблоками весит %1$f, " +
                "в ней %2$d яблок\n", boxOfApples2.getWeight(),numOfApples2);

        //метод Compare
        System.out.println("Первая коробка c апельсинами весит столько же сколько весит " +
                "вторая коробка с яблоками?"+boxOfOranges1.compare(boxOfApples2));
        System.out.println("Первая коробка c апельсинами весит столько же сколько весит " +
                "первая коробка с яблоками?"+boxOfOranges1.compare(boxOfApples1));

        //пересыпем из первой коробки с яблоками все во вторую
        boxOfApples1=boxOfApples2.addFromAnotherBox(boxOfApples1);
        System.out.println("После пересыпания взвесим коробки:");
        System.out.printf("Первая коробка с яблоками весит %1$f, " +
                "в ней %2$d яблок\n", boxOfApples1.getWeight(),boxOfApples1.numOfFruits);
        System.out.printf("Вторая коробка с яблоками весит %1$f, " +
                "в ней %2$d яблок\n", boxOfApples2.getWeight(),boxOfApples2.numOfFruits);
    }
}
