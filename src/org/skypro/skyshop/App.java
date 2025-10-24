package org.skypro.skyshop;

import content.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import search.SearchEngine;
import search.Searchable;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        SimpleProduct laptop = new SimpleProduct("Игровой ноутбук", 50000);
        SimpleProduct mouse = new SimpleProduct("Игровая мышь", 5000);
        DiscountedProduct keyboard = new DiscountedProduct("Механическая клавиатура", 7000, 30);
        FixPriceProduct port = new FixPriceProduct("Display порт");

        Article article1 = new Article("Обзор ноутбуков", "Лучшие игровые ноутбуки 2025 года");
        Article article2 = new Article("Выбор мыши", "Как выбрать игровую компьютерную мышь");
        Article article3 = new Article("Какой есть тип клавиатур", "Мембранная и механическая клавиатура");

        SearchEngine searchEngine = new SearchEngine(10);

        searchEngine.add(laptop);
        searchEngine.add(mouse);
        searchEngine.add(keyboard);
        searchEngine.add(port);
        searchEngine.add(article1);
        searchEngine.add(article2);
        searchEngine.add(article3);

        System.out.println();
        System.out.println(" Демонстрация поиска ");
        System.out.println();

        testSearch(searchEngine, "ноутбук");
        System.out.println();
        testSearch(searchEngine, "мышь");
        System.out.println();
        testSearch(searchEngine, "клавиатура");
        System.out.println();
        testSearch(searchEngine, "игр");
        System.out.println();
        testSearch(searchEngine, "2025");
        System.out.println();

        System.out.println(" Демонстрация корзины ");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.printContents();
    }

    public static void testSearch(SearchEngine searchEngine, String query) {
        System.out.println("Поиск: " + query);
        Searchable[] results = searchEngine.search(query);

        System.out.println("Результаты: " + Arrays.toString(results));

        System.out.println("Детали результатов: ");
        for (Searchable result : results) {
            if (result != null) {
                System.out.println(" - " + result.getStringRepresentation());
            }
        }
    }
}
