package ru.itmo.kotiki.repository;

import ru.itmo.kotiki.Interfaces.CatDAO;
import ru.itmo.kotiki.models.Cat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.itmo.kotiki.util.HibernateSessionFactoryUtil;

import java.util.List;

public class CatDAOImpl implements CatDAO {
    @Override
    public Cat findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }

    @Override
    public void save(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Cat cat) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(cat);
        tx1.commit();
        session.close();
    }

    @Override
    public Cat findFriendById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        var cat = session.get(Cat.class, id);
        session.close();
        return cat;
    }

    @Override
    public List<Cat> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Cat> cats = (List<Cat>) session.createQuery("From Cat").list();
        session.close();
        return cats;
    }
}
