package main;
import java.sql.SQLException;
import java.util.Scanner;

import db.SqlInventories.ManagerInventories;
import db.SqlUsers.ManagerUsers;
import login_register.RegisterUser.*;
import utils.Utils;
import login_register.Login.UserLogin;
import login_register.RegisterInven.NewInventory;;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String resposta;
        boolean loggedIn = false;

        ManagerUsers adminUsers = new ManagerUsers();
        ManagerInventories adminInventories = new ManagerInventories();

        NewUser user = null;
        UserLogin login = new UserLogin();
        NewInventory inventory = null;
        
        while(true){

            Utils.limparTerminal();
            System.out.println(
                Utils.TITTLE+ " MENU INICIAL "+ Utils.TITTLE
            );

            System.out.println(
                "(1) Fazer Login\n"+
                "(2) Cadastrar nova conta\n"+
                "(3) Sair do programa"
            );

            resposta = sc.nextLine();

            switch (resposta) {
                case "1":
                    System.out.println
                        (Utils.TITTLE+" LOGIN "+Utils.TITTLE);

                    login.login(sc, adminUsers);
                    loggedIn = true;
                    break;

                case "2":
                    user = new NewUser(sc, adminUsers);
                    adminUsers.newUser(user.getUserName(), user.getPassword());
                    break;

                case "3":
                    System.out.println
                        (Utils.TITTLE+" ENCERRANDO O PROGRAMA "+Utils.TITTLE);
            
                default:
                    break;
            }

            if(loggedIn == true){
                break;
            }

            continue;
        }

        while(true){
            // Utils.limparTerminal();
            
            System.out.println(Utils.TITTLE + " MENU ESTOQUE " + Utils.TITTLE);
            System.out.println(
                "(1) Registrar um novo estoque\n"+
                "(2) Visualizar e editar estoques\n"+
                "(3) Sair do programa\n"
            );
            
            resposta = sc.nextLine();

            switch (resposta) {
                case "1":
                    inventory = new NewInventory(login.getUserName());
                    inventory.setInfos();
                    inventory.registerInventory(adminInventories);
                    
                    break;

                case "2":
                    adminInventories.printInventories(login.getUserName());
            
                default:
                    break;
            }
        }
    }
}
