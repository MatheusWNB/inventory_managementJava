package register;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import utils.Utils;

public class RegisterUser {
    static Scanner sc = new Scanner(System.in);

    static public class GerenciadorFichas{
        private List<String> users = new ArrayList<>();
        private List<FichaTecnica> lists = new ArrayList<>();

        private int id = 0;

        public GerenciadorFichas(){};

        void mostrarUsuarios(){
            Utils.limparTerminal();

            System.out.println(Utils.TITTLE_FORMAT + "USUÁRIOS CADASTRADOS" + Utils.TITTLE_FORMAT);
            for(int in = 0; in < id; in++){
               System.out.println(in + " -> " + users.get(in));
            }
        }

        public void mostrarFichaTecn(FichaTecnica obj){
            Utils.limparTerminal();

            System.out.println(
                "Nome: " + getUser(obj.getIdName())+"\n"+
                "Profissão: " + obj.profissao+"\n"+
                "Empresa: " + obj.empresa+"\n"+
                "Tempo Trabalhado: " + obj.tempoTrabalhado+" anos\n"
            );

            sc.nextLine();
        }

        public void registerFichaTecnica(FichaTecnica obj){
            lists.add(obj);
        }

        public FichaTecnica getList(Scanner sc){
            mostrarUsuarios();
            int option = Utils.validarEscolha(sc, 0, id - 1);
            return lists.get(option);
        }

        void setId(){
            id++;
        }

        int getId(){
            return id;
        }

        void addUser(String nameUser){
            users.add(nameUser);
            setId();
        }

        String getUser(int idName){
            return users.get(idName);
        }
    }

    static public class FichaNormal{
        private int idName;
        private String nome;
        private int idade;

        public FichaNormal(Scanner sc, GerenciadorFichas obj){
            cadastrarUsuario(sc, obj);
        }

        void cadastrarUsuario(Scanner sc, GerenciadorFichas obj){
            Utils.limparTerminal();
            int id = obj.getId();

            System.out.println(Utils.TITTLE_FORMAT + "CADASTRO" + Utils.TITTLE_FORMAT);
            System.out.print("Digite o seu nome: ");
            this.nome = sc.nextLine();

            System.out.print("Digite sua idade: ");
            this.idade = sc.nextInt();
            sc.nextLine();

            obj.addUser(this.nome);
            this.idName = id;
        }   

        int getIdName(){
            return this.idName;
        }
    }

    static public class FichaTecnica extends FichaNormal{
        private String profissao;
        private String empresa;
        private int tempoTrabalhado;

        public FichaTecnica(Scanner sc, GerenciadorFichas obj){
            super(sc, obj);
            cadastrarFichaTecn(sc);
        }

        void cadastrarFichaTecn (Scanner sc){
            Utils.limparTerminal();

            System.out.println(Utils.TITTLE_FORMAT + "CADASTRO FICHA TÉCNICA" + Utils.TITTLE_FORMAT);
            System.out.print("Sua profissão: ");
            this.profissao = sc.nextLine();

            System.out.print("Sua empresa onde trabalha: ");
            this.empresa = sc.nextLine();

            System.out.print("Seu tempo trabalhado em " + this.empresa+ ":");
            this.tempoTrabalhado = sc.nextInt();
            sc.nextLine();
        }
    }

    public static void main(String[] args) {
        GerenciadorFichas admin = new GerenciadorFichas();

        FichaTecnica ficha = new FichaTecnica(sc, admin);
        admin.registerFichaTecnica(ficha);
        ficha = admin.getList(sc);
        admin.mostrarFichaTecn(ficha);
        
    }
}
