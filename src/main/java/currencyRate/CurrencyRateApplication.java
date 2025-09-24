package currencyRate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyRateApplication {
    private static final Map<String, Double> buyMap = Map.of(
            "юань", 11.83,
            "доллар", 85.4,
            "дирхам", 23.62
    );

    private static final Map<String, Double> sellMap = Map.of(
            "юань", 11.51,
            "доллар", 79.7,
            "дирхам", 22.37
    );

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Это приложение для покупки и продажи валюты за рубли");
        start();
    }

    public static void start() {
        System.out.println("Введите номер варианта:\n 1) купить\n 2) продать");
        try {
            switch (Integer.valueOf(scanner.nextLine())) {
                case 1:
                    buy();
                    break;
                case 2:
                    sell();
                    break;
                default:
                    System.out.println("некорректный ввод");
                    start();
            }
        } catch (Exception e) {
            System.out.println("некорректный ввод");
            start();
        }
    }

    private static void buy() {
        System.out.printf("Напишите название валюты(%s)\n", buyMap.keySet());
        String valute = scanner.nextLine();
        if (!buyMap.keySet().contains(valute.toLowerCase())) {
            System.out.println("в списке нет такой валюты");
            buy();
        } else {
            System.out.println("Введите сумму в рублях");
            double sum;
            try {
                sum = Double.valueOf(scanner.nextLine());
                System.out.printf("Вы можете купить: %s %s", convert(sum, valute, false), valute);
            } catch (Exception e) {
                System.out.println("неккоректный ввод");
                buy();
            }
        }
    }

    private static void sell() {
        System.out.printf("Напишите название валюты(%s)\n", buyMap.keySet());
        String valute = scanner.nextLine();
        if (!buyMap.keySet().contains(valute.toLowerCase())) {
            System.out.println("в списке нет такой валюты");
            sell();
        } else {
            System.out.println("Введите сумму в валюте");
            double sum;
            try {
                sum = Double.valueOf(scanner.nextLine());
                System.out.printf("Вы можете получить: %s рублей", convert(sum, valute, true));
            } catch (Exception e) {
                System.out.println("неккоректный ввод");
                sell();
            }
        }
    }

    private static double convert(double sum, String valute, boolean isSell) {
        if (isSell) {
            return (double) Math.round(sum * sellMap.get(valute) * 100) / 100;
        } else {
            return (double) Math.round(sum / buyMap.get(valute) * 100) / 100;
        }
    }
}
