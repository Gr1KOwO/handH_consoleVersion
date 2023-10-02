package Controller;

import Creature.*;

import java.util.*;

public class GameController 
{
    private List<Creature> enemies;  // Список врагов в игре.
    private List<Creature> creatures;// Список существ, учавствующих в раунде
    private Player player;// Игрок.
    private int currentTurnIndex;// Индекс текущего существа, которое совершает ход.

    private Scanner scanner;
    private Random random;
    private boolean gameOver;
    Creature targetCreature;

    int turn=1;// Счетчик ходов раунда
    /**
     * Конструктор класса GameController.
     *
     * @param player  Игрок.
     * @param enemies Список врагов в игре.
     */
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
    public void performNextTurn()
    {
        System.out.println("Текущий ход: " + turn);
        Creature currentTurnCreature = creatures.get(currentTurnIndex);

        if (currentTurnCreature instanceof Player)
        {
            playerTurn(player, targetCreature);
        }
        else
        {
            // Атака врага направлена к игроку
            enemyTurn(currentTurnCreature, player);
        }

        // Проверка на победу или поражение
        if (checkWinCondition())
        {

            enemies.remove(targetCreature); // Удаление врага из списка enemies
            System.out.println("Вы победили монстра!");

            // Проверяем, остались ли ещё враги и добавляем нового случайного врага
            if (enemies.isEmpty())
            {
                // Все монстры повержены, завершаем игру
                System.out.println("Вы победили всех монстров! Игра окончена.");
                gameOver = true;
            }
            else
            {
                if (continueGame())
                {
                    creatures.clear();
                    creatures.add(player);
                    addRandomEnemyToCreatures();
                }
                else
                {
                    gameOver = true;
                }
            }
        }
        else if (checkLoseCondition())
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

        return !targetCreature.isAlive();
    }

    private boolean checkLoseCondition() {

        return !player.isAlive();
    }

    public void startGame()
    {
        gameOver = false;

        // Добавляем игрока и врагов в список существ
        creatures.add(player);
        addRandomEnemyToCreatures();
        while (!gameOver)
        {

            performNextTurn();
        }
    }

    private void playerTurn(Player player, Creature enemy)
    {
        player.getStatusManager().applyStatusEffects();
        System.out.println("Текущее состояние: ");
        player.printInfo();
        System.out.println();
        System.out.println("Текущее врага: ");
        enemy.printInfo();
        System.out.println();
        System.out.println("Выберите действие:");
        System.out.println("1. Атаковать");
        System.out.println("2. Восстановить здоровье");
        System.out.println("Количество возможностей восстановить здоровье: " + player.getHealCount());
        try
        {
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
            turn++;
        }
        catch(Exception ex)
        {
            System.out.println("Некорректный выбор действия. Вы теряете ход.");
            turn++;
            scanner.next(); // Очищаем буфер ввода
        }
    }

    private void enemyTurn(Creature enemy, Creature player)
    {
        enemy.getStatusManager().applyStatusEffects();
        if(enemy instanceof Demon)
        {
            enemy.attack(player);
        }
        if(enemy instanceof Monster enemyMonster)
        {
            if(enemyMonster.getHealth()<enemyMonster.getMaxHealth()*0.35)
            {
                enemyMonster.desiccation(player);
            }
            enemyMonster.attack(player);
        }

        turn++;
    }

    private boolean continueGame()
    {
        System.out.println("Желаете продолжить игру? (yes/no)");
        String choice = scanner.next().toLowerCase();
        return choice.equals("yes");
    }

    private void addRandomEnemyToCreatures()
    {
        if (!enemies.isEmpty()) {
            int randomIndex = random.nextInt(enemies.size());
            Creature randomEnemy = enemies.remove(randomIndex);
            targetCreature = randomEnemy;
            creatures.add(randomEnemy);
            creatures.sort(Comparator.comparingInt(Creature::getSpeed).reversed());
        }
    }
}
