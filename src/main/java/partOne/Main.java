package partOne;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var countries = generateData();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Привет пользователь! Поддерживается три команды:
                create — создаёт новую страну
                print — выводит все добавленные страны
                exit — завершает программу""");
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "create" -> {
                    Country country = createCountry(scanner);
                    countries = addCountry(countries, country);
                    System.out.println("Страна добавлена: " + country.getName());
                }
                case "print" -> {
                    printAll(countries);
                }
                case "exit" -> {
                    scanner.close();
                    System.out.println("Программа завершена.");
                    return;
                }
                default -> System.out.println("Неизвестная команда. Доступные: create, print, exit.");
            }
        }
    }

    static void printAll(Country[] countries) {
        if (countries != null) {
            for (Country country : countries) {
                System.out.println(country);
            }
        }
    }

    private static Country[] addCountry(Country[] countries, Country country) {
        var newArray = new Country[countries.length + 1];
        System.arraycopy(countries, 0, newArray, 0, countries.length);
        newArray[countries.length] = country;
        return newArray;
    }

    private static Country createCountry(Scanner scanner) {
        while (true) {
            try {
                Country country = new Country();
                country.setName(readString(scanner, "Введите название страны:"));
                country.setArea(readDouble(scanner, "Введите площадь (км²):"));
                country.setPopulation(readLong(scanner, "Введите население:"));
                boolean answer = readStringOptional(scanner, "Есть ли столица? (y/n):");
                if (answer) {
                    String name = readString(scanner, "Введите название столицы:");
                    long population = readLong(scanner, "Введите население столицы:");
                    country.setCapital(name, population);
                }
                return country;
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println("Ошибка валидации: " + e.getMessage() + ". Попробуйте снова.");
            }
        }
    }

    private static String readString(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Ошибка: строка не может быть пустой.");
            } else if (input.matches(".*\\d.*")) {
                System.out.println("Ошибка: строка не должна содержать цифры.");
            } else {
                return input;
            }
        }
    }

    private static boolean readStringOptional(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
        }
    }

    private static double readDouble(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное дробное число.");
            }
        }
    }

    private static long readLong(Scanner scanner, String text) {
        while (true) {
            System.out.println(text);
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное целое число.");
            }
        }
    }

    private static Country[] generateData() {
        return new Country[]{
                new Country("Russia", 17100000D, 146700000L, "Moscow", 12600000L),
                new Country("Finland", 338000D, 5500000L, "Helsinki", 655000L),
                new Country("France", 643800D, 67800000L, "Paris", 2100000L),
                new Country("Andorra", 467D, 85400L, "Andorra la Vella", 22600L),
                new Country("Singapore", 2D, 5700000L)
        };
    }
}
