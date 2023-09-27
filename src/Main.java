public class Main {
    public static void main(String[] args)
    {
        Player player = new Player("Gr1k", 32, 25, 95, 7, 12, 3);
        Demon monster = new Demon("Саргассо", 29, 50, 225, 3, 14);

        Weapon sword = new Weapon("Меч", 10, 5, 5, 10, 0, 5, 15); // Пример оружия
        Armor helmet = new Armor("Шлем", 0, 0, 0, 5, 10, 0, 0); // Пример брони
        Weapon bigSword = new Weapon("Меч Гатса",20,9,20,20,0,0,0);
        Status poison = new Status("Отравление",2,2,0.2);
        sword.addStatus(poison);
        monster.equipItem(sword, "leftHand");
        monster.equipItem(sword, "rightHand");
        player.equipItem(helmet, "head");
        player.equipItem(sword,"leftHand");


        System.out.println("Информация о существах:");
        player.printInfo();
        monster.printInfo();

        System.out.println("Бой начался!");
        int turn = 1;
        while (player.isAlive() && monster.isAlive()) {
            System.out.println("Ход: " + turn);
            player.attack(monster);

            if (!monster.isAlive()) {
                System.out.println("Игрок победил!");
                break;
            }

            if (player.getHealth() < player.getMaxHealth() * 0.25) {
                player.heal();
            }

            monster.attack(player);

            if (!player.isAlive()) {
                System.out.println("Монстр победил!");
                break;
            }

            System.out.println("Информация о существах:");
            player.printInfo();
            monster.printInfo();

            turn++;
        }

        System.out.println("Бой завершился за " + turn + " ходов");
        player.printInfo();
        monster.printInfo();
    }
}