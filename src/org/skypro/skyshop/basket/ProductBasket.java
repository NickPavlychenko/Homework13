package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] products;
    private int productCount;

    public ProductBasket() {
        this.products = new Product[10];
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

        int specialCount = 0;

        for (int i = 0; i < productCount; i++) {
            Product product = products[i];
            System.out.println(product.toString());

            if (product.isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + specialCount);
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
