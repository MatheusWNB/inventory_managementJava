package main;
import java.sql.SQLException;
import java.util.Scanner;

import db.SqlUsers.ManagerUsers;
import login_register.RegisterInventory;
import login_register.RegisterUser.*;
import utils.Utils;
import login_register.Login.UserLogin;;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String username;

        ManagerUsers admin = new ManagerUsers();
        NewUser user = new NewUser(sc, admin);
        UserLogin login = new UserLogin();


        System.out.println(
            "Seja bem-vindo ao seu estoque pessoal."+
            "Vamos iniciar com o seu cadastro: "
        );

        System.out.println(Utils.TITTLE_FORMAT + "CADASTRO DE USUÁRIO" + Utils.TITTLE_FORMAT);

        try{
            admin.newUser(user.getUserName(), user.getPassword());
        }catch(SQLException e){
            Utils.errorSql(e);
        }

        login.login(sc, admin);
        username = login.getUserName();
        System.out.println(username);


        admin.selectAll();


    }
}
