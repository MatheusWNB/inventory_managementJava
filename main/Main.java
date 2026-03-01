package main;
import db.SqlEditInventory.ManagerEditInventory;
import db.SqlInventories.ManagerInventories;
import db.SqlUsers.ManagerUsers;
import edit_inventory.EditInventory.SetItem;
import java.util.Scanner;
import login_register.Login.UserLogin;
import login_register.RegisterInventories.NewInventory;
import login_register.RegisterUser.NewUser;
import utils.Utils;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String resposta;
        boolean loggedIn = false;

        ManagerUsers adminUsers = new ManagerUsers();
        ManagerInventories adminInventories = new ManagerInventories();
        ManagerEditInventory admEditInventory = new ManagerEditInventory();

        NewUser user = null;
        UserLogin login = new UserLogin();
        NewInventory inventory = null;
        SetItem edit = null;
        
        while(true){

            // Utils.limparTerminal();
            Utils.printTittle("MENU INICIAL");

            System.out.println("""
            (1) Fazer Login
            (2) Cadastrar nova conta
            (3) Sair do programa
            """);

            resposta = sc.nextLine();

            switch (resposta) {
                case "1":
                    Utils.printTittle("LOGIN");

                    login.login(sc, adminUsers);
                    loggedIn = true;
                    break;

                case "2":
                    user = new NewUser(sc, adminUsers);
                    adminUsers.newUser(user.getUserName(), user.getPassword());
                    break;

                case "3":
                    Utils.printTittle("ENCERRANDO O PROGRAMA");
            
                default:
                    break;
            }

            if(loggedIn == true){
                break;
            }
        }

        while(true){
            // Utils.limparTerminal();
            Utils.printTittle("SEUS ESTOQUES");
            
            System.out.println("""
            (1) Registrar um novo estoque
            (2) Visualizar e editar estoques
            (3) Sair do programa
            """);
            
            resposta = sc.nextLine();

            switch (resposta) {
                case "1":
                    inventory = new NewInventory(login.getUserName());
                    inventory.setInfos();
                    inventory.registerInventory(adminInventories);
                    
                    break;

                case "2":
                    adminInventories.printInventories(login.getUserName());  
                    edit = new SetItem(login.getUserName());
                    edit.regItem(admEditInventory);

                    break;

                default:
                    break;
            }
        }
    }
}
