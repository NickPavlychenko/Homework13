package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ProductBasket {
    private Map<String, List<Product>> productsMap;

    public ProductBasket() {
        this.productsMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        String productName = product.getName();
        productsMap.computeIfAbsent(productName, k -> new ArrayList<>()).add(product);
    }

    public int getTotalPrice() {
        return productsMap.values().stream()
                .flatMap(Collection::stream)
                .mapToInt(Product::getPrice)
                .sum();
            }

    public void printContents() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        AtomicInteger index = new AtomicInteger(1);
        productsMap.values().stream()
                .flatMap(Collection::stream)
                .forEach(product -> {
                    System.out.println(index.getAndIncrement() + ". " + product.toString());
                });

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    private long getSpecialCount() {
        return productsMap.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public boolean containsProduct(String productName) {
        return productsMap.containsKey(productName);
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removedProducts = productsMap.remove(name);
        return removedProducts != null ? removedProducts : new ArrayList<>();
    }

    public void clearBasket() {
        productsMap.clear();
    }

    public int getAvailableSpace() {
        return Integer.MAX_VALUE;
    }

    public boolean isBasketFull() {
        return false;
    }

    public int getProductCount() {
        return productsMap.values().stream()
                .mapToInt(List::size)
                .sum();
    }
}