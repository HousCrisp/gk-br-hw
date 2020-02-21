

import java.sql.*;
import java.util.ArrayList;

public class DBUtility {
    /*
    * Каждый из тасков решается одним SQL запросом
     */

    /*
    Создать таблицу принтеры, Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)
    добавить в нее 3 записи:
    1 1012 col laser 20000 (производитель HP)
    2 1010 bw jet 5000 (производитель Canon)
    3 1010 bw jet 5000 (производитель Canon)
    Каждая вставка в таблицу принтер должна отражаться добавлением записи в таблицу продукт
     */


    void AddPrinters(Statement stmt) {
        // TODO: 16.12.2019
        try {
            int model, price;
            String color, type,maker,dev_type;
            model = 1012;
            price = 20000;
            color = "col";
            type = "laser";
            maker = "HP";
            dev_type = "printer";

            stmt.addBatch(String.format("INSERT INTO Printer (model, color,type, price ) " +
                    "VALUES(%d, '%s', '%s', %d)", model, color, type, price));
            stmt.addBatch(String.format("INSERT INTO Product (maker, model, type ) " +
                    "VALUES('%s', %d, '%s')", maker, model, dev_type));
            model = 1010;
            price = 5000;
            color = "bw";
            type = "jet";
            maker = "Canon";
            stmt.addBatch(String.format("INSERT INTO Printer (model, color,type, price ) " +
                    "VALUES(%d, '%s', '%s', %d)", model, color, type, price));
            stmt.addBatch(String.format("INSERT INTO Product (maker, model, type ) " +
                    "VALUES('%s', %d, '%s')", maker, model, dev_type));

            stmt.addBatch(String.format("INSERT INTO Printer (model, color,type, price ) " +
                    "VALUES(%d, '%s', '%s', %d)", model, color, type, price));
            stmt.addBatch(String.format("INSERT INTO Product (maker, model, type ) " +
                    "VALUES('%s', %d, '%s')", maker, model, dev_type));
            stmt.executeBatch();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public void createPrinterTable(Connection conn,Statement  stmt) {
        // TODO: 16.12.2019
        try {
            stmt.execute("CREATE table if not exists " +
                    "Printer(id INTEGER AI PK U, model INTEGER, color TEXT, type TEXT, price INTEGER)");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    /*
    * Метод должен вернуть список уникальных моделей PC дороже 15 тысяч
     */

    public ArrayList<String> selectExpensivePC(Statement stmt) {
        //todo
        ResultSet rs;
        ArrayList<String> rsOut = new ArrayList<String>();
        try {
            rs = stmt.executeQuery(String.format("select distinct model " +
                    "from pc where price >%d", 15000));
            while (rs.next()) {
                rsOut.add(rs.getString(1)); }
        }
        catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return rsOut;
    }

    /*
     * Метод должен вернуть список id ноутов, скорость процессора
     * которых выше чем 2500
     */

    public ArrayList<Integer> selectQuickLaptop(Statement stmt) {
        ResultSet rs;
        ArrayList<Integer> rsOut = new ArrayList<Integer>();
        try {
            rs = stmt.executeQuery(String.format("select distinct id " +
                    "from laptop where speed >%d", 2500));
            while (rs.next()) {
                rsOut.add(rs.getInt(1)); }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rsOut;
    }

    /*
     * Метод должен вернуть список производителей которые
     *  делают и пк и ноутбуки
     */
    public ArrayList<String> selectMaker(Statement stmt){
        ResultSet rs;
        ArrayList<String> ans = new ArrayList<>();
        // TODO: 18.02.2020
        try{
        rs = stmt.executeQuery("select distinct a.maker " +
                "from (select maker from product where type = 'Laptop') a" +
                " join (select maker from product where type = 'PC') b" +
                " on a.maker = b.maker");
        while (rs.next()) {
            ans.add(rs.getString(1)); }
    }
        catch (Exception e) {
        System.out.println(e.getMessage());
    }
        return ans;
    }

    /*
     * Метод должен вернуть максимальную среди всех произодителей
     * суммарную стоимость всех изделий типов ноутбук или компьютер,
     * произведенных одним производителем
     * Необходимо объединить таблицы продуктов ноутбуков и компьютеров
     * и отгрупировать по сумме прайсов после чего выбрать максимум
     * или сделать любым другим способом
     */

    public int makerWithMaxProceeds(Statement stmt){
        int result = 0;
        ResultSet rs;
        try {
            rs =stmt.executeQuery("select  " +
                    "sum(case when pc.price is not null then pc.price" +
                    " when l.price is not null then l.price" +
                    " else 0 end) as sum_total " +
                    " from (select distinct model, maker from product) a " +
                    "left join pc on pc.model = a.model" +
                    " left join laptop l on l.model = a.model " +
                    " group by a.maker" +
                    " order by sum_total desc");
            result = rs.getInt(1);
        }
        catch (Exception e ) {
            System.out.println(e.getMessage());}
        return result;

    }
}
