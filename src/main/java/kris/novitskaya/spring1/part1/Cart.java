package kris.novitskaya.spring1.part1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = "prototype")
public class Cart {
    private ProductRepository repository;
    private Map<Product, Integer> userCart;

    @Autowired
    public void setRepository(ProductRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    private void init() {
        userCart = new HashMap<>();
    }
    
    public void displayRepository(){
        repository.displayProducts();
    }
    
    public void addProduct(long id){
        Product p = repository.findProductById(id);
        if(p == null){
            System.out.println("Product with id = " + id + " not exist");
        }else{
            userCart.merge(p,1, Integer::sum);
        }

    }

    public void removeProduct(long id){
        Product p = repository.findProductById(id);
        if(p == null){
            System.out.println("Product with id = " + id + " not exist");
        }else {
            if(userCart.containsKey(p)){
                userCart.computeIfPresent(p, (key, value) -> value - 1);
                if(userCart.containsValue(0)){
                    userCart.remove(p);
                }
            }else{
                System.out.println("Product with id = " + " not found in the cart");
            }
        }
    }



    public void displayCart(){
        if(userCart.isEmpty()){
            System.out.println("Cart is empty");
        } else {
            System.out.println("Cart contains:");

            for (Map.Entry<Product, Integer> entry : userCart.entrySet()) {
                System.out.println(entry.getKey().getName() + ", количество: " + entry.getValue());
            }
            System.out.println("------------------------");
        }
    }
}
