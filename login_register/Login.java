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

                System.out.print("Seu nome de usuário: ");
                nome = sc.nextLine();

                System.out.print("Sua senha: ");
                password = sc.nextLine();

                validate = admin.login(nome, password);
                if(validate == false){
                    System.out.println
                    (Utils.ERROR+
                        " NOME OU SENHA INVÁLIDOS! "+
                        Utils.ERROR);  

                    continue;
                }   
                this.username = nome;
                break;
            }

            Utils.limparTerminal();
            System.out.println
                (Utils.TITTLE+
                " LOGIN EFETUADO COM SUCESSO! "+
                Utils.TITTLE);
        }

        public String getUserName(){
            return this.username;
        }
    }
}
