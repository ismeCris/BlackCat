package controller;

import model.Entity.Usuario;
import model.Service.UsuarioService;

import java.util.List;

public class UsuarioController {


    private UsuarioService usuarioService;

    public UsuarioController() {
        this.usuarioService = new UsuarioService();
    }

    public Usuario login(String nome, String senha) {
        return usuarioService.login(nome, senha);
    }

    public List<Usuario> findAll() {
        return usuarioService.findAll();
    }

    public void deleteUserById(long id){
        usuarioService.deleteUser(id);
    }

}
