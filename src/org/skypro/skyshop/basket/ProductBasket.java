package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

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
        int total = 0;
        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    public void printContents() {
        if (productsMap.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        int specialCount = 0;
        int index = 1;

        for (List<Product> productList : productsMap.values()) {
            for (Product product : productList) {
                System.out.println(index + ". " + product.toString());

                if (product.isSpecial()) {
                    specialCount++;
                }
                index++;
            }
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
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
        int totalCount = 0;
        for (List<Product> productList : productsMap.values()) {
            totalCount += productList.size();
        }
        return totalCount;
    }
}