package com.LongLQ.FoodProject.entity;
import javax.persistence.*;
import java.util.Set;

@Entity(name = "status")
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status")
    private Set<OrderStatusEntity> otherStatus;

    public Set<OrderStatusEntity> getOtherStatus() {
        return otherStatus;
    }

    public void setOtherStatus(Set<OrderStatusEntity> otherStatus) {
        this.otherStatus = otherStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
