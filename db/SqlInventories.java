package db;

import utils.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import db.env.SqlAdmin;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SqlInventories{
    static public class ManagerInventories{
        private final String url = SqlAdmin.getUrl();
        private final String user = SqlAdmin.getUser();
        private final String password = SqlAdmin.getPassword();
        private Connection conn;

        private PreparedStatement psInsert;
        private PreparedStatement psSelectInventory;
        private PreparedStatement psSelectWithPass;
        private PreparedStatement psSelectPass;
        private PreparedStatement psSelectAll;

        public ManagerInventories(){
            try{
                this.conn = DriverManager.getConnection(url,user,password);

                this.psInsert = conn.prepareStatement(
                    "INSERT owner, name_inventory, password_inventory, total_items INTO inventories VALUES (?,?,?,?)"
                );

                this.psSelectInventory = conn.prepareStatement(
                    "SELECT id_inventory, name_inventory FROM inventories WHERE id_inventory = ? AND name_inventory = ?"
                );

                this.psSelectWithPass = conn.prepareStatement(
                    "SELECT id_inventory, name_inventory, password_inventory FROM inventories WHERE id_inventory = ? AND name_inventory = ? AND password_inventory = ?"
                );

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

                psInsert.executeUpdate();
                
                validate = true;

            } catch(SQLException e){
                Utils.errorSql(e);
                validate = false;
            }

            return validate;
        }

        public void printInventories(String name){
            int i = 0;
            List<String> inventories = new ArrayList<>();

            System.out.println(
                Utils.TITTLE_FORMAT+
                " SEUS INVENTÁRIOS "+
                Utils.TITTLE_FORMAT
            );

            try(ResultSet rsSelectAll = psSelectAll.executeQuery()){
                while(rsSelectAll.next()){
                    System.err.println(
                        "Id: "+i+
                        "Nome: "+rsSelectAll.getString("name_inventory")
                    );

                    inventories.add(rsSelectAll.getString("name_inventory"));
                    System.out.println("----------");
                }

            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }
    }
}
