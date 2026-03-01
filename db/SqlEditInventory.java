package db;

import db.env.SqlAdmin.AdminInventories;
import java.sql.*;
import utils.Utils;

public class SqlEditInventory {
    static public class ManagerEditInventory{
        private final String URL = AdminInventories.getUrl();
        private final String USER = AdminInventories.getUser();
        private final String PASSWORD = AdminInventories.getPassword();
        private Connection conn;

        private PreparedStatement psInsertItem;
        private PreparedStatement psSelectAll;

        public ManagerEditInventory(){
            try{
                this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
                
                this.psInsertItem = conn.prepareStatement("""
                    INSERT INTO items (id_inventory, item, amount, unit_price, total_price)
                    VALUES (?,?,?,?,?)
                """);

                this.psSelectAll = conn.prepareStatement("""
                    SELECT item, amount, unit_price, total_price FROM items
                    WHERE id_inventory = ?
                """);

            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }

        public boolean regItem(long idInventory, String item, long amount,
                                double unitPrice, double totalPrice)
        {
            boolean validate;
            try{
                psInsertItem.setLong(1, idInventory);
                psInsertItem.setString(2, item);
                psInsertItem.setLong(3, amount);
                psInsertItem.setDouble(4, unitPrice);
                psInsertItem.setDouble(5, totalPrice);

                psInsertItem.executeUpdate();
                
                validate = true;

            } catch(SQLException e){
                Utils.errorSql(e);
                validate = false;
            }

            return validate;
        }   

        public void printItems(long idInventory){
            int id = 1;
            Utils.printTittle("SEUS ITENS");
            
            try{
                psSelectAll.setLong(1, idInventory);
                ResultSet rsSelectAll = psSelectAll.executeQuery();

                while(rsSelectAll.next()){
                    System.out.println(
                        "Id: " + id+"\n"+
                        "Item : " + rsSelectAll.getString("item")+"\n"+
                        "Quantidade: " + rsSelectAll.getLong("amount")+"\n"+
                        "Preço Unitário: " + rsSelectAll.getBigDecimal("unit_price")+"\n"+
                        "Preço Total: " + rsSelectAll.getBigDecimal("total_price")+"\n"
                    );
                    System.out.println("--------------");
                    id++;
                }

            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }
    }
}
