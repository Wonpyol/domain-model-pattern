package com.example.service;

import com.example.domain.Address;
import com.example.domain.Member;
import com.example.domain.Order;
import com.example.domain.OrderStatus;
import com.example.domain.item.Book;
import com.example.repository.OrderRepository;
import com.example.repository.OrderSearch;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @Rollback(value = false)
    void 상품주문() {
        //given
        Member member = Member.builder().name("이원표").address(new Address("서울", "2길", "123-123")).build();
        em.persist(member);
        Book book = Book.builder().author("원푤").name("DDDD").price(1000).stockQuantity(10).build();
        em.persist(book);
        //when
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "주문상태확인");
        assertEquals(1, getOrder.getOrderItems().size(), "주문수량확인");
        assertEquals(getOrder.getTotalPrice(), 1000*orderCount, "주문가격확인");
        assertEquals(book.getStockQuantity(), 8,"재고차감확인");
    }
    @Test
    void 주문전체조회() {
        //given
        OrderSearch orderSearch = OrderSearch.builder().orderStatus("100").memberName("이원표").build();

        //when
        List<Order> orders = orderService.findOrders(orderSearch);
        //then
        assertEquals(1, orders.size(), "전체주문리스트확인");
    }

    void 주문취소() {
    }
}