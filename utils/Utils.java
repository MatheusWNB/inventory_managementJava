package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {
    private static int usuarioEscolha;
    public static final String TITTLE_FORMAT = "/".repeat(10);
    public static final String TITTLE_ERROR_FORMAT = "*".repeat(10);

    public static void limparBuffer(Scanner arg_sc){
        arg_sc.nextLine();
    }

    public static int validarEscolha(Scanner sc, int minimo, int maximo){
        while(true){
            System.out.println("Escolha uma opção: ");
            try{
                usuarioEscolha = sc.nextInt();
                
                if(usuarioEscolha < minimo || usuarioEscolha > maximo){
                    System.out.println(TITTLE_ERROR_FORMAT + "OPÇÃO INVÁLIDA!" + TITTLE_ERROR_FORMAT);
                    continue;
                    
                } else if(usuarioEscolha >= minimo && usuarioEscolha <= maximo){
                    limparBuffer(sc);
                    return usuarioEscolha;
                }
                
            } catch(InputMismatchException e){
                System.out.println(TITTLE_ERROR_FORMAT + "DIGITE APENAS NÚMEROS!" + TITTLE_ERROR_FORMAT);
                limparBuffer(sc);
                continue;
            }
            
        }
    }
    
}
