package com.codegym.repository.customer;

import com.codegym.model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class CustomerRepository implements ICustomerRepository{
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer> query = em.createQuery("select c from Customer as c",Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer> query = em.createQuery("select c from Customer as c where c.id = :id",Customer.class);
        query.setParameter("id",id);
        Customer customer = query.getSingleResult();
        return customer;
    }

    @Override
    public void save(Customer customer) {
        if(customer.getId()!=null){
            em.merge(customer);
        }else {
            em.persist(customer);
        }
    }

    @Override
    public void delete(Long id) {
        Customer customer = findById(id);
        if (customer.getId()!=null){
            em.remove(customer);
        }
    }
}
