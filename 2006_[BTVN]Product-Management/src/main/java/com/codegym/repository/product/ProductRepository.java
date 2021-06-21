package com.codegym.repository.product;

import com.codegym.model.category.Category;
import com.codegym.model.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class ProductRepository implements IProductRepository{
    @PersistenceContext
    private EntityManager em;
    public List<Product> findAll() {
        TypedQuery<Product> query = em.createQuery("select p from Product as p ",Product.class);
        return query.getResultList();
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = em.createQuery("select p from Product as p where p.id=:id",Product.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Product product) {
        if(product.getId()!=null){
            em.merge(product);
        }else {
            em.persist(product);
        }
    }

    @Override
    public void delete(Product product) {
        em.remove(product);
    }
}
