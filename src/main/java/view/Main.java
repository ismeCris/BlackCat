package view;

import controller.UsuarioController;
import model.Entity.Usuario;
import model.Repository.UsuarioRepository;
import model.Service.UsuarioService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPie");
    private static EntityManager em = emf.createEntityManager();

    private static UsuarioRepository usuarioRepository = new UsuarioRepository(em);
    private static UsuarioService usuarioService = new UsuarioService(usuarioRepository);
    private static UsuarioController usuarioController = new UsuarioController(usuarioService);


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

        //UsuarioController usuarioController = new UsuarioController();
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


        Usuario novoUser = new Usuario();

        System.out.println("Digite o nome do novo usuario:");
        String nome = sc.nextLine();
        novoUser.setNome(nome);

        System.out.println("Digite a senha do novo usuario:");
        String senha = sc.nextLine();

        Usuario usuarioExistente = usuarioRepository.findBysenha(senha);
        if ((usuarioExistente != null)){
            System.out.println("Já existe um funcionário com a mesma senha. Por favor, escolha outra senha.");
            return;
        }
        novoUser.setSenha(senha);

        System.out.println("Digite o tipo de usuario usuario: (True sendo adiministrador e false sendo funcionarios)");
        boolean userRole = sc.nextBoolean();
        novoUser.setUserRole(userRole);

        Usuario criandoUsuario = usuarioController.createUser(novoUser);

        if(criandoUsuario != null) {
            System.out.println("Novo usuario criado com sucesso.");
            System.out.println("ID: " + criandoUsuario.getId());
            System.out.println("Nome: " + criandoUsuario.getNome());
            System.out.println("Senha: " + criandoUsuario.getSenha());
            System.out.println("Email: " + criandoUsuario.getUserRole());
        }else {
            System.out.println("Falha ao criar novo usuario.");
        }
    }

    public static void listarUsuario(){

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
        System.out.println("Digite o ID do funcionário a ser editado:");
        long id = sc.nextLong();
        sc.nextLine();

        Usuario usuario = usuarioService.findUserById(id);
        if (usuario != null){
            System.out.println("Digite o novo nome do uruario  (ou pressione Enter para manter o atual: "+ usuario.getNome()+"):");
            String novoNome = sc.nextLine();
            usuario.setNome(novoNome);
            if (!novoNome.isEmpty()) {
                usuario.setNome(novoNome);
            }

            System.out.println("Digite a nova senha do uruario (ou pressione Enter para manter o atual: "+ usuario.getSenha()+"):");
            String novaSenha = sc.nextLine();
            usuario.setSenha(novaSenha);
            if (!novaSenha.isEmpty()) {
                usuario.setSenha(novaSenha);
            }
            System.out.println("Digite a novo tipo de uruario (ou pressione Enter para manter o atual: "+ usuario.getUserRole()+"):");
            String novoUserRole = sc.nextLine();
            usuario.setSenha(novaSenha);
            if (!novoUserRole.isEmpty()) {
                usuario.setSenha(novoUserRole);
            }

            usuarioController.updateUser(usuario);
            System.out.println("Funcionário atualizado com sucesso!");
        }else {
            System.out.println("Funcionário com o ID fornecido não encontrado.");
            aguardarEnter();
            gerenciarUser();
        }


    }


//====================================================================================================================
    private static void aguardarEnter() {
        System.out.println("Pressione Enter para continuar...");
        sc.nextLine();
    }

}
