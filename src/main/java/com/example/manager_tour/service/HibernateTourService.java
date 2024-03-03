package com.example.manager_tour.service;

import com.example.manager_tour.model.Tour;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class HibernateTourService implements IHibernateTourService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tour> findAll() {
        String queryStr = "select tour from Tour As tour";
        TypedQuery<Tour> query = entityManager.createQuery(queryStr, Tour.class);
        return query.getResultList();
    }

    @Override
    public void save(Tour tour) {
        Transaction transaction = null;
        Tour origin;
        if (tour.getId() == 0) {
            origin = new Tour();
        } else {
            origin = findById(tour.getId());
        }
        try
                (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setCode(tour.getCode());
            origin.setDestination(tour.getDestination());
            origin.setPrice(tour.getPrice());
            origin.setImg(tour.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

    public Tour findById(int id) {
        String queryStr = "select tour from Tour As tour where tour.id = :id";
        TypedQuery<Tour> query = entityManager.createQuery(queryStr, Tour.class);
        query.setParameter("id", id);
        return query.getSingleResult();

    }

    @Override
    public void remove(int id) {
        Tour tour = findById(id);
        if (tour != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(tour);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null){
                    transaction.rollback();
                }
            }


        }
    }

    @Override
    public void edit(Tour tour) {
        Transaction transaction = null;
        Tour origin;
        if (tour.getId() == 0) {
            origin = new Tour();
        } else {
            origin = findById(tour.getId());
        }
        try
                (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setCode(tour.getCode());
            origin.setDestination(tour.getDestination());
            origin.setPrice(tour.getPrice());
            origin.setImg(tour.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }

    }

}
