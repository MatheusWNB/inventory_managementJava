package register;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Utils;

public class RegisterInventory {
    static Scanner scanner = new Scanner(System.in);

    static class Estoque{
        private int estoqueProdutos;
        private String nomeEstoque;
        private int idProdutos;
        private List<Integer> idsProdutos = new ArrayList<>();
        private List<String> estoquesProdutos = new ArrayList<>();

        public Estoque (String argNome){
            this.estoqueProdutos = 0;
            this.nomeEstoque = argNome;
            this.idProdutos = 0;
        }

        void mostrarEstoque(){
            System.err.println(Utils.TITTLE_FORMAT + "SEU ESTOQUE" + Utils.TITTLE_FORMAT);
            System.out.println(this.nomeEstoque);
            for(int i = 0; i < idsProdutos.size(); i++){
                System.out.println(idsProdutos.get(i) + "-> " + estoquesProdutos.get(i));
            }
        }
        int adicionarItem(Scanner argSc){
            int validacao = 0;
            String item;

            System.out.println("Adicionar novo item? (0/1): ");
            validacao = argSc.nextInt();
            Utils.limparBuffer(argSc);

            if(validacao == 1){
                return validacao;

            } else if (validacao == 0){
                System.out.print("Digite o item que deseja adicionar ('-1' para sair): ");
                item = argSc.nextLine();
                estoquesProdutos.add(idProdutos, item);
                idsProdutos.add(idProdutos);
                idProdutos++;
            }
            
            return validacao;
        }

        String getName(){
            return this.nomeEstoque;
        }
    } 

    static class GerenciarEstoque{
        public static final int MAXIMO_ESTOQUES = 5;
        public static final int MAXIMO_PRODUTOS = 10;

        private static int totalProdutos = 0;
        private static int totalEstoques = 0;
        private static List<String> nomesEstoques = new ArrayList<>();
        private static List<Estoque> estoques = new ArrayList<>();

        public GerenciarEstoque(){};

        public void setEstoque(Estoque obj, String name){
            totalEstoques++;
            nomesEstoques.add(name);
            estoques.add(obj);
        }

        public Estoque getEstoque(Scanner sc){
            for(int i = 0; i < totalEstoques; i++){
                System.out.println(i + " -> " + nomesEstoques.get(i));
            }

            int escolha = Utils.validarEscolha(sc, 0, totalEstoques - 1);
            return estoques.get(escolha);
        }

    }

    public static void main(String[] args) {
        Estoque estoque;
        int usuarioEscolha;
        int validacao;

        GerenciarEstoque admin = new GerenciarEstoque();

        while(true){
            System.out.println(Utils.TITTLE_FORMAT + "MENU INICIAL" + Utils.TITTLE_FORMAT);
            System.out.println(
                "(1) Registrar um novo estoque\n"+
                "(2) Visualizar e editar estoque\n"+
                "(3) Sair do programa\n"
            );
            usuarioEscolha = Utils.validarEscolha(scanner,1,3);

            switch(usuarioEscolha){
                case 1:
                    String nomeEstoque;

                    System.out.println(Utils.TITTLE_FORMAT + "CADASTRAR NOVO ESTOQUE" + Utils.TITTLE_FORMAT);
                    System.out.print("Digite o nome do seu novo estoque: ");
                    nomeEstoque = scanner.nextLine();

                    estoque = new Estoque(nomeEstoque);
                    admin.setEstoque(estoque, nomeEstoque);

                    while(true){
                        validacao = estoque.adicionarItem(scanner);
                        
                        if(validacao == 1)
                            break;
                        else if (validacao == 0)
                            continue;

                    }   

                case 2:
                    estoque = admin.getEstoque(scanner);
                    estoque.mostrarEstoque();
            }
        }
    }
}
