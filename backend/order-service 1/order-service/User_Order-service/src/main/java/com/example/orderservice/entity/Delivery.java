package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "delivery")
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partnerName;
    private String partnerPhone;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
