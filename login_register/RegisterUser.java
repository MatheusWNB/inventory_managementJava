package login_register;

import db.SqlUsers.ManagerUsers;
import java.util.Scanner;
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

        public String setNome(Scanner sc, ManagerUsers admin) {
            boolean any;
            String name;
            
            while(true){
                Utils.limparTerminal();

                System.out.print("Nome de usuário (mínimo 8 caracteres): ");
                name = sc.nextLine();
                
                if(name.length() < 8 || name.isBlank() == true){
                    Utils.printError("NOME INVÁLIDO! POR FAVOR, TENTE NOVAMENTE");
                    continue;
                }

                any = admin.validateName(name);

                if(any == true){
                    Utils.limparTerminal();
                    Utils.printError("ESSE NOME DE USUÁRIO JÁ ESTÁ EM USO!");
                    continue;
                }

                return name;
            }
        }

        public String setSenha(Scanner sc) {
            String password;

            while(true){
                Utils.limparTerminal();

                System.out.print("Sua senha (mínimo 6 caracteres): ");
                password = sc.nextLine();   
                
                if(password.length() < 6 || password.isBlank() == true)
                    Utils.printError("SENHA INVÁLIDA! POR FAVOR, TENTE NOVAMENTE");
                else 
                    break;
            }

            return password;
        }

        public String getUserName(){
            return this.userName;
        }

        public String getPassword(){
            return this.password;
        }
    }
}