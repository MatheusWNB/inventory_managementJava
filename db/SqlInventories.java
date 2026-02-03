package db;

import utils.Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import db.env.SqlAdmin;
import java.sql.*;

public class SqlInventories{
    static public class ManagerInventories{
        private final String url = SqlAdmin.getUrl();
        private final String user = SqlAdmin.getUser();
        private final String password = SqlAdmin.getPassword();
        private Connection conn;

        private PreparedStatement psInsert;
        private PreparedStatement psInsertPass;
        private PreparedStatement psSelectInventory;
        private PreparedStatement psSelectWithPass;
        private PreparedStatement psSelectPass;
        private PreparedStatement psSelectAll;

        public ManagerInventories(){
            try{
                this.conn = DriverManager.getConnection(url,user,password);

                this.psInsert = conn.prepareStatement(
                    "INSERT owner, name_inventory, password, total_items INTO inventories VALUES (?,?,?,?)"
                );

                this.psInsertPass = conn.prepareStatement(
                    "INSERT password INTO inventories VALUES (?)"
                );

                this.psSelectInventory = conn.prepareStatement(
                    "SELECT id_inventory, name FROM inventories WHERE id_inventory = ? AND name = ?"
                );

                this.psSelectWithPass = conn.prepareStatement(
                    "SELECT id_inventory, name, password FROM inventories WHERE id_inventory = ? AND name = ? AND password = ?"
                );

                this.psSelectPass = conn.prepareStatement(
                    "SELECT password FROM inventories WHERE id_inventory = ?"
                );

                this.psSelectAll = conn.prepareStatement(
                    "SELECT * FROM inventories WHERE name = ?"
                );

            } catch(SQLException e){
                System.out.println
                (Utils.TITTLE_ERROR_FORMAT+
                    " CONEXÃO COM O DB FALHOU! "+
                    Utils.TITTLE_ERROR_FORMAT);

                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }

        public boolean setInventory(String name, String name_inventory, String password){
            boolean validate;

            try{
                psInsert.setString(1, name);
                psInsert.setString(2, name_inventory);
                psInsert.setString(3, password);
                psInsert.setInt(4, 0);
                
                validate = true;

            } catch(SQLException e){
                System.out.println
                (Utils.TITTLE_ERROR_FORMAT+
                    " CONEXÃO COM O DB FALHOU! "+
                    Utils.TITTLE_ERROR_FORMAT);

                System.out.println(e.getMessage());
                e.getStackTrace();
                validate = false;
            }

            return validate;
        }

    }

}
