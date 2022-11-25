package com.LongLQ.FoodProject.entity;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "t_order")
public class TOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "estimate_ship")
    private String estimateShip;

    @Column(name = "deliver_address")
    private String deliverAddress;

    @ManyToOne()
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @OneToMany(mappedBy = "tOrder")
    private Set<OrderStatusEntity> orderStatus;

    @OneToMany(mappedBy = "tOrder")
    private Set<FoodOrderEntity> foodOrders;

    public Set<FoodOrderEntity> getFoodOrders() {
        return foodOrders;
    }

    public void setFoodOrders(Set<FoodOrderEntity> foodOrders) {
        this.foodOrders = foodOrders;
    }

    public Set<OrderStatusEntity> getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Set<OrderStatusEntity> orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstimateShip() {
        return estimateShip;
    }

    public void setEstimateShip(String estimateShip) {
        this.estimateShip = estimateShip;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
