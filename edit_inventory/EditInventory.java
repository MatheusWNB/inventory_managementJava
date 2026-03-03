package edit_inventory;

import db.SqlEditInventory.ManagerEditInventory;
import db.SqlInventories.ManagerInventories;
import java.util.Scanner;
import utils.Utils;

public class EditInventory {
    static public class SetItem{
        private String user;
        private static Scanner scInventory = new Scanner(System.in);
        private long idInventory;
        private String item;
        private long amount;
        private double unitPrice;
        private double totalPrice;

        public SetItem(String userName, ManagerInventories obj){
            this.idInventory = getInventory(obj);
            this.user = userName;
            this.unitPrice = -1;
            this.totalPrice = -1;
        };

        public long getInventory(ManagerInventories obj){
            System.out.print("Digite qual inventário deseja modificar: ");
            int id = scInventory.nextInt();

            long idInventory = obj.getInventory(id);
            return idInventory;
        }

        public long retInventory(){return this.idInventory;}

        public boolean regItem(ManagerEditInventory obj){
            Utils.printTittle("REGISTRO DE ITEM");

            Utils.limparBuffer(scInventory);
            System.out.print("Item a ser registrado: ");
            this.item = scInventory.nextLine();

            System.out.print("Quantidade: ");
            this.amount = scInventory.nextLong();

            int escolha;
            boolean validate;
            while(true){
                System.out.println("""
                    Digite:
                    (0) -> Cadastrar preço
                    (1) -> Finalizar registro
                """);

                escolha = scInventory.nextInt();

                switch(escolha){
                    case 0:
                        System.out.print("Digite o preço do item: ");
                        this.unitPrice = scInventory.nextDouble();
                        this.totalPrice = this.unitPrice * this.amount;

                    case 1:
                        validate = obj.regItem(this.idInventory, this.item, this.amount, 
                                this.unitPrice, this.totalPrice);

                        break;

                    default:
                        Utils.printError("DIGITE UMA OPÇÃO VÁLIDA");
                        continue;
                }

                return validate;
            }
        }
    }
}
