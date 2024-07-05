package model.Repository;

import model.Entity.ProdutoHasVenda;
import model.Entity.Produtos;
import model.Entity.Usuario;
import model.Entity.Vendas;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class VendaRepository implements BasicCrud{

    private EntityManager em;
    private VendaRepository vendaRepository;

    public VendaRepository(EntityManager em){
        this.em = em;
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Object create(Object object) {
        Vendas vendas = (Vendas) object;
<<<<<<< HEAD
        try {
        	em.getTransaction().begin();
        	em.persist(vendas);
        	em.getTransaction().commit();
			
        	return findById(vendas.getId());
		} catch (javax.persistence.PersistenceException pe) {
	        if (pe.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
	            org.hibernate.exception.ConstraintViolationException cve = 
	                (org.hibernate.exception.ConstraintViolationException) pe.getCause();
	            System.err.println("Erro de violação de restrição: " + cve.getConstraintName());
	        } else {
	            System.err.println("Erro de persistência: " + pe.getMessage());
	        }
	        return null;
	    } catch (Exception e) {
	        System.err.println("Erro geral: " + e.getMessage());
	        return null;
	    }
=======

        em.getTransaction().begin();
        em.persist(vendas);
        em.getTransaction().commit();
        return findById(vendas.getId());
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
    }

    @Override
    public Object update(Object object) {
<<<<<<< HEAD
        Vendas venda = (Vendas) object;
      try {
    	  em.getTransaction().begin();
    	  em.merge(venda);
    	  em.getTransaction().commit();
    	  return findById(venda);
      }catch (Exception e) {
		em.getTransaction().rollback();
	}
=======
        Usuario user = (Usuario) object;

        // Verificar se a nova senha já está sendo usada por outro usuário
        Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.senha = :senha AND u.id != :userId");
        query.setParameter("senha", user.getSenha());
        query.setParameter("userId", user.getId());
        List<Usuario> usuariosComMesmaSenha = query.getResultList();

        if (!usuariosComMesmaSenha.isEmpty()) {
            System.out.println("A senha fornecida já está sendo usada por outro usuário. A senha não foi alterada.");
            return null;
        }

        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd

        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var vendas =(Vendas)findById(id);
        em.remove(vendas);
        em.getTransaction().commit();
        System.out.println("Venda com ID " + id + " deletado.");

    }

    @Override
    public Object findById(Object id) {
        try {
            Vendas vendaInbBd = em.find(Vendas.class,id);
            return  vendaInbBd;
        } catch (Exception e) {

        }
        return null;
    }

<<<<<<< HEAD
    public List<Vendas> findALl(){
=======
    public List<Vendas>findALl(){
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
        return em.createQuery("SELECT v FROM Vendas v ",Vendas.class).getResultList();
    }

    public void save(Vendas venda) {
        em.getTransaction().begin();
        em.persist(venda);
        em.getTransaction().commit();
    }

<<<<<<< HEAD
    public ProdutoHasVenda saveProdutoHasVenda(ProdutoHasVenda produtoHasVenda) {
    	try {
    		em.getTransaction().begin();
    		em.persist(produtoHasVenda);
    		em.getTransaction().commit();
    		
    		return findProdutoHasVendaById(produtoHasVenda);
    		
    	}catch (Exception e) {
			em.getTransaction().rollback();
			return null;
		}
    }
    
    public ProdutoHasVenda findProdutoHasVendaById(Object id) {
        try {
        	ProdutoHasVenda prodHasVendInbBd = em.find(ProdutoHasVenda.class,id);
            return  prodHasVendInbBd;
        } catch (Exception e) {
        	em.getTransaction().rollback();
        }
        return null;
    	
=======
    public void saveProdutoHasVenda(ProdutoHasVenda produtoHasVenda) {
        em.getTransaction().begin();
        em.persist(produtoHasVenda);
        em.getTransaction().commit();
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd
    }

}
