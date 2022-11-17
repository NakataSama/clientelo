package br.com.alura.clientelo.util;

import br.com.alura.clientelo.report.impl.LoyalCustomers;
import br.com.alura.clientelo.report.impl.MostProfitableCustomers;
import br.com.alura.clientelo.report.impl.SalesPerCategory;
import br.com.alura.clientelo.report.impl.TopSellingProducts;
import br.com.alura.clientelo.store.category.Category;
import br.com.alura.clientelo.store.category.CategoryRepository;
import br.com.alura.clientelo.store.category.vo.SalesPerCategoryVO;
import br.com.alura.clientelo.store.customer.Address;
import br.com.alura.clientelo.store.customer.Customer;
import br.com.alura.clientelo.store.customer.CustomerRepository;
import br.com.alura.clientelo.store.customer.vo.LoyalCustomersVO;
import br.com.alura.clientelo.store.customer.vo.MostProfitableCustomersVO;
import br.com.alura.clientelo.store.order.DiscountType;
import br.com.alura.clientelo.store.order.Order;
import br.com.alura.clientelo.store.order.OrderRepository;
import br.com.alura.clientelo.store.orderitem.OrderItem;
import br.com.alura.clientelo.store.orderitem.OrderItemDiscountType;
import br.com.alura.clientelo.store.product.Product;
import br.com.alura.clientelo.store.product.ProductRepository;
import br.com.alura.clientelo.store.product.vo.TopSellingProductsVO;
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
        List<Category> allCategories = (List<Category>) categoryRepository.findAll();
        Category category1 = new Category("COMPUTERS");
        Category category2 = new Category("TOYS");
        Category category3 = new Category("CLOTHING");
        Category category4 = new Category("GAMES");
        Category category5 = new Category("HOUSEWARE");
        Category category6 = new Category("FURNITURE");

        List<Category> categories = List.of(category1, category2, category3, category4, category5, category6);
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
                    address
            );

            customers.add(customer);
        }
        customerRepository.saveAll(customers);
    }

    private void generateOrders(int number) {
        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        List<Product> products = (List<Product>) productRepository.findAll();
        List<Order> orders = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Order order = new Order(
                    LocalDate.now(),
                    customers.get(ThreadLocalRandom.current().nextInt(customers.size())),
                    BigDecimal.ZERO,
                    DiscountType.NONE
            );

            List<OrderItem> items = new ArrayList<>();
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(5); j++) {
                OrderItem orderItem = new OrderItem(
                        ThreadLocalRandom.current().nextInt(1, 5),
                        order,
                        products.get(ThreadLocalRandom.current().nextInt(products.size())),
                        BigDecimal.ZERO,
                        OrderItemDiscountType.NONE
                );

                items.add(orderItem);
            }

            order.setItems(items);
            orders.add(order);
        }

        orderRepository.saveAll(orders);
    }
}
