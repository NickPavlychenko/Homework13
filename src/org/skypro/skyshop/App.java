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

        System.out.println();

        System.out.println(" - Демонстрация проверки даныых -");
        testDataValidation();

        System.out.println();

        System.out.println("  - Демонстрация основго функционала - ");
        testMainFunction();
    }

    private static void testDataValidation() {
        System.out.println(" - Тест  корректных данных - ");
        try {
            SimpleProduct validProduct = new SimpleProduct("Валидный товар", 1000);
            System.out.println("Корректный товар создан: " + validProduct.getName());
        } catch (IllegalArgumentException e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage() );
        }

        System.out.println();
        System.out.println(" - Тест некорректных даанных - ");

        try {
            SimpleProduct emptyName = new SimpleProduct("  ", 1000);
            System.out.println("Ошибка, в названии пусто");
        } catch (IllegalArgumentException  e) {
            System.out.println("Caught IllegalArgumentException " + e.getMessage());
        }

        try {
            SimpleProduct zeroPrice = new SimpleProduct("Товар", 0);
            System.out.println("Ошибка, цена ноль");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException " + e.getMessage());
        }

        try {
            DiscountedProduct negativeDiscount = new DiscountedProduct("Товар", 1000, -10);
            System.out.println("Ошибка отрицательной скидки");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException " + e.getMessage());
        }

        try {
            DiscountedProduct highDiscount = new DiscountedProduct("Товар", 1000, 150);
            System.out.println("Ошибка слишком большой скидки");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught IllegalArgumentException " + e.getMessage());
        }
    }

    public static void testMainFunction() {
        SimpleProduct laptop = new SimpleProduct("Игровой ноутбук для геймеров", 50000);
        SimpleProduct mouse = new SimpleProduct("Игровая мышь для игр", 5000);
        DiscountedProduct keyboard = new DiscountedProduct("Механическая клавиатура для игр", 7000, 30);
        Article article1 = new Article("Обзор игровых ноутбуков", "Лучшие игровые ноутбуки для геймеров 2025 года");
        Article article2 = new Article("Выбор игровой мыши", "Как выбрать игровую мышь для компьютерных игр");

        SearchEngine searchEngine = new SearchEngine(10);
        searchEngine.add(laptop);
        searchEngine.add(mouse);
        searchEngine.add(keyboard);
        searchEngine.add(article1);
        searchEngine.add(article2);

        System.out.println(" - Обычный поиск - ");
        testSearch(searchEngine, "игр");

        System.out.println();
        System.out.println(" -  Улучшенный поиск  - ");
        testBestMatch(searchEngine, "игр");
        testBestMatch(searchEngine, "ноутбук");
        testBestMatch(searchEngine, "мышь");

        System.out.println();
        System.out.println(" - Демонстрация корзины - ");
        ProductBasket basket = new ProductBasket();
        basket.addProduct(laptop);
        basket.addProduct(mouse);
        basket.addProduct(keyboard);
        basket.printContents();
    }

    private static void testSearch(SearchEngine searchEngine, String query) {
        System.out.println(" - Поиск: '" + query + "' - ");
        Searchable[] results = searchEngine.search(query);

        int count = 0;
        for (Searchable result : results) {
            if (result != null) count++;
        }

        System.out.println("Найдено результатов: " + count);
        for (int i = 0; i < results.length; i++) {
            if (results[i] != null) {
                System.out.println((i + 1) + ". " + results[i].getStringRepresentation());
            }
        }
    }

    private static void testBestMatch(SearchEngine searchEngine, String query) {
        Searchable bestMatch = searchEngine.findBestMatch(query);
        if (bestMatch != null) {
            System.out.println("Наиболее подходящий для '" + query + "': " +
                    bestMatch.getStringRepresentation());
        } else {
            System.out.println("Для '" + query + "' ничего не найдено");
        }
        }
    }

