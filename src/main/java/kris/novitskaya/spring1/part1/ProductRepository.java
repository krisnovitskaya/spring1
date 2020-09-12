package kris.novitskaya.spring1.part1;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    private void init() {
        products = new ArrayList<>();
        products.add(new Product(1L, "Chocolate", 200));
        products.add(new Product(2L, "Milk", 100));
        products.add(new Product(3L, "Eggs", 120));
        products.add(new Product(4L, "Tea", 220));
        products.add(new Product(5L, "Coffee", 500));
    }

    public void displayProducts(){
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public List<Product> findAllProducts(){
        return Collections.unmodifiableList(products);
    }

    public Product findProductById(long id){
        for (Product product : products) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

}
