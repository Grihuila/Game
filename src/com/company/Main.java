package com.company;

import java.util.Random;
import java.util.Scanner;

class Player {
    int point = 0;
    int[] deck = new int[50];
}
class Computer {
    int point = 0;
    int[] deck = new int[50];
}
class Game {
    static Player player = new Player();
    static Computer computer = new Computer();
    static String[] acts = {"Sum"};
    static String act;
    static specialCard Card = new specialCard();
    static Scanner in = new Scanner(System.in);
    static String input;
    public static void start() {
        System.out.println("Игра против пекарни. Колода состоит из 50 карт(1-50). У тебя есть 2 карты и у него. Раз в один ход ты можешь сбросить 1 карту." +
                "У кого из вас карты больше в сумме, тому присуждается определитель ((1 ваша карта x 2 карта пк) - (2 ваша карта x карта пк))." +
                "Помимо этого вы можете использовать специальную карту(). Пример игры: " +
                " " +
                "Например, у вас карты 22 и 4, у ПК 2 и 11 (после сбрасывания карт), тогда у вас в сумме 26>13------>" +
                "Определитель присуждается вам. В данном случае он равен (242 - 8 = 234). Но будьте осторожны, т.к. определитель может быть и <0" +
                "В таком случае вы лишь ухудшите свой счёт РОФЛАНЕБАЛО");
    }
    public static void run() {
        draw(player.deck);
        draw(computer.deck);
        for (int i = 0; i < 50-1; i+=2) {
            dropCard(i);
            act = dropSpecialCard();
            useSpecialCard(act, player.deck[i], player.deck[i+1]);
            play(player.deck[i], computer.deck[i]);
            play(player.deck[i+1], computer.deck[i+1]);
            printStats();
        }
    }

    private static void draw(int[] deck) {
        int[] arr = new int[50];
        int rand, len;
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        len = arr.length;
        while (len != 1) {
            rand = random.nextInt(len);
            if (rand == len - 1) deck[arr.length - len] = arr[len - 1];
            else deck[arr.length - len] = arr[rand];
            for (int j = 0; j < len - 1; j++) {
                if (j >= rand) {
                    arr[j] = arr[j + 1];
                }
            }
            len--;
        }
        deck[arr.length - len] = arr[0];
//
    }
    private static void dropCard(int i) {
            System.out.println("////////////////////////");
            System.out.println("[ " + player.deck[i]+" ] "+" [ "+player.deck[i+1]+" ]");
            System.out.println("--------------------------");
            System.out.println("[ " + computer.deck[i]+" ] "+" [ "+computer.deck[i+1]+" ]");
            System.out.println("////////////////////////");
    }
    private static String dropSpecialCard() {
        Random random = new Random();
        int rand = random.nextInt(acts.length);
        return acts[rand];
    }
    private static void play(int card1, int card2) {
        if (card1 > card2) {
            player.point += card1 - card2;

        } else if(card1 < card2) {
            computer.point += card2 - card1;
        } else {
            player.point+=card1/2;
            computer.point+=card1/2;
        }
    }
    private static void useSpecialCard(String _act, int val1, int val2) {
       switch (_act) {
           case "sum":
               //TODO
               break;
       }
    }
    private static void printStats() {
        System.out.print("Ваш счет: " + player.point);
        System.out.print(" Счет компьтера" + computer.point + '\n');
    }
}
class specialCard {
     public static void sum() {
         //TODO
     }
}
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        System.out.println("Hy");
        game.run();
    }
}