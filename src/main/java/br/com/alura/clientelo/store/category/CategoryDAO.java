package br.com.alura.clientelo.store.category;

import br.com.alura.clientelo.store.DAO;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class CategoryDAO implements DAO<Category> {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Category category) {
        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<Category> getById(long id) {
        em.getTransaction().begin();
        Category response = em.find(Category.class, id);
        em.getTransaction().commit();
        em.close();

        return Optional.ofNullable(response);
    }

    @Override
    public void update(Category category) {
        em.getTransaction().begin();
        em.merge(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Category category) {
        em.getTransaction().begin();
        em.remove(category);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Category> getAll() {
        em.getTransaction().begin();
        List<Category> response = em.createQuery("SELECT c FROM category c", Category.class).getResultList();
        em.getTransaction().commit();
        em.close();

        return response;
    }
}
