package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    private static int usuarioEscolha;
    public static final String TITTLE_FORMAT = "/".repeat(10);
    public static final String TITTLE_ERROR_FORMAT = "*".repeat(10);

    public static void limparTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void limparBuffer(Scanner arg_sc){
        arg_sc.nextLine();
    }

    public static int validarEscolha(Scanner sc, int minimo, int maximo){
        while(true){
            System.out.print("Escolha uma opção: ");
            try{
                usuarioEscolha = sc.nextInt();
                
                if(usuarioEscolha < minimo || usuarioEscolha > maximo){
                    limparTerminal();
                    System.out.println(TITTLE_ERROR_FORMAT + "OPÇÃO INVÁLIDA!" + TITTLE_ERROR_FORMAT);
                    continue;
                    
                } else if(usuarioEscolha >= minimo && usuarioEscolha <= maximo){
                    limparBuffer(sc);
                    return usuarioEscolha;
                }
                
            } catch(InputMismatchException e){
                limparTerminal();
                System.out.println(TITTLE_ERROR_FORMAT + "DIGITE APENAS NÚMEROS!" + TITTLE_ERROR_FORMAT);
                limparBuffer(sc);
                continue;
            }
            
        }
    }

    public static void errorSql(Exception e){
        System.out.println
        (Utils.TITTLE_ERROR_FORMAT+
            " CONEXÃO COM O DB FALHOU! "+
            Utils.TITTLE_ERROR_FORMAT);

            System.out.println(e.getMessage());
            e.getStackTrace();
    }
}
