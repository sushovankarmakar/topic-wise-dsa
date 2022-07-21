package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * https://javatechonline.com/java-8-sorting-list-use-java8-to-make-sorting-easy/
 */
public class Java8Sorting {

    public static void main(String[] args) {
        Comparator<Book> sortByName = (b1, b2) -> b1.getName().compareToIgnoreCase(b2.getName());
        Comparator<Book> sortByAuthor = (b1, b2) -> b1.getAuthor().compareToIgnoreCase(b2.getAuthor());
        Comparator<Book> sortByCost = Comparator.comparingDouble(Book::getCost);

        List<Book> books = getBooks();

        books.stream()
                .sorted(sortByName
                        .thenComparing(sortByAuthor)
                        .thenComparing(sortByCost)
                ).forEach(System.out::println);
    }

    private static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "acf", "qwo", 10.0));
        books.add(new Book(2, "abc", "fge", 21.0));
        books.add(new Book(3, "acf", "aqz", 13.0));
        books.add(new Book(4, "xcf", "poc", 11.0));
        books.add(new Book(5, "ewc", "rtl", 41.0));
        return books;
    }

    static class Book {
        private Integer id;
        private String name;
        private String author;
        private Double cost;

        public Book(Integer id, String name, String author, Double cost) {
            this.id = id;
            this.name = name;
            this.author = author;
            this.cost = cost;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", author='" + author + '\'' +
                    ", cost=" + cost +
                    '}';
        }
    }
}

