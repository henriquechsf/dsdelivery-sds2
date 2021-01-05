package me.henrique.dsdelivery.services;

import me.henrique.dsdelivery.dto.ProductDTO;
import me.henrique.dsdelivery.entities.Product;
import me.henrique.dsdelivery.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAllByOrderByNameAsc();
        return list.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());
    }
}
