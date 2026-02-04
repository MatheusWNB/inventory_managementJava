package login_register;

import java.util.Scanner;
import db.SqlUsers.ManagerUsers;
import utils.Utils;

public class Login {
    static public class UserLogin{
        private String username;

        public UserLogin(){};

        public void login(Scanner sc, ManagerUsers admin){
            String nome;
            String password;
            boolean validate;

            Utils.limparTerminal();
            while(true){

                System.out.println
                (Utils.TITTLE_FORMAT+
                    " FAÇA SEU LOGIN "+
                    Utils.TITTLE_FORMAT);

                System.out.print("Seu nome de usuário: ");
                nome = sc.nextLine();

                System.out.print("Sua senha: ");
                password = sc.nextLine();

                validate = admin.login(nome, password);
                if(validate == false){
                    System.out.println
                    (Utils.TITTLE_ERROR_FORMAT+
                        " NOME OU SENHA INVÁLIDOS! "+
                        Utils.TITTLE_ERROR_FORMAT);  

                    continue;
                }   
                this.username = nome;
                break;
            }

            Utils.limparTerminal();
            System.out.println
                (Utils.TITTLE_FORMAT+
                " LOGIN EFETUADO COM SUCESSO! "+
                Utils.TITTLE_FORMAT);
        }

        public String getUserName(){
            return this.username;
        }
    }
}
