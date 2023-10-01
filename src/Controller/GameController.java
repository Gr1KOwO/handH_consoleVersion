package Controller;

import Creature.Creature;
import Creature.Player;

import java.util.*;

public class GameController 
{
    private List<Creature> enemies;
    private List<Creature> creatures;
    private Player player;
    private int currentTurnIndex;

    private Scanner scanner;
    private Random random;
    private boolean gameOver;

    public GameController(Player player, List<Creature> enemies)
    {
        this.player = player;
        this.enemies = enemies;
        this.creatures = new ArrayList<>();
        this.currentTurnIndex = 0;
        this.random = new Random();
        this.scanner = new Scanner(System.in);
    }

    // Метод для выполнения следующего хода
    public void performNextTurn() {
        Creature currentTurnCreature = creatures.get(currentTurnIndex);

        if (currentTurnCreature instanceof Player)
        {
            playerTurn(player, enemies.get(0)); // Пока ходит игрок против первого врага
        }
        else
        {
            enemyTurn(enemies.get(0), player); // Пока ходит первый враг против игрока
        }

        // Проверка на победу или поражение
        if (checkWinCondition()) {
            // Враг повержен, удаление его из списка
            enemies.remove(0);
            System.out.println("Вы победили монстра!");

            // Проверяем, остались ли ещё враги
            if (enemies.isEmpty()) {
                // Все монстры повержены, завершаем игру
                System.out.println("Вы победили всех монстров! Игра окончена.");
                gameOver = true;
            }
        }
        else if
        (checkLoseCondition())
        {
            // Игрок проиграл, завершаем игру
            System.out.println("Вы проиграли! Игра окончена.");
            gameOver = true;
        }
        else
        {
            // Переход к следующему существу
            currentTurnIndex = (currentTurnIndex + 1) % creatures.size();
        }
    }

    // Методы для проверки условий победы и поражения
    private boolean checkWinCondition() {

        return enemies.isEmpty();
    }

    private boolean checkLoseCondition() {

        return !player.isAlive();
    }

    public void startGame()
    {
        gameOver = false;

        // Добавляем игрока и врагов в список существ
        creatures.add(player);
        creatures.addAll(enemies);
        creatures.sort(Comparator.comparingInt(Creature::getSpeed).reversed());
        while (!gameOver)
        {
            // Выполняем следующий ход
            performNextTurn();
        }
    }

    private void playerTurn(Player player, Creature enemy) {
        System.out.println("Выберите действие:");
        System.out.println("1. Атаковать");
        System.out.println("2. Восстановить здоровье");

        int choice = scanner.nextInt();

        if (choice == 1)
        {
            player.attack(enemy);
        }
        else if (choice == 2)
        {
            player.heal();
        }
        else
        {
            System.out.println("Некорректный выбор действия. Вы теряете ход.");
        }
    }

    private void enemyTurn(Creature enemy, Creature player)
    {
        // Реализовать логику хода врага здесь
        enemy.attack(player);
    }

    private boolean continueGame() {
        System.out.println("Желаете продолжить игру? (да/нет)");
        String choice = scanner.next().toLowerCase();
        return choice.equals("да");
    }
}
