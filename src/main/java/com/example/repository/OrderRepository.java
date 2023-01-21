package com.example.repository;

import com.example.domain.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        //TODO 동적쿼리 변경 필요함. 실무에선 안쓰는게 좋으니 안만듬
        return em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :status" +
                        " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }

    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        //TODO 동적쿼리 변경 필요
        return em.createQuery("select o from Order o join o.member m" +
                        " where o.status = :status" +
                        " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000)
                .getResultList();
    }
}
