package com.codegym.repository.category;

import com.codegym.model.category.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CategoryRepository implements ICategoryRepository{
@PersistenceContext
private EntityManager em;
    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = em.createQuery("select c from Category as c ",Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(Long id) {
        TypedQuery<Category> query = em.createQuery("select c from Category as c where c.id =:id",Category.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    public void save(Category category) {
        if(category.getId()!=null){
            em.merge(category);
        }else {
            em.persist(category);
        }
    }

    @Override
    public void delete(Category category) {
        em.remove(category);
    }
}
