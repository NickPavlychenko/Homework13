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

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        System.out.println();

        System.out.println(" - Демонстрация проверки даныых -");
        testDataValidation();

        System.out.println();

        System.out.println("  - Демонстрация основго функционала - ");
        testMainFunctionality();

        System.out.println();
        System.out.println(" - Демонастрация собственнго исключения - ");
        testCustomException();
    }

    private static void testDataValidation() {
        System.out.println(" - Тест корректных данных - ");
        try {
            SimpleProduct validProduct = new SimpleProduct("Валидный товар", 1000);
            System.out.println(" Корректный товар создан: " + validProduct.getName());
        } catch (IllegalArgumentException e) {
            System.out.println(" Неожиданная ошибка: " + e.getMessage());
        }

        System.out.println(" - Тест некорректных данных - ");

        try {
            SimpleProduct emptyName = new SimpleProduct("   ", 1000);
            System.out.println(" Ожидалась ошибка для пустого названия");
        } catch (IllegalArgumentException e) {
            System.out.println(" Правильно отловили: " + e.getMessage());
        }

        try {
            SimpleProduct zeroPrice = new SimpleProduct("Товар", 0);
            System.out.println(" Ожидалась ошибка для нулевой цены");
        } catch (IllegalArgumentException e) {
            System.out.println(" Правильно отловили: " + e.getMessage());
        }

        try {
            DiscountedProduct negativeDiscount = new DiscountedProduct("Товар", 1000, -10);
            System.out.println(" Ожидалась ошибка для отрицательной скидки");
        } catch (IllegalArgumentException e) {
            System.out.println(" Правильно отловили: " + e.getMessage());
        }

        try {
            DiscountedProduct bigDiscount = new DiscountedProduct("Товар", 1000, 150);
            System.out.println(" Ожидалась ошибка для слишком большой скидки");
        } catch (IllegalArgumentException e) {
            System.out.println(" Правильно отловили: " + e.getMessage());
        }
    }

    private static void testMainFunctionality() {
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
        System.out.println(" - Улучшенный поиск (наиболее подходящий) - ");
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

    private static void testCustomException() {
        System.out.println(" - Тест пустого поискового движка - ");
        SearchEngine emptyEngine = new SearchEngine(10);

        try {
            emptyEngine.findBestMatch("тест");
            System.out.println(" Ожидалось исключение BestResultNotFoundException");
        } catch (BestResultNotFoundException e) {
            System.out.println(" Правильно поймали: " + e.getMessage());
        }

        System.out.println(" - Тест поиска без результатов - ");
        SearchEngine engineWithData = new SearchEngine(10);
        SimpleProduct laptop = new SimpleProduct("Игровой ноутбук", 50000);
        SimpleProduct mouse = new SimpleProduct("Игровая мышь", 5000);
        DiscountedProduct keyboard = new DiscountedProduct("Механическая клавиатура", 7000, 30);
        Article article1 = new Article("Обзор игровых ноутбуков", "Лучшие игровые ноутбуки 2025 года");
        Article article2 = new Article("Выбор игровой мыши", "Как выбрать игровую мышь");

        engineWithData.add(laptop);
        engineWithData.add(mouse);
        engineWithData.add(keyboard);
        engineWithData.add(article1);
        engineWithData.add(article2);

        try {
            engineWithData.findBestMatch("абсолютнонесуществующееслово");
            System.out.println(" Ожидалось исключение BestResultNotFoundException");
        } catch (BestResultNotFoundException e) {
            System.out.println(" Правильно поймали: " + e.getMessage());
        }

        System.out.println(" - Тест успешного поиска - ");
        try {
            Searchable result = engineWithData.findBestMatch("игр");
            System.out.println(" Успешно нашли: " + result.getStringRepresentation());
        } catch (BestResultNotFoundException e) {
            System.out.println(" Неожиданная ошибка: " + e.getMessage());
        }

        System.out.println(" - Тест поиска по конкретному слову - ");
        try {
            Searchable result = engineWithData.findBestMatch("ноутбук");
            System.out.println(" Наиболее релевантный для 'ноутбук': " + result.getStringRepresentation());
        } catch (BestResultNotFoundException e) {
            System.out.println(" Неожиданная ошибка: " + e.getMessage());
        }
    }

    private static void testSearch(SearchEngine searchEngine, String query) {
        System.out.println("Поиск: '" + query + "' ---");
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
        try {
            Searchable bestMatch = searchEngine.findBestMatch(query);
            System.out.println("Наиболее подходящий для '" + query + "': " +
                    bestMatch.getStringRepresentation());
        } catch (BestResultNotFoundException e) {
            System.out.println("Для '" + query + "' ничего не найдено: " + e.getMessage());
        }
    }
}
