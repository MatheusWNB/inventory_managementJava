package login_register;

import db.SqlUsers.ManagerUsers;
import java.util.Scanner;
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
                    Utils.printError("NOME OU SENHA INVÁLIDOS!");
                    continue;
                }   
                this.username = nome;
                break;
            }

            Utils.limparTerminal();
            Utils.printTittle("LOGIN EFETUADO COM SUCESSO!");
        }

        public String getUserName(){
            return this.username;
        }
    }
}
