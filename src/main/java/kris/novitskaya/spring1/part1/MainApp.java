package kris.novitskaya.spring1.part1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

/*1. Есть класс Product (id, название, цена). Товары хранятся в бине ProductRepository, в виде
        List<Product>, при старте в него нужно добавить 5 любых товаров. ProductRepository
        позволяет получить весь список или один товар по id. Создаем бин Cart, в который можно
        добавлять и удалять товары по id. Написать консольное приложение, позволяющее управлять
        корзиной. При каждом запросе корзины из контекста, должна создаваться новая корзина.
*/
public class MainApp {
    public static final String COMMANDS = "show commands; show products; show cart; new cart; add id; remove id; exit.";
    public static Cart cart;
    public static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    public static String line;


    public static void main(String[] args) {
        cart = newCart();
        System.out.println("Commands: " + COMMANDS);
        Scanner scanner = new Scanner(System.in);

        while(!(line = scanner.nextLine()).equals("exit")){
            switch (line){
                case "show commands":
                    System.out.println("Commands: " + COMMANDS);
                    break;
                case "show products":
                    cart.displayRepository();
                    break;
                case "show cart":
                    cart.displayCart();
                    break;
                case "new cart":
                    cart = newCart();
                    break;
                default:
                    checkCommand(line);
                    break;
            }

        }
        context.close();
    }

    private static void checkCommand(String line) {
        if(line.startsWith("add ")){
            String[] comm = line.split(" ");
            try{
                long id = Long.parseLong(comm[1]);
                cart.addProduct(id);
            }catch (NumberFormatException e){
                System.out.println("trouble with parse id. make it long format");
            }
        } else if(line.startsWith("remove ")){
            String[] comm = line.split(" ");
            try{
                long id = Long.parseLong(comm[1]);
                cart.removeProduct(id);
            }catch (NumberFormatException e){
                System.out.println("trouble with parse id. make it long format");
            }
        }else {
            System.out.println("unknown command");
        }
    }

    public static Cart newCart(){
        System.out.println("new cart created");
        return context.getBean("cart", Cart.class);
    }

}
