package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.util.*;
import java.util.stream.Collectors;
import java.util.DoubleSummaryStatistics;

public class HardTasks {

    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
        HardTasks tasks = new HardTasks();
    }

    // Task 1
    public List<Product> getBooksWithPrice() {

        return products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .filter(p -> p.getPrice() > 100)
                .collect(Collectors.toList());
    }

    // Task 2
    public List<Order> getOrdersWithBabyProducts() {

        return orders.stream()
                .filter(order ->
                        order.getProducts().stream()
                                .anyMatch(p -> "Baby".equalsIgnoreCase(p.getCategory()))
                )
                .collect(Collectors.toList());
    }

    // Task 3
    public List<Product> applyDiscountToToys() {

        return products.stream()
                .filter(p -> "Toys".equalsIgnoreCase(p.getCategory()))
                .peek(p -> p.setPrice(p.getPrice() * 0.5))
                .collect(Collectors.toList());
    }

    // Task 4
    public Optional<Product> getCheapestBook() {

        return products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .min(Comparator.comparing(Product::getPrice));
    }

    // Task 5
    public List<Order> getRecentOrders() {

        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    // Task 6
    public DoubleSummaryStatistics getBooksStats() {

        return products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();
    }

    // Task 7
    public Map<Integer, Integer> getOrdersProductsMap() {

        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        o -> o.getProducts().size()
                ));
    }

    // Task 8
    public Map<String, List<Integer>> getProductsByCategory() {

        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }
}
