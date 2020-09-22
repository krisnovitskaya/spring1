package kris.novitskaya.spring1.hibernate;


//        1. В базе данных необходимо реализовать возможность хранить информацию о покупателях (id,
//        имя) и товарах (id, название, стоимость). У каждого покупателя свой набор купленных
//        товаров.
//        Задача: написать тестовое консольное приложение, которое позволит посмотреть, какие
//        товары покупал клиент, какие клиенты купили определенный товар, и предоставит
//        возможность удалять из базы товары/покупателей.
//        2. ** Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент
//        покупки клиентом.
public class MainHiberApp {
    public static void main(String[] args) {
        Service service = new Service();

        try {

            //service.deleteCustomer(3l);
            System.out.println(service.getCustomerProductsList(1L));
            service.detailing(5L, 5L);
            //System.out.println(service.getProductCustomersList(2L));
            //service.deleteProduct(10L);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } finally {
            service.close();
        }
    }
}
