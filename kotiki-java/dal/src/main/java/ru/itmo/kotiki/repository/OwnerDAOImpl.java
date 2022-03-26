package ru.itmo.kotiki.repository;

import ru.itmo.kotiki.Interfaces.OwnerDAO;
import ru.itmo.kotiki.models.Cat;
import ru.itmo.kotiki.models.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.kotiki.util.HibernateSessionFactoryUtil;

import java.util.List;

public class OwnerDAOImpl implements OwnerDAO {
    @Override
    public Owner findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var owner = session.get(Owner.class, id);
        session.close();
        return owner;
    }

    @Override
    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }

    @Override
    public Cat findCatById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }

    @Override
    public List<Owner> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List <Owner> owners = (List<Owner>) session.createQuery("From Owner").list();
        session.close();
        return owners;
    }
}
