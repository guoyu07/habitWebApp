package com.habit.custom.server.api.dao.impl;

import com.habit.custom.server.api.dao.HabitDao;
import com.habit.custom.server.api.model.Habit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * OLTS on 25.12.2016.
 */
@Repository("hibHabDao")
@EnableTransactionManagement
public class HibHabitDao implements HabitDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager", propagation = Propagation.REQUIRES_NEW, readOnly = true)
    //@Cacheable(value = "habitFindCache",key="#idhabit")
    public Habit get(int habitId) {
        return getCurrentSession().get(Habit.class, habitId);
    }

    @Override
    @Transactional(transactionManager = "hibernateTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public Habit create(Habit templateHabit) {
        return (Habit) getCurrentSession().save(templateHabit);
    }

    @Override
    public void delete(int habitId) {
        getCurrentSession().delete(habitId);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
