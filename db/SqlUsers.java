package db;
import java.sql.*;
import java.util.Scanner;
import db.env.SqlAdmin.AdminUsers;
import utils.Utils;

public class SqlUsers {
    static public class ManagerUsers{
        private final String URL = AdminUsers.getUrl();
        private final String USER = AdminUsers.getUser();
        private final String PASSWORD = AdminUsers.getPassword();
        private Connection conn;

        private PreparedStatement psInsert;
        private PreparedStatement psSelectUser;
        private PreparedStatement psLogin;
        private PreparedStatement psSelectAll;

        public ManagerUsers(){
            try{

                this.conn = DriverManager.getConnection(URL, USER, PASSWORD);

                this.psInsert = conn.prepareStatement(
                    "INSERT INTO users (username, password) VALUES (?,?)"
                );

                this.psSelectUser = conn.prepareStatement(
                    "SELECT username FROM users WHERE username = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                
                this.psLogin = conn.prepareStatement(
                    "SELECT username, password FROM users WHERE username = ? AND password = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );

                this.psSelectAll = conn.prepareStatement(
                    "SELECT id_user, username, password FROM users",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );

            }catch(SQLException e){
                System.out.println
                (Utils.ERROR+
                    " CONEXÃO COM O DB FALHOU "+
                    Utils.ERROR);

                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }

        public void newUser(String argName, String argPassword){
            try{
                psInsert.setString(1, argName);
                psInsert.setString(2, argPassword);
                psInsert.executeUpdate();
                
            } catch(SQLException e){
                Utils.errorSql(e);
            }
        }

        public boolean validateName(String argName){
            boolean any = false;
            try{
                psSelectUser.setString(1, argName);

                ResultSet rsReturn = psSelectUser.executeQuery();
                any = rsReturn.next();

            }catch(SQLException e){
                System.out.println
                (Utils.ERROR+
                    " BUSCA NO DB FALHOU ! "+
                    Utils.ERROR);

                System.out.println(e.getMessage());
                e.getStackTrace();
            }

            return any;
        }

        public boolean login(String argName, String argPassword){
            boolean validate = false;

            try{
                psLogin.setString(1, argName);
                psLogin.setString(2, argPassword);

                ResultSet rsReturnLogin = psLogin.executeQuery();
                validate = rsReturnLogin.next();

            }catch(SQLException e){
                System.out.println
                (Utils.ERROR+
                    " BUSCA NO DB FALHOU! "+
                    Utils.ERROR);

                System.out.println(e.getMessage());
                e.getStackTrace();
            }

            return validate;
        }

        public void selectAll(){
            try(ResultSet rsSelectAll = psSelectAll.executeQuery()){
                while(rsSelectAll.next()){
                    System.out.println
                    ("("+rsSelectAll.getInt("id_user")+") \n"+
                        "Nome: " + rsSelectAll.getString("username") +"\n"+ 
                        "Senha: " + rsSelectAll.getString("password")+"\n");

                    System.out.println("---------------\n");
                }

            }catch(SQLException e){
                System.out.println("Não foi possível realizar a busca!");
            }
        }
    }
}
