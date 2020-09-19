package kris.novitskaya.spring1.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "products_customers")
@NamedQueries({
        @NamedQuery(name = "details", query = "SELECT pc FROM PurchaseDetails pc WHERE pc.product.id = :p_id and pc.customer.id = :c_id"),
        @NamedQuery(name = "test", query = "SELECT pc FROM PurchaseDetails pc WHERE pc.product = :id")
})
public class PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @Column(name = "actual_price")
    private int actualPrice;


    public Long getId() {
        return id;
    }

    public PurchaseDetails() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(int actualPrice) {
        this.actualPrice = actualPrice;
    }
}
