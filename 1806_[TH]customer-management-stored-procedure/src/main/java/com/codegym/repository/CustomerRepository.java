package com.codegym.repository;

import com.codegym.model.Customer;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        TypedQuery<Customer> query = em.createQuery("select c from Customer as c where c.id =:id",Customer.class);
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
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public boolean insertWithStoredProcedure(Customer customer) {
        String sql = "CALL Insert_Customer(:firstName, :lastName)";
        Query query = em.createNativeQuery(sql);
        query.setParameter("firstName", customer.getFirstName());
        query.setParameter("lastName", customer.getLastName());
        return query.executeUpdate() == 0;
    }
}
