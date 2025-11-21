package search;

import org.skypro.skyshop.exception.BestResultNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private List<Searchable> searchables;

    public SearchEngine() {
        this.searchables = new ArrayList<>();
    }

    public void add(Searchable searchable) {
        searchables.add(searchable);
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> results = new TreeMap<>();

        for (Searchable item : searchables) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results.put(item.getSearchTerm(), item);
            }
        }
        return results;
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


