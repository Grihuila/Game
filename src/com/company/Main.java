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
    private Player player = new Player();
    private Computer computer = new Computer();
    private String[] acts = {"Sum"};
    private String act;
    private specialCard Card = new specialCard();
    private Scanner in = new Scanner(System.in);
    private String input;
    private void start() {
        System.out.println("Игра против пекарни. Колода состоит из 50 карт(1-50). У тебя есть 2 карты и у него. Раз в один ход ты можешь сбросить 1 карту." +
                "У кого из вас карты больше в сумме, тому присуждается определитель ((1 ваша карта x 2 карта пк) - (2 ваша карта x карта пк))." +
                "Помимо этого вы можете использовать специальную карту(). Пример игры: " +
                " " +
                "Например, у вас карты 22 и 4, у ПК 2 и 11 (после сбрасывания карт), тогда у вас в сумме 26>13------>" +
                "Определитель присуждается вам. В данном случае он равен (242 - 8 = 234). Но будьте осторожны, т.к. определитель может быть и <0" +
                "В таком случае вы лишь ухудшите свой счёт РОФЛАНЕБАЛО");
    }
    private void draw(int[] deck) {
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
    private void dropCard(int i) {
            System.out.println("////////////////////////");
            System.out.println("[ " + player.deck[i]+" ] "+" [ "+player.deck[i+1]+" ]");
            System.out.println("--------------------------");
            System.out.println("[ " + computer.deck[i]+" ] "+" [ "+computer.deck[i+1]+" ]");
            System.out.println("////////////////////////");
    }
    private String dropSpecialCard() {
        Random random = new Random();
        int rand = random.nextInt(acts.length);
        return acts[rand];
    }
    private int[] getPcCard(int index) {
        int[] card = new int[2];
        card[0] = computer.deck[index];
        card[1] = computer.deck[index+1];
        return card;
    }
    private void play(int[][] cards) {
        int determinant;
        int[] playerCards = new int[2];
        int[] pcCards = new int[2];
        int playerValue = 0, pcValue = 0; // Переменая для хранения сумму карт
        for (int i = 0; i < 2; i++) {
            playerCards[i] = cards[0][i];
            pcCards[i] = cards[1][i];
            playerValue += playerCards[i];
            pcValue += pcCards[i];
        }
        determinant = cards[0][0]*cards[1][1] - cards[1][0]*cards[0][1];
        if (pcValue > playerValue) {
            computer.point += determinant;
        } else {
            player.point += determinant;
        }
    }
    private void useSpecialCard(String _act, int val1, int val2) {
       switch (_act) {
           case "sum":
               Card.sum();
               break;
       }
    }
    private void printStats() {
        System.out.print("Ваш счет: " + player.point);
        System.out.print(" Счет компьтера" + computer.point + '\n');
    }
    void run() {
        draw(player.deck);
        draw(computer.deck);
        for (int i = 0; i < 50-1; i+=2) {
            dropCard(i);
            act = dropSpecialCard();
            useSpecialCard(act, player.deck[i], player.deck[i+1]);
            printStats();
        }
    }
}
class specialCard {
     void sum() {
         //TODO
     }

}
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
