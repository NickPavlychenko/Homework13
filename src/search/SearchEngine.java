package search;

import org.skypro.skyshop.exception.BestResultNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private List<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new ArrayList<>(); // Динамический список
    }

    public void add(Searchable searchable) {
        searchables.add(searchable); // Просто добавляем - размер не ограничен
    }

    // ОБНОВЛЁННЫЙ МЕТОД: возвращает все подходящие результаты
    public List<Searchable> search(String query) {
        List<Searchable> results = new ArrayList<>();

        for (Searchable item : searchables) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.add(item);
            }
        }

        return results; // Возвращаем все найденные результаты
    }

    public Searchable findBestMatch(String search) throws BestResultNotFoundException {
        if (searchables.isEmpty()) {
            throw new BestResultNotFoundException("Поисковый движок пуст. Нечего искать.");
        }

        Searchable bestMatch = null;
        int maxOccurrences = 0;
        String searchLower = search.toLowerCase();

        for (Searchable item : searchables) {
            String searchTerm = item.getSearchTerm().toLowerCase();

            int occurrences = countOccurrences(searchTerm, searchLower);

            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFoundException("Не найдено подходящих результатов для: '" + search + "'");
        }

        return bestMatch;
    }

    private int countOccurrences(String text, String search) {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(search, index)) != -1) {
            count++;
            index += search.length();
        }

        return count;
    }
}


