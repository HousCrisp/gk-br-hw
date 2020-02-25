import com.google.gson.internal.bind.util.ISO8601Utils;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;
import java.util.Random.*;

import static java.lang.Integer.parseInt;

public class FileUtility {

    public char popChar(char[] array, int length, int iOfEl) {
        char out = array[iOfEl];
        for (int i = iOfEl; i < length; i++) {
            if(i<length-1)
            array[i] = array[i+1];
        }
        return out;
    }
    public void randomChars(int length, Random rand, int beg, int end, int ib, char[] array) {
        //char[] out = new char[length];
        for (int i=ib;i<length+ib;i++){
            array[i] = (char)(rand.nextInt((char)(end -beg ))+beg);
        }
    }
    public String passStringGen (){
        Random rand = new Random();
        char[] symbols = {'*','!','%'};
        int length = rand.nextInt(6)+6;
        int upperCharsLength=rand.nextInt(length-3)+1;
        int numsLength = rand.nextInt(length-2-upperCharsLength)+1;
        int symCharslength= rand.nextInt(length-1-upperCharsLength-numsLength)+1;
        int lowerCharsLength=length-upperCharsLength-numsLength-symCharslength;
        char[] allChars = new char[length];
        //lowerChars
        int begLower ='a';
        int endLower = 'z';
        randomChars(lowerCharsLength,rand, begLower, endLower,0,allChars);
        //upperCase
        int begUpper='A';
        int endUpper= 'Z';
        randomChars(upperCharsLength,rand, begUpper, endUpper,lowerCharsLength,allChars);
        //nums
        int begNums='0';
        int endNums= '9';
        randomChars(numsLength,rand, begNums, endNums,lowerCharsLength+upperCharsLength,allChars );
        //symbols
        for (int i = length-symCharslength; i <length ; i++) {
            allChars[i] = symbols[rand.nextInt(2)];
        }
        String passWord="";
        int lengthCur = length;
        while (lengthCur>0) {
            passWord+=popChar(allChars,lengthCur,rand.nextInt(lengthCur--));
        }
        return passWord;
    }

    /*
     * Структура файла ввода: в первой строке одно целое число - N
     * далее следует N целых чисел через пробел
     * Метод должен отсортировать элементы с четным значением,
     * а нечетные оставить на своих местах и вывести результат через пробел в файл вывода
     * Пример:
     * in:
     * 5
     * 5 4 2 1 3    // 2 4
     * out:
     * 5 2 4 1 3
     */
    public void sortEvenElements(File in, File out) {
       int N;
       int[] nInts;
       try {
           Scanner inSc = new Scanner(in);
           FileOutputStream fos = new FileOutputStream(out);

           N = parseInt(inSc.nextLine().split("\r\n")[0]);
           nInts = new int[N];
           byte[][] outData = new byte[N][];

           String[] a = inSc.nextLine().split(" ");
           int[] even = new int[N];
           int numOfEvens = 0;
           int element = 0;
           for (int i = 0; i < N; i++) {
               nInts[i] = parseInt(a[i]);
               if (nInts[i] % 2 == 0) {
                   even[numOfEvens++] = i;
               }
           }
           for (int i = 0; i < numOfEvens - 1; i++) {
               for (int j = 0; j < numOfEvens - i - 1; j++) {
                   if (nInts[even[j]] > nInts[even[j + 1]]) {
                       element = nInts[even[j]];
                       nInts[even[j]] = nInts[even[j + 1]];
                       nInts[even[j + 1]] = element;
                   }
               }
           }
           for (int i = 0; i < N; i++) {
               fos.write(Integer.toString(nInts[i]).getBytes());
               fos.write(" ".getBytes());
           }
       }

           catch (IOException e) {
               System.out.println(e.getMessage());
               System.out.println(e.getStackTrace());
               }
       }

    /*
     * Генератор паролей, пароль должен отвечать требованиям:
     * длина не менее 6 и не более 12, включает минимум по одному символу
     * из наборов: a-z, A-Z, 0-9, {*,!,%}
     * все пароли должны быть разными
     * На вход метод получает файл с логинами пользователей
     * Метод должен записать в файл вывода записи с логинами и паролями
     * для каждого пользователя
     */

    public void passwordGen(File in, File out)
    {
        try {
            Scanner input = new Scanner(in);
            FileOutputStream output = new FileOutputStream(out);
            while(input.hasNextLine()) {
                output.write((input.nextLine()+" "+passStringGen()+"\n").getBytes());
            }


        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }

    /*
     *  Метод должен дописать в переданный файл все
     * записи из списка по одной записи в строке
     * */
    public void appender(File file, List<String> records) {
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            for (int i = 0; i <records.size() ; i++) {
                out.write((records.get(i)+"\n").getBytes());
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }

    /*
     * Метод возвращает список из N последних строк файла
     * строки можно дополнять пробелами до длины 80
     * тогда количество символов в файле будет предсказуемо
     * 10 строк это ровно 800 символов
     * Изучите класс RandomAccessFile для эффективного решения данной
     * задачи
     * Альтернативное решение: использование очереди или стека
     * */
    public List<String> getNString(String pathToFile, int n) {
        List<String> out = new ArrayList<String>();
        try {
            RandomAccessFile raf = new RandomAccessFile(pathToFile, "r");
            long length = raf.length();
            long pos = length-n*81;
            if (pos<0) pos = 0;
            raf.seek(pos);
            while(raf.getFilePointer()<length) {
                out.add(raf.readLine());
            }

        }
        catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return out;
    }

}
