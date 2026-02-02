package register;
import java.sql.*;
import java.util.Scanner;
import utils.Utils;

public class SqlUsers {

    static class ManagerUsers{
        private final String url;
        private final String user;
        private final String password; 
        private Connection conn;

        private PreparedStatement psInsert;
        private PreparedStatement psSelectUser;
        private PreparedStatement psLogin;
        private PreparedStatement psSelectAll;

        public ManagerUsers(){
            try{
                this.conn = DriverManager.getConnection(url, user, password);

                this.psInsert = conn.prepareStatement(
                    "INSERT INTO users (name, password) VALUES (?,?)"
                );

                this.psSelectUser = conn.prepareStatement(
                    "SELECT name FROM users WHERE name = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                
                this.psLogin = conn.prepareStatement(
                    "SELECT name, password FROM users WHERE name = ? AND password = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );

                this.psSelectAll = conn.prepareStatement(
                    "SELECT id_user, name, password FROM users",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );

            }catch(SQLException e){
                System.out.println
                (Utils.TITTLE_ERROR_FORMAT+
                    " CONEXÃO COM O DB FALHOU "+
                    Utils.TITTLE_ERROR_FORMAT);

                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }

        public void newUser(String argName, String argPassword) throws SQLException{
            psInsert.setString(1, argName);
            psInsert.setString(2, argPassword);
            psInsert.executeUpdate();
        }

        public boolean validateName(String argName){
            boolean any = false;
            try{
                psSelectUser.setString(1, argName);

                ResultSet rsReturn = psSelectUser.executeQuery();
                any = rsReturn.next();

            }catch(SQLException e){
                System.out.println
                (Utils.TITTLE_ERROR_FORMAT+
                    " BUSCA NO DB FALHOU ! "+
                    Utils.TITTLE_ERROR_FORMAT);

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
                (Utils.TITTLE_ERROR_FORMAT+
                    " BUSCA NO DB FALHOU! "+
                    Utils.TITTLE_ERROR_FORMAT);

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
                        "Nome: " + rsSelectAll.getString("name") +"\n"+ 
                        "Senha: " + rsSelectAll.getString("password")+"\n");

                    System.out.println("---------------\n");
                }

            }catch(SQLException e){
                System.out.println("Não foi possível realizar a busca!");
            }
        }
    }
}
