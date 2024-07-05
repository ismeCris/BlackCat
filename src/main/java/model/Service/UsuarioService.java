package model.Service;

import model.Entity.Usuario;
import model.Repository.UsuarioRepository;

import javax.persistence.EntityManager;
<<<<<<< HEAD
import javax.persistence.EntityManagerFactory;
=======
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
import javax.persistence.Persistence;
import java.util.List;

public class UsuarioService {
    private EntityManager em;
    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
<<<<<<< HEAD
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPie");
        this.em = emf.createEntityManager();
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUser(Usuario usuario) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Usuario createdUser = (Usuario) usuarioRepository.create(usuario);
            em.getTransaction().commit();
            return createdUser;
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        }
    }

    public void deleteUser(long id) {
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            usuarioRepository.delete(id);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw ex;
        }
    }

    public Usuario login(String nome, String senha) {
        return usuarioRepository.login(nome, senha);
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public void updateUser(Usuario usuario) {
            usuarioRepository.update(usuario);
    }

=======
        this.em = Persistence.createEntityManagerFactory("BancoPie").createEntityManager();
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUser(Usuario usuario){
        return (Usuario) usuarioRepository.create(usuario);
    }

    //deletar usuario
    public  void deleteUser(long id){
        usuarioRepository.delete(id);
    }

    //login
    public  Usuario login(String nome, String senha){
        Usuario user = usuarioRepository.login(nome, senha);
        if(user !=null){
            System.out.println("Login bem-sucedido para o usuario:" + nome);
        }else {
            System.out.println("falha ao realizar login");
        }

        return user;
    }
    //listar usuario
    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    //editar usuario
    public  void updateUser(Usuario usuario){
        usuarioRepository.update(usuario);
    }
    //buscar por id
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
    public Usuario findUserById(long id) {
        return (Usuario) usuarioRepository.findById(id);
    }

<<<<<<< HEAD
    public Usuario findBysenha(String senha) {
        return usuarioRepository.findBysenha(senha);
    }
=======
    //conferir senha
    public Usuario findBysenha(String senha) {
        return usuarioRepository.findBysenha(senha);
    }

>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
}
