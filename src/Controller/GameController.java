package Controller;

import Creature.*;

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
    Creature targetCreature;

    int turn=1;

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
        System.out.println("Желаете продолжить игру? (да/нет)");
        String choice = scanner.next().toLowerCase();
        return choice.equals("да");
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
