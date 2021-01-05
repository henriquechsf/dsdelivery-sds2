package me.henrique.dsdelivery.services;

import me.henrique.dsdelivery.dto.OrderDTO;
import me.henrique.dsdelivery.dto.ProductDTO;
import me.henrique.dsdelivery.entities.Order;
import me.henrique.dsdelivery.entities.OrderStatus;
import me.henrique.dsdelivery.entities.Product;
import me.henrique.dsdelivery.repositories.OrderRepository;
import me.henrique.dsdelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll() {
        List<Order> list = orderRepository.findOrdersWithProducts();
        return list.stream().map(x -> new OrderDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public OrderDTO insert(OrderDTO dto) {
        Order order = new Order(null,
                dto.getAddress(),
                dto.getLatitude(),
                dto.getLongitude(),
                Instant.now(),
                OrderStatus.PENDING);

        // adicionando os produtos relacionados no pedido
        for (ProductDTO p : dto.getProducts()) {
            Product product = productRepository.getOne(p.getId());
            order.getProducts().add(product);
        }

        order = orderRepository.save(order);

        return new OrderDTO(order);
    }
}
