package model.Repository;

import model.Entity.Produtos;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

<<<<<<< HEAD
public class ProdutoRepository implements BasicCrud {
	private EntityManager em;

	public ProdutoRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Object create(Object object) {
		Produtos produto = (Produtos) object;

		Query query = em.createQuery("SELECT p FROM  Produtos p where p.nome = :nome");
		query.setParameter("nome", produto.getNome());
		List<Produtos> msmNome = query.getResultList();

		if (!msmNome.isEmpty()) {
			return null;
		}
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		return findById(produto.getId());
	}

	@Override
	public Object update(Object object) {
		Produtos produto = (Produtos) object;

		try {
			em.getTransaction().begin();
			em.merge(produto);
			em.getTransaction().commit();
			if( findById(produto.getId()) != null) {
				System.out.println("Produto Editado");
			}else {
				System.err.println("Erro ao editar");
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		em.getTransaction().begin();
		var produto = (Produtos) findById(id);
		em.remove(produto);
		em.getTransaction().commit();
		System.out.println("Produto com ID " + id + " deletado.");
	}

	@Override
	public Object findById(Object id) {
		try {
			Produtos produtoInbBd = em.find(Produtos.class, id);
			return produtoInbBd;
		} catch (Exception e) {

		}
		return null;
	}

	// listar produtos
	public List<Produtos> findAll() {
		return em.createQuery("SELECT p FROM Produtos p ", Produtos.class).getResultList();
	}
=======
public class ProdutoRepository implements BasicCrud{
    private EntityManager em;
    private ProdutoRepository produtoRepository;


    public ProdutoRepository(EntityManager em){
        this.em = em;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Object create(Object object) {
        Produtos produto = (Produtos) object;

        Query query = em.createQuery("SELECT p FROM  Produtos p where p.nome = :nome");
        query.setParameter("nome",produto.getNome());
        List<Produtos> msmNome = query.getResultList();

        if (!msmNome.isEmpty()){
            return null;
        }
        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();
        return findById(produto.getId());
    }

    @Override
    public Object update(Object object) {
        Produtos produto = (Produtos) object;

        em.getTransaction().begin();
        em.persist(produto);
        em.getTransaction().commit();

        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var produto =(Produtos)findById(id);
        em.remove(produto);
        em.getTransaction().commit();
        System.out.println("Produto com ID " + id + " deletado.");
    }

    @Override
    public Object findById(Object id) {
        try {
            Produtos produtoInbBd = em.find(Produtos.class,id);
            return  produtoInbBd;
        } catch (Exception e) {

        }
        return null;
    }
    //listar produtos
    public List<Produtos> findAll(){
        return em.createQuery("SELECT p FROM Produtos p ",Produtos.class).getResultList();
    }
>>>>>>> 9b4857a634fed663df8707b1988046257f763fcd

}
