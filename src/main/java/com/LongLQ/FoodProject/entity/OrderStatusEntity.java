package com.LongLQ.FoodProject.entity;
import com.LongLQ.FoodProject.entity.id.OrderStatusId;

import javax.persistence.*;

@Entity(name = "order_status")
@IdClass(OrderStatusId.class)
public class OrderStatusEntity {
    @Id
    private int id_order;

    @Id
    private int id_status;

    @ManyToOne()
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private TOrderEntity tOrder;

    @ManyToOne()
    @JoinColumn(name = "id_status", insertable = false, updatable = false)
    private StatusEntity status;

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public TOrderEntity gettOrder() {
        return tOrder;
    }

    public void settOrder(TOrderEntity tOrder) {
        this.tOrder = tOrder;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
