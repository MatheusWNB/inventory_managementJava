package register;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;
import register.SqlUsers.ManagerUsers;


public class RegisterUser {
    static Scanner sc = new Scanner(System.in);

    static class NewUser{
        private String nome;
        private String senha;

        public NewUser(Scanner sc, ManagerUsers admin){
            this.nome = setNome(sc, admin);
            this.senha = setSenha(sc);
        }

        void registerUser(ManagerUsers admin){
            try{
                admin.newUser(this.nome, this.senha);
                System.out.println("CADASTRO CONCLUIDO");
            }catch(SQLException e){
                System.out.println
                (Utils.TITTLE_ERROR_FORMAT+
                    " REGISTRO DE USUÁRIO FALHOU! "+
                    Utils.TITTLE_ERROR_FORMAT);

                System.out.println(e.getMessage());
                e.getStackTrace();
            }
        }

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
                break;
            }
            
            Utils.limparTerminal();
            System.out.println
            (Utils.TITTLE_FORMAT+
                " LOGIN EFETUADO COM SUCESSO! "+
                Utils.TITTLE_FORMAT);
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
        try{
            admin.newUser(user.getName(), user.getPassword());
        }catch(SQLException e){
            System.out.println("Falhou");
        }

        user.login(sc, admin);
        admin.selectAll();
    }
}