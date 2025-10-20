package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {

        SimpleProduct laptop = new SimpleProduct("Ноутбук", 50000);
        SimpleProduct mouse = new SimpleProduct("Компьютерная мышь", 2000);

        DiscountedProduct keyboard = new DiscountedProduct("Клавиатура", 4000,20);
        DiscountedProduct monitor = new DiscountedProduct("Монитор", 30000,15);

        FixPriceProduct cable = new FixPriceProduct("Кабель HDMI");
        FixPriceProduct port = new FixPriceProduct("Display порт");

        ProductBasket basket = new ProductBasket();

        System.out.println();
        System.out.println(" -Демонстрация обновленной корзины- ");
        System.out.println();

        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.addProduct(monitor);
        basket.addProduct(cable);
        basket.addProduct(port);

        System.out.println("Содержиоме корзины:");
        basket.printContents();

        System.out.println();
        System.out.println(" -Дополнительны проверки- ");
        System.out.println();
        System.out.println("Общая стоимсоть: " + basket.getTotalPrice() + " руб.");
        System.out.println("Количество товаров: " + basket.getProductsCount());
        System.out.println("Есть ли 'Кабель HDMI' в корзине: " + basket.containsProduct("Кабель HDMI"));
        System.out.println("Есть ли 'Планшет' в корзине: " + basket.containsProduct("Планшет"));

        System.out.println();
        System.out.println(" -Очистка корзины- ");
        System.out.println();
        basket.clearBasket();
        basket.printContents();
    }
}
