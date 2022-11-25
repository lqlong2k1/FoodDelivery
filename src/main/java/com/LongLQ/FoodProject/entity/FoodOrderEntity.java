package com.LongLQ.FoodProject.entity;
import com.LongLQ.FoodProject.entity.id.FoodOrderId;

import javax.persistence.*;

@Entity(name = "food_order")
@IdClass(FoodOrderId.class)
public class FoodOrderEntity {
    @Id
    private int id_order;

    @Id
    private int id_food;

    @Column(name = "price")
    private float price;

    @Column(name = "quality")
    private int quality;

    @ManyToOne()
    @JoinColumn(name = "id_order", insertable = false, updatable = false)
    private TOrderEntity tOrder;

    @ManyToOne()
    @JoinColumn(name = "id_food", insertable = false, updatable = false)
    private FoodEntity food;

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_food() {
        return id_food;
    }

    public void setId_food(int id_food) {
        this.id_food = id_food;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public TOrderEntity gettOrder() {
        return tOrder;
    }

    public void settOrder(TOrderEntity tOrder) {
        this.tOrder = tOrder;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }
}
