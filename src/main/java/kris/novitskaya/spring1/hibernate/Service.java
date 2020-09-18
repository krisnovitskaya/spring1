package kris.novitskaya.spring1.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private SessionFactory factory = new Configuration()
            .configure("configs/hibernate.cfg.xml")
            .buildSessionFactory();

    private Session session;


    public void deleteCustomer(long customerID) throws ResourceNotFoundException {
        session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            session.delete(session.get(Customer.class, customerID));
            System.out.println("Customer with id = " + customerID + " deleted.");
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException();
        } finally {
            session.getTransaction().commit();
        }
    }

    public void deleteProduct(long productID) throws ResourceNotFoundException {
        session = factory.getCurrentSession();
        session.beginTransaction();
        try {
            session.delete(session.get(Product.class, productID));
            System.out.println("Product with id = " + productID + " deleted.");
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException();
        } finally {
            session.getTransaction().commit();
        }
    }

    public List<Product> getCustomerProductsList(long customerID) throws ResourceNotFoundException {
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Product> list = new ArrayList<>();
        try {
            Customer customer = session.get(Customer.class, customerID);

            //Какие существуют адекватные варианты борьбы с LazyInitializationException, сохраняя lazyinitialization?
            //если принудительно не "выкачать" (sout или как ниже) вылезает ошибка.
            list.addAll(customer.getProducts());
            return list;
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException();
        } finally {
            session.getTransaction().commit();
        }
    }


    public List<Customer> getProductCustomersList(long productID) throws ResourceNotFoundException {
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Customer> list = new ArrayList<>();
        try {
            Product product = session.get(Product.class, productID);
            list.addAll(product.getCustomers());
            return list;
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException();
        } finally {
            session.getTransaction().commit();
        }
    }

    public void detailing(long customerID, long productID) {
        session = factory.getCurrentSession();
        session.beginTransaction();

//        PurchaseDetails purchaseDetails = (PurchaseDetails) session.getNamedQuery("details")
//                    .setParameter("p_id", productID)
//                    .setParameter("c_id", customerID)
//                    .getSingleResult();

        //namedQuery выдает ошибку. лог в error.txt

        String query = String.format("SELECT pc FROM PurchaseDetails pc WHERE pc.product = %d and pc.customer = %d", productID, customerID);
        PurchaseDetails purchaseDetails = session.createQuery(query, PurchaseDetails.class).getSingleResult();

        System.out.println(purchaseDetails.getActualPrice() + " actual price");
        System.out.println(purchaseDetails.getProduct().getPrice() + " current price");
        session.getTransaction().commit();
    }


    public void close() {
        factory.close();
        if (session != null) {
            session.close();
        }
    }
}
