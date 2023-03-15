import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
    /* создали и инициировали массив строк с наименованием карт */
    public static String[] Deck = {"R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10",
                                   "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10",
                                   "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10",
                                   "W1", "W2", "W3", "W4", "W5", "W6", "W7", "W8", "W9", "W10"};

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        //Инициализация переменных
        int N;
        int C;
        int C1;
        //Создаем список игроков, т.е. активируем динамический массив с объектами класса Player
        // для начала зададим максимально возможное число игроков-40
        ArrayList<Player> players = new ArrayList<>(40);
        boolean error = false;
        do { error = false; // на всякий случай вводим переменную error
             String l = console.next(); //считываем с косоли команду
        //Ждем команду "start N C", , где N-количество карт, а C-номер игрока
        //Предполагается что команды приходят в необходимом формате
        if (l.equals("start")) {
            Shuffle(Deck); // перемешали колоду
            //System.out.println(Arrays.toString(Deck)); //для отладки вся колода
            N = Integer.parseInt(console.next()); // в N количество карт
            C = Integer.parseInt(console.next()); // в C количество игроков
            //по условию известно, что N и C целые, больше 0, поэтому не проверяем.
            //Проверим количество возможных наборов в раздаче
            if (N*C>Deck.length){
                System.out.println("Нехватает карт в колоде");
                break;
                          }
            //Eсли команда start верна,
            //заполняем список игроков, создаем С объектов класса Player
            //паралельно задаем порядковый номер и сдаем карты
            //первому игроку первые С карт, второму вторые С и т.д.
            String cards = "";  //здесь будем хранить строку с описанием сданных карт
            players.clear();    //сбросили ранние данные об игроках для новой раздачи
            for (int i = 0; i < C; i++) {
                for (int j = 0; j < N; j++){
                    cards = cards + " "+ Deck[N*i+j];
                }
                players.add(new Player(cards, i+1));
                cards = "";
               // System.out.println(players.get(i).IDnumber+players.get(i).cards); //для отладки все карты всех игроков
            }
        }
            /*Ждем команду "get-cards C", где C-номер игрока*/
            if (l.equals("get-cards")) {
                C1 = Integer.parseInt(console.next())-1;
                players.trimToSize();
                //Если ввели число большее чем количесво игроков выход с сообшением
                if (C1+1> players.size()){
                    System.out.println("Нет такого игрока");
                    break;
                }
                System.out.println(players.get(C1).IDnumber+players.get(C1).cards);}
        } while (!error);
    }
    private static void Shuffle (String [] deck){
        /*Перетасовка по методу Фишера-Йейтса */
        int Nsize = deck.length;
        Random rand = new Random();           //создали объект генератора случайных чисел
        for (int i = Nsize-1; i > 0; i--)  {  //для всех ячеек начиная с последней
          int randIndx = rand.nextInt(Nsize); //сгенерировали номер случайной ячейки массива
            String tmp = deck[randIndx];      //поменяли ячейки местами
            deck[randIndx] = deck[i];
            deck[i] = tmp;
        }
    }
}