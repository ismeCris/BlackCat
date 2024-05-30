package view;

import controller.UsuarioController;
import model.Entity.Usuario;
import model.Repository.UsuarioRepository;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {

        Login();
    }
//=====================================================================================================================

    public static void Login(){
        System.out.println("Realize o login");

        System.out.println("Nome de usuario:");
        String nome = sc.nextLine();

        System.out.println("Senha:");
        String senha = sc.nextLine();

        UsuarioController usuarioController = new UsuarioController();
        Usuario autenticarUser = usuarioController.login(nome, senha);

        if(autenticarUser != null){
            System.out.println("Bem-Vind@, " + autenticarUser.getNome()+ "!");
            menuprinciapal();
        }else {
            System.out.println("Nome de usuário ou senha incorretos. Por favor, tente novamente.");
            Login();
        }

    }
//=====================================================================================================================
    public static void menuprinciapal() {
        Scanner sc = new Scanner(System.in);


        boolean sair = false;

        while(!sair) {

            System.out.println("===== Menu Principal =====");
            System.out.println("-> 1. Gerenciar usuarios");
            System.out.println("-> 2. Gerenciar vendas");
            System.out.println("-> 3. Gerenciar vendas");
            System.out.println("-> 4. Relatorios");
            System.out.println("-> 0. sair \n");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {
                case 1:
                    gerenciarUser();
                    aguardarEnter();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 0:
                    sair = true;
                    System.out.println("Volte sempre..........");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente");

            }

        }

    }
//=====================================================================================================================
    private static void gerenciarUser() {
        UsuarioController usuarioController = new UsuarioController();

        System.out.println("=======- Gestao de funcionarios -=======");
        System.out.println("1 - Novo usuario");
        System.out.println("2 - Lista de usuario");
        System.out.println("3 - Excluir usuario");
        System.out.println("4 - Editar usuario");
        System.out.println("0 - Voltar ao menu principal");
        System.out.println("Escolha uma opção:");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao) {
            case 1:
                criaUsuario();
                aguardarEnter();
                gerenciarUser();
                break;
            case 2:
                listarUsuario();
                aguardarEnter();
                gerenciarUser();

                break;
            case 3:
                System.out.println("Digite o ID do funcionário a ser excluído:");
                long deleteId = sc.nextLong();
                sc.nextLine();
                usuarioController.deleteUserById(deleteId);
                System.out.println("Funcionário excluído.");
                aguardarEnter();
                gerenciarUser();
                break;
            case 4:
                editarUsuario();
                aguardarEnter();
                gerenciarUser();
                break;
            case 0:
                menuprinciapal();
                break;
            default:
                System.out.println("Opção inválida.");
                gerenciarUser();
                break;
        }
    }

    public static void criaUsuario(){

    }

    public static void listarUsuario(){

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            System.out.println("Não há usuarios cadastrados no sistema.");
        }else {
            System.out.println("Lista de usuarios cadastrados");
            for (Usuario usuario : usuarios){
                System.out.println("Id:"+usuario.getId()+ ", Nome: "+usuario.getNome());
            }
        }
    }

    public static void editarUsuario(){

    }


//====================================================================================================================
    private static void aguardarEnter() {
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }

}
