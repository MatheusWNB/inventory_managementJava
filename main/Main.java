package main;
import java.util.Scanner;

import register.RegisterInventory;
import register.RegisterUser;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(
            "Seja bem-vindo ao seu estoque pessoal."+
            "Vamos iniciar com o seu cadastro: "
        );
        RegisterUser.main(args);
        RegisterInventory.main(args);
    }
}
