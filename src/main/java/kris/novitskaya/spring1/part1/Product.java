package kris.novitskaya.spring1.part1;

public class Product {
    private long id;
    private String name;
    private int price;

    public long getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public int getPrice() {
        return price;
    }



    public Product(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }



    @Override
    public String toString() {
        return String.format("name = %s; price = %d; id = %d",getName(),getPrice(),getId());
    }
}
