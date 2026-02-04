package login_register;

import java.util.Scanner;
import db.SqlUsers.ManagerUsers;
import java.sql.SQLException;
import utils.Utils;

public class RegisterUser {
    static Scanner sc = new Scanner(System.in);

    public static class NewUser{
        private String userName;
        private String password;

        public NewUser(Scanner sc, ManagerUsers admin){
            this.userName = setNome(sc, admin);
            this.password = setSenha(sc);
        }

        void registerUser(ManagerUsers admin){
            try{
                admin.newUser(this.userName, this.password);
                System.out.println("CADASTRO CONCLUIDO");

            }catch(SQLException e){
                Utils.errorSql(e);
            }
        }

        public String setNome(Scanner sc, ManagerUsers admin) {
            System.out.print("Nome de usuário: ");
            boolean any;
            while(true){
                String tempNome = sc.nextLine();
                any = admin.validateName(tempNome);

                if(any == true){
                    Utils.limparTerminal();

                    System.out.println
                    (Utils.TITTLE_ERROR_FORMAT+
                        " ESSE NOME DE USUÁRIO JÁ ESTÁ EM USO! "+
                        Utils.TITTLE_ERROR_FORMAT);

                    continue;
                }
                return tempNome;
            }
        }

        public String setSenha(Scanner sc) {
            System.out.print("Sua senha: ");
            String tempSenha = sc.nextLine();
            return tempSenha;
        }

        public String getUserName(){
            return this.userName;
        }

        public String getPassword(){
            return this.password;
        }
    }
}