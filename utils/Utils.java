package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    private static int usuarioEscolha;
    public static final String TITTLE = "/".repeat(10);
    public static final String ERROR = "*".repeat(10);

    public static void limparTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printTittle(String text){
        System.out.println(
            TITTLE + " " + text + " " + TITTLE
        );
    }

    public static void printError(String text){
        System.out.println(
            ERROR + " " + text + " " + ERROR
        );
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
                    System.out.println(ERROR + "OPÇÃO INVÁLIDA!" + ERROR);

                } else if(usuarioEscolha >= minimo && usuarioEscolha <= maximo){
                    limparBuffer(sc);
                    return usuarioEscolha;
                }
                
            } catch(InputMismatchException e){
                limparTerminal();
                System.out.println(ERROR + "DIGITE APENAS NÚMEROS!" + ERROR);
                limparBuffer(sc);
            }
        }
    }

    public static void errorSql(Exception e){
        printError("CONEXÃO COM O DB FALHOU!");
        System.out.println(e.getMessage());
        e.getStackTrace();
    }
}
