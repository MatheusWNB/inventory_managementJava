package register;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;
import register.SqlUsers.ManagerUsers;


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

        public NewUser(Scanner sc, ManagerUsers admin){
            this.nome = setNome(sc);
            this.senha = setSenha(sc);

            try{
                admin.newUser(nome, senha);
            }catch(SQLException e){
                System.out.println(
                    Utils.TITTLE_ERROR_FORMAT+
                    " REGISTRO DE USUÁRIO FALHOU! "+
                    Utils.TITTLE_ERROR_FORMAT
                );

                System.out.println(e.getMessage());
                e.getStackTrace();
            }
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
        ManagerUsers admin = new ManagerUsers();
        NewUser user = new NewUser(sc, admin);

        System.out.println("Nome: " + user.getName());
        System.out.println("Senha: " + user.getPassword());
        admin.selectAll();

        
    }
}