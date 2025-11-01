package search;

public class SearchEngine {

    private Searchable[] searchables;
    private int count;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
        this.count = 0;
    }

    public void add(Searchable searchable) {
        if (count < searchables.length) {
            searchables[count] = searchable;
            count++;
        } else {
            System.out.println("Поиск переполнен, невозможно добавить новый элемент");
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int resultCount = 0;

        for (int i = 0; i < count && resultCount < 5; i++) {
            Searchable item = searchables[i];
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                results[resultCount] = item;
                resultCount++;
            }
        }

        return results;
    }

    public Searchable findBestMatch(String search) {
        if (count == 0) {
            return null;
        }

        Searchable bestMatch = null;
        int maxOccurrences = 0;
        String searchLower = search.toLowerCase();

        for (int i = 0; i < count; i++) {
            Searchable item = searchables[i];
            String searchTerm = item.getSearchTerm().toLowerCase();

            int occurrences = countOccurrences(searchTerm, searchLower);

            if (occurrences > maxOccurrences) {
                maxOccurrences = occurrences;
                bestMatch = item;
            }
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
