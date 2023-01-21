package com.example.domain;

import com.example.domain.item.Item;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "order_item")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성 금지
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private  int count;

    /* 연관관계 메소드 */
    public void setOrder(Order order) {
        this.order = order;
    }

    /* 생성 메서드 */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        item.removeStock(count);
        return OrderItem.builder().item(item).orderPrice(orderPrice).count(count).build();
    }

    /* 비지니스 로직 */
    public void cancel() {
        getItem().addStock(count);
    }

    /* 조회 로직 */
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }
}
