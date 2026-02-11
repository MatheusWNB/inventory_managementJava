package db;

import java.sql.Connection;
import db.env.SqlAdmin.AdminInventories;
import utils.Utils;

import java.sql.*;

public class SqlEditInventory {
    private final String URL = AdminInventories.getUrl();
    private final String USER = AdminInventories.getUser();
    private final String PASSWORD = AdminInventories.getPassword();
    private Connection conn;

    private PreparedStatement psInsertItem;

    public SqlEditInventory(){
        try{
            this.conn = DriverManager.getConnection(URL, USER, PASSWORD);
            
            this.psInsertItem = conn.prepareStatement("""
                INSERT INTO items (id_inventory, item, amount, unit_price, total_price)
                VALUES (?,?,?,?,?)
            """
            );

        } catch(SQLException e){
            Utils.errorSql(e);
        }
    }
}
