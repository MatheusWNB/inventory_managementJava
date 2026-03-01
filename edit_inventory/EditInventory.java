package edit_inventory;

import java.util.Scanner;

import db.SqlEditInventory;
import jdk.jshell.execution.Util;
import utils.Utils;
import login_register.RegisterUser.NewUser;
import env.SqlEditInventory;

public class EditInventory {
    static public class SetItem{
        private String user;
        private long idInventory;
        private String item;
        private long amount;
        private double unitPrice;
        private double totalPrice;
        private Scanner scInventory;

        public SetItem(NewUser obj){
            scInventory = new Scanner(System.in);
            this.user = obj.getUserName();
            this.idInventory = getInventory(scInventory);
            this.unitPrice = -1;
            this.totalPrice = -1;
        };

        public int getInventory(Scanner sc){
            System.out.print("Digite qual inventário deseja modificar: ");
            int id = sc.nextInt();
            return id;
        }

        public boolean regItem(Scanner sc, SqlEditInventory obj){
            Utils.printTittle("REGISTRO DE ITEM");

            System.out.print("Item a ser registrado: ");
            this.item = sc.nextLine();

            System.out.print("Quantidade: ");
            this.amount = sc.nextLong();

            int escolha;
            boolean loop = true;
            while(true){
                System.out.println("""
                    Digite:
                    (0) -> Cadastrar preço
                    (1) -> Finalizar registro
                """);

                escolha = sc.nextInt();

                switch(escolha){
                    case 0:
                        System.out.print("Digite o preço do item: ");
                        this.unitPrice = sc.nextDouble();
                        loop = false;
                        break;

                    case 1:
                        loop = false;

                    default:
                        Utils.printError("DIGITE UMA OPÇÃO VÁLIDA");
                }
            }
        }
    }
}
