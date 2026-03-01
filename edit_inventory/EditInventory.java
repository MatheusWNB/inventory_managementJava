package edit_inventory;

import java.util.Scanner;
import jdk.jshell.execution.Util;
import utils.Utils;
// id_inventory
// item 
// amount 
// unit_price 
// total_price 

public class EditInventory {
    static public class SetItem{
        private long idInventory; // = getId
        private String item;
        private long amount;
        private double unitPrice;
        private double totalPrice;

        public SetItem(){};

        public boolean regItem(Scanner sc){
            System.out.println(
                Utils.TITTLE + " REGISTRO DE ITEM " + Utils.TITTLE
            );
        }
    }
}
