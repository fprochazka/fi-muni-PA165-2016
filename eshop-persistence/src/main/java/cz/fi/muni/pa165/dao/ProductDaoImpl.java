package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Product product)
	{
		em.persist(product);
	}

	@Override
	public void delete(Product product) throws IllegalArgumentException
	{
		em.remove(product);
	}

	@Override
	public List<Product> findAll()
	{
		TypedQuery<Product> query = em
			.createQuery("SELECT p FROM Product p", Product.class);
		return query.getResultList();
	}

	@Override
	public Product findById(Long id)
	{
		try {
			return em
				.createQuery("SELECT p FROM Product p WHERE p.id = :id", Product.class)
				.setParameter("id", id)
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
