package register;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

public class RegisterUser {
    static Scanner sc = new Scanner(System.in);

    static class UsersManagement{
        private static List<String> users = new ArrayList<>();
        private static List<String> passwords = new ArrayList<>();

        public UsersManagement(){};
        
        static void setNewNameUser(String name){
            users.add(name);
        }

        static void setNewPasswordUser(String password){
            passwords.add(password);
        }

    }

    static class NewUser{
        String nome;
        String senha;

        public NewUser(Scanner sc){
            this.nome = setNome(sc);
            this.senha = setSenha(sc);

        }

        public String setNome(Scanner sc) {
            System.out.print("Nome de usuário: ");
            String tempNome = sc.nextLine();
            return tempNome;
        }

        public String setSenha(Scanner sc) {
            System.out.print("Sua senha: ");
            String tempSenha = sc.nextLine();
            return tempSenha;
        }

        String getName(){
            return this.nome;
        }

         String getPassword(){
            return this.senha;
        }


    }
    public static void main(String[] args) {
        System.out.println(Utils.TITTLE_FORMAT + "CADASTRO DE USUÁRIO" + Utils.TITTLE_FORMAT);
        NewUser user = new NewUser(sc);
        UsersManagement.setNewNameUser(user.getName());
        UsersManagement.setNewPasswordUser(user.getPassword());

        
    }
}
