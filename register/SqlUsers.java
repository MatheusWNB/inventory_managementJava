package register;
import java.sql.*;
import java.util.Scanner;
import utils.Utils;

public class SqlUsers {

    static class ManagerUsers{
        private String url;
        private String user;
        private String password;
        private Connection conn;

        private PreparedStatement psInsert;
        private PreparedStatement psSelectUser;
        private PreparedStatement psSelectPassword;
        private PreparedStatement psSelectAll;

        public ManagerUsers(){
            this.url;
            this.user;
            this.password;

            try{
                this.conn = DriverManager.getConnection(url, user, password);

                this.psInsert = conn.prepareStatement(
                    "INSERT INTO users(name, password) VALUES (?,?)"
                );

                this.psSelectUser = conn.prepareStatement(
                    "SELECT name FROM users WHERE name = ?)",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );
                
                this.psSelectPassword = conn.prepareStatement(
                    "SELECT name FROM users WHERE name = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );

                this.psSelectAll = conn.prepareStatement(
                    "SELECT * FROM users",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
                );

            }catch(SQLException e){
                System.out.println(
                    Utils.TITTLE_ERROR_FORMAT+
                    " CONEXÃO COM O DB FALHOU "+
                    Utils.TITTLE_ERROR_FORMAT
                );

                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }

        public void newUser(String argName, String argPassword) throws SQLException{
            psInsert.setString(1, argName);
            psInsert.setString(2, argPassword);
        }

        public String login(String argname, String argPassword)throws SQLException{
            String validateName= null;
            String validatePassword = null;

            try(ResultSet rsSelectUser = psSelectUser.executeQuery()){
                while(rsSelectUser.next()){
                    validateName = rsSelectUser.getString(argname);
                }

            }catch(SQLException e){
                System.out.println(
                    Utils.TITTLE_ERROR_FORMAT+
                    " BUSCA NO DB FALHOU! "+
                    Utils.TITTLE_ERROR_FORMAT
                );
                System.out.println(e.getMessage());
                e.getStackTrace();
            }

            if(validateName == argname){
                try(ResultSet rsSelectPassword = psSelectPassword.executeQuery()){
                    while(rsSelectPassword.next()){
                        validatePassword = rsSelectPassword.getString(argPassword);
                    }

                }catch(SQLException e){
                    System.out.println(
                        Utils.TITTLE_ERROR_FORMAT+
                        " BUSCA NO DB FALHOU! "+
                        Utils.TITTLE_ERROR_FORMAT
                    );
                    System.out.println(e.getMessage());
                    e.getStackTrace();
                }
            }

            return validatePassword;
        }

        public void selectAll(){
            try(ResultSet rsSelectAll = psSelectAll.executeQuery()){
                while(rsSelectAll.next()){
                    System.out.println(
                        "("+rsSelectAll.getInt("id_user")+") \n"+
                        "Nome: " + rsSelectAll.getString("name") +"\n"+ 
                        "Senha: " + rsSelectAll.getString("password")+"\n"
                    );

                    System.out.println("---------------\n");
                }

            }catch(SQLException e){
                System.out.println("Não foi possível realizar a busca!");
            }
        }
    }
}
