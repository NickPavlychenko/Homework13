package org.skypro.skyshop;

import content.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exception.BestResultNotFoundException;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import search.SearchEngine;
import search.Searchable;
import java.util.List;
import java.util.Arrays;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        System.out.println(" - Демонстрация обновленного функционала -");
        testUpdatedFunctionality();
    }

    private static void testUpdatedFunctionality() {

        SimpleProduct laptop1 = new SimpleProduct("Игровой ноутбук", 50000);
        SimpleProduct laptop2 = new SimpleProduct("Игровой ноутбук", 45000);
        SimpleProduct mouse = new SimpleProduct("Игровая мышь", 5000);
        DiscountedProduct keyboard = new DiscountedProduct("Механическая клавиатура", 7000, 30);
        Article article1 = new Article("Обзор игровых ноутбуков", "Лучшие игровые ноутбуки 2025 года");
        Article article2 = new Article("Выбор игровой мыши", "Как выбрать игровую мышь для компьютерных игр");


        System.out.println("\n - Демонстрацияя корзины со списком -");
        ProductBasket basket = new ProductBasket();


        basket.addProduct(laptop1);
        basket.addProduct(laptop2);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);

        System.out.println("Корзина после добавления товаров:");
        basket.printContents();


        System.out.println("\n - Демонстрация удаление продукта по имени -");


        System.out.println("1) Удаляем 'Игровой ноутбук':");
        List<Product> removedProducts = basket.removeProductsByName("Игровой ноутбук");


        System.out.println("2) Удаленные продукты:");
        if (removedProducts.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product product : removedProducts) {
                System.out.println("   - " + product.getName() + ": " + product.getPrice() + " руб.");
            }
        }


        System.out.println("3) Корзина после удаления:");
        basket.printContents();


        System.out.println("4) Удаляем несуществующий продукт 'Планшет':");
        List<Product> removedNonExistent = basket.removeProductsByName("Планшет");


        System.out.println("5) Результат удаления несуществующего продукта:");
        if (removedNonExistent.isEmpty()) {
            System.out.println("   Список пуст");
        } else {
            for (Product product : removedNonExistent) {
                System.out.println("   - " + product.getName());
            }
        }


        System.out.println("6) Финальное содержимое корзины:");
        basket.printContents();


        System.out.println("\n - Демонстрация обновленного поиска -");
        SearchEngine searchEngine = new SearchEngine();


        searchEngine.add(laptop1);
        searchEngine.add(mouse);
        searchEngine.add(keyboard);
        searchEngine.add(article1);
        searchEngine.add(article2);


        testSearch(searchEngine, "игр");
        testSearch(searchEngine, "ноутбук");
        testSearch(searchEngine, "мышь");


        System.out.println("\n - Поиск наиболее подходящего -");
        testBestMatch(searchEngine, "игр");
    }

    private static void testSearch(SearchEngine searchEngine, String query) {
        System.out.println("\n - Поиск: '" + query + "' - ");
        Map<String, Searchable> results = searchEngine.search(query);;

        System.out.println("Найдено результатов: " + results.size());

        int index = 1;
        for (Map.Entry<String, Searchable> entry : results.entrySet()) {
            System.out.println(index + ". " + entry.getValue().getStringRepresentation());
            index++;
        }
    }

    private static void testBestMatch(SearchEngine searchEngine, String query) {
        try {
            Searchable bestMatch = searchEngine.findBestMatch(query);
            System.out.println("Наиболее подходящий для '" + query + "': " +
                    bestMatch.getStringRepresentation());
        } catch (BestResultNotFoundException e) {
            System.out.println("Для '" + query + "' ничего не найдено: " + e.getMessage());
        }
    }
}
