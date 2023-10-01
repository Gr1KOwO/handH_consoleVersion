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

public class Main {
    public static void main(String[] args)
    {
        Player player = new Player("Gr1k", 2, 25, 435, 2, 9, 3,34);
        Demon monster = new Demon("Саргассо", 9, 30, 395, 3, 9,56);
        Monster devil = new Monster("Viktor",12,34,425,4,16,4,0.22);

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

        bestHelmet.addResistance("Отравление", 20);
        bestHelmet.addResistance("Кровотечение", 53);




        monster.equipItem(sword, "leftHand");
        devil.equipItem(sword,"rightHand");
        devil.equipItem(helmet,"Head");
        player.equipItem(bigSword,"rightHand");

        List<Creature> enemiesList = new ArrayList<>();
        enemiesList.add(devil);
        enemiesList.add(monster);


        GameController game = new GameController(player,enemiesList);
        game.startGame();
    }
}