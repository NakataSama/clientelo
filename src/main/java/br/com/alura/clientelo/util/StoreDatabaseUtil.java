package br.com.alura.clientelo.util;

import br.com.alura.clientelo.store.category.Category;
import br.com.alura.clientelo.store.category.CategoryDAO;
import br.com.alura.clientelo.store.customer.Address;
import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.customer.CustomerDAO;
import br.com.alura.clientelo.store.product.Product;
import br.com.alura.clientelo.store.product.ProductDAO;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class StoreDatabaseUtil {

    private EntityManager em = JPAUtil.getEntityManager();

    private CategoryDAO categoryDAO = new CategoryDAO(em);
    private ProductDAO productDAO = new ProductDAO(em);
    private CustomerDAO customerDAO = new CustomerDAO(em);

    private void generateCategories() {
        Category category1 = new Category("COMPUTERS");
        Category category2 = new Category("TOYS");
        Category category3 = new Category("CLOTHING");
        Category category4 = new Category("GAMES");
        Category category5 = new Category("HOUSEWARE");
        Category category6 = new Category("FURNITURE");

        List<Category> categories = List.of(category1, category2, category3, category4, category5, category6);
        categories.forEach(categoryDAO::create);
    }

    private void generateProducts(int number) {
        List<Category> categories = categoryDAO.getAll();
        List<Product> products = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            products.add(new Product(
                    "Product " + (i+1),
                    BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(0.0, 5000.0)),
                    "Description of Product " + (i+1),
                    ThreadLocalRandom.current().nextInt(1, 1001),
                    categories.get(ThreadLocalRandom.current().nextInt(categories.size()))
            ));
        }

        products.forEach(productDAO::create);
    }

    private void generateCustomers(int number) {
        List<Customer> customers = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Address address = new Address(
                    "Street " + (i+1),
                    String.valueOf(i),
                    "Apt. " + (i+1),
                    "District",
                    "City",
                    "State"
            );

            Customer customer = new Customer(
                    "Customer " + (i + 1),
                    String.format("%s.%s", (i + 10), (i + 20)),
                    String.format("%s.%s", (i + 50), (i + 60)),
                    address
            );

            customers.add(customer);
        }

        customers.forEach(customerDAO::create);
    }
}
