package br.com.alura.clientelo.util;

import br.com.alura.clientelo.reportgenerator.domain.report.impl.LoyalCustomers;
import br.com.alura.clientelo.reportgenerator.domain.report.impl.MostProfitableCustomers;
import br.com.alura.clientelo.reportgenerator.domain.report.impl.SalesPerCategory;
import br.com.alura.clientelo.reportgenerator.domain.report.impl.TopSellingProducts;
import br.com.alura.clientelo.store.domain.category.Category;
import br.com.alura.clientelo.store.infra.repository.category.CategoryRepository;
import br.com.alura.clientelo.store.infra.repository.category.vo.SalesPerCategoryVO;
import br.com.alura.clientelo.store.domain.customer.Address;
import br.com.alura.clientelo.store.domain.customer.Customer;
import br.com.alura.clientelo.store.infra.repository.customer.CustomerRepository;
import br.com.alura.clientelo.store.infra.repository.customer.vo.LoyalCustomersVO;
import br.com.alura.clientelo.store.infra.repository.customer.vo.MostProfitableCustomersVO;
import br.com.alura.clientelo.store.domain.order.Order;
import br.com.alura.clientelo.store.infra.repository.order.OrderRepository;
import br.com.alura.clientelo.store.domain.orderitem.OrderItem;
import br.com.alura.clientelo.store.domain.product.Product;
import br.com.alura.clientelo.store.infra.repository.product.ProductRepository;
import br.com.alura.clientelo.store.infra.repository.product.vo.TopSellingProductsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class StoreDatabaseUtil {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    public void generateBasicStore() {
        generateCategories();
        generateCustomers(10);
        generateProducts(20);
        generateOrders(20);
        List<Category> allCategories = categoryRepository.findAll();
        allCategories.forEach(System.out::println);
    }

    public void testReports() {
        System.out.println("#############");
        List<SalesPerCategoryVO> salesPerCategory = categoryRepository.getSalesPerCategory();
        salesPerCategory.forEach(System.out::println);
        System.out.println(new SalesPerCategory().processFromDatabase(salesPerCategory, null).generateText());
        System.out.println("#############");

        System.out.println("#############");
        List<MostProfitableCustomersVO> mostProfitableCustomers = customerRepository.getMostProfitableCustomers();
        mostProfitableCustomers.forEach(System.out::println);
        System.out.println(new MostProfitableCustomers().processFromDatabase(mostProfitableCustomers, null).generateText());
        System.out.println("#############");

        System.out.println("#############");
        List<LoyalCustomersVO> loyalCustomers = customerRepository.getLoyalCustomers();
        loyalCustomers.forEach(System.out::println);
        System.out.println(new LoyalCustomers().processFromDatabase(loyalCustomers, null).generateText());
        System.out.println("#############");

        System.out.println("#############");
        List<TopSellingProductsVO> topSellingProducts = productRepository.getTopSellingProducts();
        topSellingProducts.forEach(System.out::println);
        System.out.println(new TopSellingProducts().processFromDatabase(topSellingProducts, null).generateText());
        System.out.println("#############");
    }

    private void generateCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        Category category1 = new Category("COMPUTERS");
        Category category2 = new Category("TOYS");
        Category category3 = new Category("CLOTHING");
        Category category4 = new Category("GAMES");
        Category category5 = new Category("HOUSEWARE");
        Category category6 = new Category("FURNITURE");
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);

        List<String> categoryNames = allCategories.stream().map(Category::getName).toList();

        for (String name : categoryNames) {
            categories.removeIf(category -> category.getName().equals(name));
        }
        categoryRepository.saveAll(categories);
    }

    private void generateProducts(int number) {
        List<Category> categories = (List<Category>) categoryRepository.findAll();
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
        productRepository.saveAll(products);
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
                    String.format("%s-%s", (i + 50), (i + 60)),
                    address,
                    "email",
                    "password"
            );

            customers.add(customer);
        }
        customerRepository.saveAll(customers);
    }

    private void generateOrders(int number) {
        List<Customer> customers = customerRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Order order = new Order(
                    LocalDate.now(),
                    customers.get(ThreadLocalRandom.current().nextInt(customers.size()))
            );

            List<OrderItem> items = new ArrayList<>();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(5); j++) {
                OrderItem orderItem = new OrderItem(
                        ThreadLocalRandom.current().nextInt(1, 5),
                        order,
                        products.get(ThreadLocalRandom.current().nextInt(products.size()))
                );

                items.add(orderItem);
            }

            order.addItems(items);
            orders.add(order);
        }

        orderRepository.saveAll(orders);
    }
}
