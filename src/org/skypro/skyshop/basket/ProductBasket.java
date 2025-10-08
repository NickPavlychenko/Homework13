package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products;
    private int productCount;

    public ProductBasket() {
        this.products = new Product[5];
        this.productCount = 0;
    }

    public void addProduct(Product product) {
        if (productCount >= products.length) {
            throw new IllegalStateException("Невозможно добавить продукт: корзина переполнена");
        }
        products[productCount] = product;
        productCount++;
    }

    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < productCount; i++) {
            total += products[i].getPrice();
        }
        return total;
    }

    public void printContents() {
        if (productCount == 0) {
            System.out.println(" Корзина путсая ");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            System.out.println(products[i].getName() + ": " + products[i].getPrice());
        }
        System.out.println("Итого: " + getTotalPrice());
    }

    public boolean containsProduct(String productName) {
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
        productCount = 0;
    }

    public int getAvailableSpace() {
        return products.length - productCount;
    }

    public boolean isBasketFull() {
        return productCount >= products.length;
    }

    public int getProductsCount() {
        return productCount;
    }
}
