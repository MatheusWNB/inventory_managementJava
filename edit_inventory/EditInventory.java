package edit_inventory;

import db.SqlEditInventory.ManagerEditInventory;
import java.util.Scanner;
import utils.Utils;

public class EditInventory {
    static public class SetItem{
        private String user;
        private long idInventory;
        private String item;
        private long amount;
        private double unitPrice;
        private double totalPrice;
        private Scanner scInventory;

        public SetItem(String userName){
            scInventory = new Scanner(System.in);
            this.user = userName;
            this.idInventory = getInventory(scInventory);
            this.unitPrice = -1;
            this.totalPrice = -1;
        };

        public long getInventory(Scanner sc){
            System.out.print("Digite qual inventário deseja modificar: ");
            int id = sc.nextInt();
            return id;
        }

        public long getInventory(){return this.idInventory;}

        public boolean regItem(ManagerEditInventory obj){
            Utils.printTittle("REGISTRO DE ITEM");

            Utils.limparBuffer(scInventory);
            System.out.print("Item a ser registrado: ");
            this.item = scInventory.nextLine();

            System.out.print("Quantidade: ");
            this.amount = scInventory.nextLong();

            int escolha;
            boolean loop = true;
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

                        loop = false;
                        break;

                    case 1:
                        loop = false;

                    default:
                        Utils.printError("DIGITE UMA OPÇÃO VÁLIDA");
                }

                boolean validate;
                validate = obj.regItem(this.idInventory, this.item, this.amount, 
                    this.unitPrice, this.totalPrice);

                return validate;
            }
        }
    }
}
