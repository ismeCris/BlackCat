package model.Service;

import model.Entity.Usuario;
import model.Repository.UsuarioRepository;

import java.util.List;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    // Construtor para inicializar o repositório
    public UsuarioService() {
        this.usuarioRepository = new UsuarioRepository();
    }

    public  void deleteUser(long id){
        usuarioRepository.delete(id);
    }


    public  Usuario login(String nome, String senha){
        Usuario user = usuarioRepository.login(nome, senha);
        if(user !=null){
            System.out.println("Login bem-sucedido para o usuario:" + nome);
        }else {
            System.out.println("falha ao realizar login");
        }

        return user;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

}
