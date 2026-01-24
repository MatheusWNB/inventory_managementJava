package register;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utils.Utils;

public class RegisterInventory {
    static Scanner scanner = new Scanner(System.in);

    static class GerenciarEstoques{
        private static int totalProdutos = 0;
        private static int totalEstoques = 0;
        private static List<String> nomesEstoques = new ArrayList<>();
        private static List<Estoque> estoques = new ArrayList<>();
        private static List<Integer> totalCadaEstoque = new ArrayList<>();

        public GerenciarEstoques(){};

        static void setEstoque(Estoque obj, String name){
            totalEstoques++;
            nomesEstoques.add(name);
            estoques.add(obj);
        }

        static void setTotalProdutos(){
            totalProdutos++;
        }

        static void setTotalCadaEstoque(int total, int idEstoque){
            totalCadaEstoque.add(idEstoque, total);
        }

        static Estoque getEstoque(Scanner sc){
            for(int i = 0; i < totalEstoques; i++){
                System.out.println(i + " -> " + nomesEstoques.get(i));
            }

            int escolha = Utils.validarEscolha(sc, 0, totalEstoques - 1);
            return estoques.get(escolha);
        }

        static void getAllEstoques(){
            for(int i = 0; i < totalEstoques; i++){
                System.out.println("Estoque " + i + " -> " + totalCadaEstoque.get(i) + " Produtos.");
                System.out.println("----------------");
            }

            System.out.println("Total de produtos: " + totalProdutos);
        }
    }

    static class Estoque{
        private String nomeEstoque;
        private int idProdutos;
        private int idEstoque;
        private static int idsEstoques = 0;
        private List<String> estoquesProdutos = new ArrayList<>();

        public Estoque (String argNome){
            this.nomeEstoque = argNome;
            this.idProdutos = 0;
            this.idEstoque = idsEstoques;
            idsEstoques++;
        }

        void mostrarEstoque(){
            System.err.println(Utils.TITTLE_FORMAT + "SEU ESTOQUE" + Utils.TITTLE_FORMAT);
            System.out.println(this.nomeEstoque);
            for(int i = 0; i < idProdutos; i++){
                System.out.println(i + "-> " + estoquesProdutos.get(i));
            }
        }
        void adicionarItem(Scanner argSc){
            String item;

            System.out.print("Digite o item que deseja adicionar: ");
            item = argSc.nextLine();

            estoquesProdutos.add(idProdutos, item);
            setIdProdutos();
            GerenciarEstoques.setTotalProdutos();
        }

        String getName(){
            return this.nomeEstoque;
        }

        int getTotalProdutos(){
            return this.idProdutos;
        }

        void setIdProdutos(){
            this.idProdutos++;
        }

        int getIdProdutos(){
            return this.idProdutos;
        }

        int getIdEstoque(){
            return this.idEstoque;
        }
    } 

    public static void main(String[] args) {
        Estoque estoque;
        int usuarioEscolha;
        int validacao;
        boolean laco = true;

        while(laco){
            Utils.limparTerminal();
            
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

                    Utils.limparTerminal();

                    System.out.println(Utils.TITTLE_FORMAT + "CADASTRAR NOVO ESTOQUE" + Utils.TITTLE_FORMAT);
                    System.out.print("Digite o nome do seu novo estoque: ");
                    nomeEstoque = scanner.nextLine();

                    estoque = new Estoque(nomeEstoque);
                    GerenciarEstoques.setEstoque(estoque, nomeEstoque);

                    while(true){
                        System.out.println(
                            "(0)Registrar novo item\n"+
                            "(1)Sair"
                        );

                        validacao = Utils.validarEscolha(scanner, 0, 1);
                        if(validacao == 1){
                            GerenciarEstoques.setTotalCadaEstoque(estoque.getTotalProdutos(), estoque.getIdEstoque());
                            break;
                        }else if(validacao == 0){
                            estoque.adicionarItem(scanner);
                            Utils.limparTerminal();
                            System.out.println(Utils.TITTLE_FORMAT + "ITEM REGISTRADO!" + Utils.TITTLE_FORMAT);
                        }else{
                            System.out.println(
                                Utils.TITTLE_ERROR_FORMAT + "ERRO" + Utils.TITTLE_ERROR_FORMAT
                            );
                            break;
                        }
                    }   

                    break;

                case 2:
                    Utils.limparTerminal();

                    estoque = GerenciarEstoques.getEstoque(scanner);
                    estoque.mostrarEstoque();
                    scanner.nextLine();
                    GerenciarEstoques.getAllEstoques();
                    scanner.nextLine();
                    break;

                case 3:
                    Utils.limparTerminal();

                    System.out.println(Utils.TITTLE_FORMAT + "FIM DO PROGRAMA" + Utils.TITTLE_FORMAT);
                    laco = false;
            }
        }
    }
}
