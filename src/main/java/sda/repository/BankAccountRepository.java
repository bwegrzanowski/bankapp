package sda.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.HibernateUtil;
import sda.model.BankAccount;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BankAccountRepository {
    public static boolean saveAccount(BankAccount account) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.save(account);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean saveOrUpdateAccount(BankAccount account) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(account);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null && session.isOpen() && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static Optional<BankAccount> findAccount(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            BankAccount account = session.find(BankAccount.class, id);
            return Optional.ofNullable(account);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static List<BankAccount> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT b FROM BankAccount b ";
            Query query = session.createQuery(hql);
            List resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public static boolean isAccountNumberIsUnique(String accountNumber) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "select count (*) FROM BankAccount b where b.accountNumber = :accountNumber ";
            Query query = session.createQuery(hql);
            query.setParameter("accountNumber", accountNumber);
            Long count = (Long) query.uniqueResult();
            if (count.equals(0L)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
