package partTwo;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Publisher[] publishers = createPublishers();
        Book[] books = createBooks(publishers);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Привет пользователь! Поддерживается три команды:
                create — создаёт новую книгу/издателя
                print — выводит все добавленные книги
                exit — завершает программу""");
        while (true) {
            String command = scanner.nextLine().trim().toLowerCase();
            switch (command) {
                case "create" -> {
                    try {
                        System.out.print("Какой объект необходимо создать (p/b): ");
                        String input = scanner.nextLine().trim().toLowerCase();
                        if (input.equals("p")) {
                            Publisher newPublisher = createPublisher(scanner, publishers);
                            if (newPublisher != null) {
                                publishers = Arrays.copyOf(publishers, publishers.length + 1);
                                publishers[publishers.length - 1] = newPublisher;
                                System.out.println("Издательство добавлено: " + newPublisher.getName() + ".");
                            }
                        } else if (input.equals("b")) {
                            Book newBook = createBook(scanner, publishers);
                            books = Arrays.copyOf(books, books.length + 1);
                            books[books.length - 1] = newBook;
                            System.out.println("Книга добавлена: " + newBook.getTitle() + ".");
                        } else {
                            System.out.println("Неверный ввод. Введите 'p' или 'b'.");
                        }
                    } catch (NullPointerException | IllegalArgumentException e) {
                        System.out.println("Ошибка валидации. Попробуйте снова.");
                    }
                }
                case "print" -> printAll(books);
                case "exit" -> {
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Неизвестная команда. Доступные: create, print, exit.");
            }
        }
    }

    private static Publisher[] createPublishers() {
        return new Publisher[]{new Publisher("Проспект", "Москва"),
                new Publisher("Питер", "Санкт-Петербург"),
                new Publisher("БХВ", "Санкт-Петербург"),
                new Publisher("Диалектика", "Киев")};
    }

    private static Book[] createBooks(Publisher[] publishers) {
        return new Book[]{
                new Book("Computer Science: основы программирования на Java, ООП, алгоритмы и структуры данных",
                        new String[]{"Седжвик Роберт", "Уэйн Кевин"},
                        2018,
                        findPublisher(publishers, "Питер")),
                new Book("Разработка требований к программному обеспечению. 3-е издание, дополненное",
                        "Вигерс Карл",
                        2019,
                        findPublisher(publishers, "БХВ")),
                new Book("Java. Полное руководство, 10-е издание",
                        "Шилдт Герберт",
                        2018,
                        findPublisher(publishers, "Диалектика")),
                new Book("C/C++. Процедурное программирование",
                        "Полубенцева М.И.",
                        2017,
                        findPublisher(publishers, "БХВ")),
                new Book("Конституция РФ",
                        2020,
                        findPublisher(publishers, "Проспект"))
        };
    }

    static void printAll(Book[] book) {
        if (book != null) {
            for (Book b : book) {
                System.out.println(b.toString());
            }
        }
    }

    private static Publisher createPublisher(Scanner scanner, Publisher[] publishers) {
        while (true) {
            try {
                System.out.print("Введите название издательства: ");
                String name = scanner.nextLine().trim();
                if (findPublisher(publishers, name) != null) {
                    System.out.println("Издатель уже есть в базе.");
                    return null;
                }
                System.out.print("Введите город издательства: ");
                String city = scanner.nextLine().trim();
                return new Publisher(name, city);
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
    }

    private static Publisher findPublisher(Publisher[] publishers, String name) {
        for (Publisher p : publishers) {
            if (name.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    private static Book createBook(Scanner scanner, Publisher[] publishers) {
        System.out.println("Введите название книги");
        String name = scanner.nextLine().trim();
        System.out.println("Введите год издания книги");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Какому издательству принадлежит книга?");
        String publisherName = scanner.nextLine().trim();
        Publisher publisher = findPublisher(publishers, publisherName);
        if (publisher == null) {
            throw new NullPointerException("Нет издательства в базе");
        }
        System.out.println("Введите автора/ов книги, через запятую");
        String[] authors = scanner.nextLine().trim().split(",");
        if (authors.length == 1) {
            return new Book(name, authors[0], year, publisher);
        } else if (authors.length > 1) {
            return new Book(name, authors, year, publisher);
        }
        return new Book(name, year, publisher);
    }
}
