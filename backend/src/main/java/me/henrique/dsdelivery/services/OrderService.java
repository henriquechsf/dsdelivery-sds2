package me.henrique.dsdelivery.services;

import me.henrique.dsdelivery.dto.OrderDTO;
import me.henrique.dsdelivery.dto.ProductDTO;
import me.henrique.dsdelivery.entities.Order;
import me.henrique.dsdelivery.entities.Product;
import me.henrique.dsdelivery.repositories.OrderRepository;
import me.henrique.dsdelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = repository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }
}
