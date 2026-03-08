package db;

import db.env.SqlAdmin.AdminInventories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

public class SqlInventories{
    static public class ManagerInventories{
        private final String URL = AdminInventories.getUrl();
        private final String USER = AdminInventories.getUser();
        private final String PASSWORD = AdminInventories.getPassword();
        private Connection conn;

        private PreparedStatement psInsert;
        private PreparedStatement psSelectInventory;
        private PreparedStatement psSelectWithPass;
        private PreparedStatement psSelectPass;
        private PreparedStatement psSelectAll;

        private List<Long> inventories = new ArrayList<>();

        public ManagerInventories(){
            try{
                this.conn = DriverManager.getConnection(URL,USER,PASSWORD);

                this.psInsert = conn.prepareStatement("""
                    INSERT Into inventories 
                    (owner, name_inventory, password_inventory, total_items) 
                    VALUES (?,?,?,?) RETURNING id_inventory
                """);

                this.psSelectInventory = conn.prepareStatement("""
                    SELECT id_inventory FROM inventories WHERE owner = ?
                """);

                this.psSelectWithPass = conn.prepareStatement("""
                    SELECT id_inventory, name_inventory, password_inventory FROM inventories 
                    WHERE id_inventory = ? AND name_inventory = ? AND password_inventory = ?        
                """);

                this.psSelectPass = conn.prepareStatement(
                    "SELECT password_inventory FROM inventories WHERE id_inventory = ?"
                );

                this.psSelectAll = conn.prepareStatement(
                    "SELECT * FROM inventories WHERE owner = ?"
                );

            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }

        public boolean setInventory(String name, String name_inventory, String password){
            boolean validate;

            try{
                psInsert.setString(1, name);
                psInsert.setString(2, name_inventory);
                psInsert.setString(3, password);
                psInsert.setInt(4, 0);

                ResultSet getId = psInsert.executeQuery();

                inventories.add(getId.getLong("id_inventory"));

                validate = true;

            } catch(SQLException e){
                Utils.errorSql(e);
                validate = false;
            }

            return validate;
        }

        public void printInventories(String name){
            int i = 0;

            System.out.println(
                Utils.TITTLE+
                " SEUS INVENTÁRIOS "+
                Utils.TITTLE
            );

            try{
                psSelectAll.setString(1, name);

                ResultSet rsSelectAll = psSelectAll.executeQuery();
                while(rsSelectAll.next()){
                    System.err.println(
                        "Id: "+i+"\n"+
                        "Name: "+rsSelectAll.getString("name_inventory")+"\n"+
                        "Total items: "+rsSelectAll.getBigDecimal("total_items")+"\n"
                    );

                    i++;
                }

            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }

        public void setListInventories(String owner){
            try{
                psSelectInventory.setString(1, owner);
                ResultSet rsSelectInventory = psSelectInventory.executeQuery();

                while(rsSelectInventory.next()){
                    this.inventories.add(rsSelectInventory.getLong("id_inventory"));
                }
            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }

        public long getInventory(int id){return this.inventories.get(id);}
    }
}
