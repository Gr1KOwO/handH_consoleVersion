import Controller.GameController;
import Creature.Creature;
import Creature.Demon;
import Creature.Monster;
import Creature.Player;
import Equipment.Armor;
import Equipment.Weapon;
import Status.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Создание персонажа");

        System.out.println("Создание персонажа");

        // Запрашиваем никнейм
        System.out.print("Введите никнейм: ");
        String name = scanner.nextLine();

        // Запрашиваем атаку и проверяем, что она в диапазоне от 1 до 30
        int attack;
        while (true) {
            System.out.print("Введите атаку (1-30): ");
            if (scanner.hasNextInt()) {
                attack = scanner.nextInt();
                if (attack >= 1 && attack <= 30) {
                    break;
                } else {
                    System.out.println("Некорректные данные. Атака должна быть в диапазоне от 1 до 30.");
                }
            } else {
                System.out.println("Некорректные данные. Введите целое число.");
                scanner.next(); // Очистка буфера от некорректного ввода
            }
        }

        // Запрашиваем защиту и проверяем, что она в диапазоне от 1 до 30
        int defense;
        while (true) {
            System.out.print("Введите защиту (1-30): ");
            if (scanner.hasNextInt()) {
                defense = scanner.nextInt();
                if (defense >= 1 && defense <= 30) {
                    break;
                } else {
                    System.out.println("Некорректные данные. Защита должна быть в диапазоне от 1 до 30.");
                }
            } else {
                System.out.println("Некорректные данные. Введите целое число.");
                scanner.next(); // Очистка буфера от некорректного ввода
            }
        }

        // Запрашиваем здоровье (натуральное число)
        int health;
        while (true) {
            System.out.print("Введите здоровье: ");
            if (scanner.hasNextInt()) {
                health = scanner.nextInt();
                if (health > 0) {
                    break;
                } else {
                    System.out.println("Некорректные данные. Здоровье должно быть натуральным числом.");
                }
            } else {
                System.out.println("Некорректные данные. Введите целое число.");
                scanner.next(); // Очистка буфера от некорректного ввода
            }
        }

        // Генерируем случайный минимальный урон
        int minDamage = random.nextInt(10) + 1;

        // Генерируем случайный максимальный урон от минимального до 20
        int maxDamage = minDamage + random.nextInt(20) + 1;

        // Генерируем удачу от 0 до 2
        int luck = random.nextInt(3);

        // Генерируем скорость от 20 до 53
        int speed = random.nextInt(34) + 20;

        Player player = new Player(name,attack,defense,health,minDamage,maxDamage,luck,speed);
        Demon monster = new Demon("Саргассо", 9, 30, 125, 3, 9,56);
        Monster devil = new Monster("Viktor",12,24,135,4,16,34,0.22);

        Weapon sword = new Weapon("Меч", 10, 5, 5, 10, 4, 1, "Hand"); // Пример оружия
        Armor helmet = new Armor("Шлем", 0, 0, 0, 5, 10, 0, "Head"); // Пример брони
        Armor bestHelmet = new Armor("Улучшенный шлем",0,0,0,20,20,1,"Head");
        Weapon bigSword = new Weapon("Меч Гатса",20,9,20,20,0,0,"Hand");
        Status poison = new Status("Отравление",2,2,0.2);
        Status bleed = new Status("Кровотечение",3,4,0.4);

        //Добавляем эффект отравления на меч
        sword.addStatus(poison);
        sword.addStatus(bleed);
        bigSword.addStatus(bleed);

        bestHelmet.addResistance("Отравление", 60);
        bestHelmet.addResistance("Кровотечение", 83);


        monster.equipItem(sword, "leftHand");
        devil.equipItem(sword,"rightHand");
        devil.equipItem(helmet,"Head");
        player.equipItem(bigSword,"rightHand");
        player.equipItem(bestHelmet,"Head");

        List<Creature> enemiesList = new ArrayList<>();
        enemiesList.add(devil);
        enemiesList.add(monster);


        GameController game = new GameController(player,enemiesList);
        game.startGame();
    }
}