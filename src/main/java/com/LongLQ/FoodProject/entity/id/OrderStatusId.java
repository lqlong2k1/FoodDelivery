package com.LongLQ.FoodProject.entity.id;
import java.io.Serializable;

public class OrderStatusId implements Serializable {
    private int id_order;
    private int id_status;

    public OrderStatusId(int id_order, int id_status){
        this.id_status = id_status;
        this.id_order = id_order;
    }

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
}
