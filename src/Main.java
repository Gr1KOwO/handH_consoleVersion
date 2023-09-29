import Creature.*;
import Equipment.*;
import Status.*;

public class Main {
    public static void main(String[] args)
    {
        Player player = new Player("Gr1k", 2, 25, 235, 2, 9, 3,34);
        Demon monster = new Demon("Саргассо", 9, 30, 95, 3, 9,56);
        Monster devil = new Monster("Viktor",12,34,125,4,16,4,0.2);

        Weapon sword = new Weapon("Меч", 10, 5, 5, 10, 0, 5, "Hand"); // Пример оружия
        Armor helmet = new Armor("Шлем", 0, 0, 0, 5, 10, 0, "Head"); // Пример брони
        Armor bestHelmet = new Armor("Улучшенный шлем",0,0,0,20,20,1,"Head");
        Weapon bigSword = new Weapon("Меч Гатса",20,9,20,20,0,0,"Hand");
        Status poison = new Status("Отравление",2,2,0.2);
        Status bleed = new Status("Кровотечение",3,4,0.4);

        //Добавляем эффект отравления на меч
        sword.addStatus(poison);
        sword.addStatus(bleed);
        bigSword.addStatus(bleed);

        bestHelmet.addResistance("Отравление", 50);


        monster.equipItem(sword, "leftHand");
        player.equipItem(sword,"leftHand");
        player.equipItem(bigSword,"rightHand");
        player.equipItem(bestHelmet,"Head");

        System.out.println("Информация о существах:");
        player.printInfo();
        monster.printInfo();
        devil.printInfo();

        System.out.println("Бой начался!");
        int turn = 1;
        while (true) {
            System.out.println("Ход: " + turn);
            player.attack(monster);
            player.attack(devil);
            monster.attack(player);
            devil.attack(player);
            if (!monster.isAlive() && !devil.isAlive())
            {
                System.out.println("Игрок победил!");
                break;
            }

            if (player.getHealth() < player.getMaxHealth() * 0.25) {
                player.heal();
            }

            if(devil.getHealth()<devil.getMaxHealth()*0.25)
            {
                devil.desiccation(player);
            }



            if (!player.isAlive())
            {
                System.out.println("Монстры победили!");
                break;
            }

            monster.applyStatusEffects();
            player.applyStatusEffects();
            devil.applyStatusEffects();


            System.out.println("Информация о существах:");
            player.printInfo();
            monster.printInfo();
            devil.printInfo();
            turn++;
        }

        System.out.println("Бой завершился за " + turn + " ходов");
        player.printInfo();
        monster.printInfo();
        devil.printInfo();
    }
}