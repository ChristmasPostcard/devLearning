package partTwo;

import java.util.Arrays;

public class Book {
    private String title;
    private Publisher publisher;
    private int year;
    private String[] authors;

    public Book(String title, int year, Publisher publisher) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым.");
        }
        this.title = title;
        if (publisher == null) {
            throw new IllegalArgumentException("Ошибка: название издательства не может быть пустым. Задайте издателя.");
        }
        this.publisher = publisher;
        if (year <= 0) {
            throw new IllegalArgumentException("Ошибка: год не может быть отрицательным или 0. Задайте корректный год");
        }
        this.year = year;
        authors = null;
    }

    public Book(String title, String author, int year, Publisher publisher) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым.");
        }
        this.title = title;
        if (publisher == null) {
            throw new IllegalArgumentException("Ошибка: название издательства не может быть пустым. Задайте издателя.");
        }
        this.publisher = publisher;
        if (year <= 0) {
            throw new IllegalArgumentException("Ошибка: год не может быть отрицательным или 0. Задайте корректный год");
        }
        this.year = year;
        this.authors = new String[]{author};
    }

    public Book(String title, String[] authors, int year, Publisher publisher) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым.");
        }
        this.title = title;
        if (publisher == null) {
            throw new IllegalArgumentException("Ошибка: название издательства не может быть пустым. Задайте издателя.");
        }
        this.publisher = publisher;
        if (year <= 0) {
            throw new IllegalArgumentException("Ошибка: год не может быть отрицательным или 0. Задайте корректный год");
        }
        this.year = year;
        if (authors == null) {
            throw new IllegalArgumentException("Ошибка. Авторы не заданы.");
        }
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String[] getAuthors() {
        return authors;
    }

    public int getAuthorsCount() {
        if (authors == null) {
            return 0;
        }
        return authors.length;
    }

    public String getAuthor(int index) {
        if (index > authors.length) {
            throw new IndexOutOfBoundsException("Индекс за пределами массива");
        }
        return authors[index];
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым.");
        }
        this.title = title;
    }

    public void setPublisher(Publisher publisher) {
        if (publisher == null) {
            throw new IllegalArgumentException("Ошибка: название издательства не может быть пустым. Задайте издателя.");
        }
        this.publisher = publisher;
    }

    public void setYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Ошибка: год не может быть отрицательным или 0. Задайте корректный год");
        }
        this.year = year;
    }

    public void setAuthor(String author) {
        if (authors == null) {
            throw new IllegalArgumentException("Ошибка. Авторы не заданы.");
        }
        authors[authors.length - 1] = author;
    }

    @Override
    public String toString() {
        if (authors == null) {
            return String.format("""
                    Название: %s
                    Издательство: %s, %s
                    Год: %s""", title, publisher.getName(), publisher.getCity(), year);
        }
        return String.format("""
                Название: %s
                Автор(ы): %s
                Издательство: %s, %s
                Год: %s""", title, Arrays.toString(authors), publisher.getName(), publisher.getCity(), year);
    }
}
