package model.Repository;

import model.Entity.Usuario;

import javax.persistence.*;
import java.util.List;


public class UsuarioRepository  implements  BasicCrud{

  EntityManager em = Persistence.createEntityManagerFactory("BancoPie").createEntityManager();

    @Override
    public Object create(Object object) {
        Usuario user = (Usuario) object;

        Query query = em.createQuery("SELECT u FROM  Usuario u where u.senha = :senha");
        query.setParameter("senha",user.getSenha());
        List<Usuario> userMesmaSenha = query.getResultList();

        if (!userMesmaSenha.isEmpty()){
            return null;
        }
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return findById(user.getId());
    }

    @Override
    public Object update(Object object) {
        Usuario user = (Usuario) object;

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var user = (Usuario) findById(id);
        em.remove(user);
        em.getTransaction().commit();
        System.out.println("Usuário com ID " + id + " deletado.");
    }

    @Override
    public Object findById(Object id) {
        try {
            Usuario usuarioInBd = em.find(Usuario.class, id);
            return usuarioInBd;
        } catch (Exception e) {

        }
        return null;
    }

    public  Usuario login(String nome, String senha){
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha", Usuario.class);
            query.setParameter("nome", nome);
            query.setParameter("senha", senha);
            return query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("Nenhum usuário encontrado com o nome e senha fornecidos.");
            return null;
        } catch (NonUniqueResultException e) {
            System.out.println("Mais de um usuário encontrado com o mesmo nome e senha. Isso não deveria acontecer.");
            return null;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar fazer login: " + e.getMessage());
            return null;
        }
    }

    public  List<Usuario> findAll(){
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

}
