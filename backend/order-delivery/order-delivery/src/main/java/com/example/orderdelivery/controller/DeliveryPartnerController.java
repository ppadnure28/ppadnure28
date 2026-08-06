package com.example.orderdelivery.controller;

import com.example.orderdelivery.entity.DeliveryPartner;
import com.example.orderdelivery.repository.DeliveryPartnerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordr/delivery-partners")
public class DeliveryPartnerController {

    private final DeliveryPartnerRepository repository;

    public DeliveryPartnerController(DeliveryPartnerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DeliveryPartner> getAllPartners() {
        return repository.findAll();
    }

    @PostMapping
    public DeliveryPartner createPartner(@RequestBody DeliveryPartner partner) {
        return repository.save(partner);
    }
}
