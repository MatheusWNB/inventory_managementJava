package login_register;

import db.SqlInventories.ManagerInventories;
import java.util.Scanner;
import utils.Utils;

public class RegisterInventories {
    static public class NewInventory{
        private String userName;
        private String password;
        private String nameInventory;
        static Scanner scRegister = new Scanner(System.in);

        public NewInventory(String userName){
            this.userName = userName;
        }

        public void setInfos(){
            String resposta;
            boolean loop = true;

            Utils.limparTerminal();
            System.out.print("Digite o nome do  seu inventário: ");
            this.nameInventory = scRegister.nextLine();

            while(loop){
                System.out.print("Deseja adicionar uma senha ao seu inventário?: ");
                resposta = scRegister.nextLine();
                
                switch(resposta){
                    case "n":
                        this.password = null;
                        loop = false;
                        break;

                    case "s":
                        System.out.println("Digite a sua senha: ");
                        this.password = scRegister.nextLine();
                        loop = false;
                        break;

                    default: 
                        Utils.limparTerminal();
                        System.out.println("Digite uma opção válida!");
                        break;
                }
            }
        }

        public boolean registerInventory(ManagerInventories admin){
            boolean validate = admin.setInventory(this.userName, this.nameInventory, this.password);

            if (validate == true){
                Utils.printTittle("ESTOQUE CRIADO COM SUCESSO!");

            } else if(validate == false){
                Utils.printError("NÃO FOI POSSÍVEL CRIAR SEU ESTOQUE!");
            }

            return validate;
        }
    }
}
