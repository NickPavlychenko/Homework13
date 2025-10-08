package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {

        Product laptop = new Product("Ноутбук", 50000);
        Product mouse = new Product("Компьютерная мышь", 2000);
        Product keyboard = new Product("Клавиатура", 4000);
        Product monitor = new Product("Монитор", 30000);
        Product headphones = new Product("Наушники", 5000);
        Product webcam = new Product("Веб-камера", 6000);

        ProductBasket basket = new ProductBasket();

        System.out.println();
        System.out.println("Работа корзины ");
        System.out.println();

        System.out.println("Добавление товара: ");
        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);
        basket.addProduct(headphones);
        System.out.println("Добавлено товаров: " + basket.getProductsCount() + " Доступно мест:" + basket.getAvailableSpace());
        System.out.println("Корзина зполнена: " + basket.isBasketFull());

        System.out.println();
        System.out.println("Попытка добавить товар в запролненную корзину:");
        try {
            basket.addProduct(webcam);
            System.out.println("Товар добавлен");
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
            System.out.println("Текущее количество товаров: " + basket.getProductsCount());
        }

        System.out.println();
        System.out.println("Содержимое корзины:");
        System.out.println();
        basket.printContents();
        System.out.println();

        System.out.println("Общая стоимость: " + basket.getTotalPrice() + " рублей");
        System.out.println();

        System.out.println("Поиск существующего товара: ");
        System.out.println("Веб-камера в корзине: " + basket.containsProduct("Веб-камера"));

        System.out.println("Поиск существующего товара: ");
        System.out.println("Компьютерная мышь в корзине: " + basket.containsProduct("Компьютерная мышь"));
        System.out.println();

        System.out.println("Очитска корзины: ");
        basket.clearBasket();

        System.out.println("Содержимое пустой корзины: ");
        basket.printContents();
        System.out.println();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice() + " рублей");
        System.out.println();

        System.out.println("Поиск в пустой корзине: ");
        System.out.println("Ноутбук в корзине: " + basket.containsProduct("Ноутбук"));

        System.out.println("Добавление товара после очистки: ");
        basket.addProduct(webcam);
        System.out.println("Веб-камера успешно добавлена после очистки!");
        basket.printContents();
    }
}
