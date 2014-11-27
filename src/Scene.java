/**
 * Created by Devil Trigger S on 25.11.2014.
 */

package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Scene extends JComponent {
    public Scene() {
        setPreferredSize(new Dimension(600, 400));
    }

    public static void main(String[] args) {


        int Razm = 0; // Размер загаданного слова
        int Schet = 0; // счетчик
        int Verno = Razm;
        int Picture = 0;
        String[] Bykvu = new String[10]; //Массив для введенных букв
        for (int i = 0; i < Bykvu.length; i++) Bykvu[i] = "*";

        String[] Naborslov = {"слон", "молоко", "пчела", "птичка", "собака"};//Массив слов на Отгадывание
        int Nomer = new Random().nextInt(4) + 1; // Выбираем слово
        String NewZagad = Naborslov[Nomer];
        System.out.println(NewZagad); //помещаем в переменную
        Razm=NewZagad.length();
        System.out.println(Razm);

        String[] Zagadka = new String[Razm];//Массив - Загаданное слово
        for (int i = 0; i < Zagadka.length; i++){
            char a=NewZagad.charAt(i);
            String NotChar = Character.toString(a);
            Zagadka[i]=NotChar;
        }

        String[] Otvet = new String[Razm]; //Массив Отгаданное
        for (int i = 0; i < Otvet.length; i++) Otvet[i] = "*";

        while (true) {

            Verno = Razm; //Стартовое значение

            System.out.println(" ");
            System.out.println("Введите Букву");
            Scanner in = new Scanner(System.in);//Ввод бкувы
            String One = in.nextLine();

            for (int i = 0; i < Bykvu.length; i++) { //Цикл на Проверку была ли буква названна до этого
                if (Bykvu[i].equals(One)) {
                    System.out.println("Такая Буква Уже называлась");
                    break;
                } else {
                    if (i == Bykvu.length - 1) {
                        //Занесение буквы и увелечение счетчика
                        Bykvu[Schet] = One;
                        Schet++;

                        //Работа со словом
                        for (int j = 0; j < Razm; j++) {
                            if (Zagadka[j].equals(One)) {
                                Otvet[j] = One;
                                Picture++;
                            }
                        }
                        if (Picture == 0) {
                            //рисуем Виселицу
                            JFrame frame = new JFrame("Виселица");
                            Scene scene = new Scene();
                            frame.add(scene);
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                        //Сравнение ответа и Загадки
                        for (int j = 0; j < Razm; j++) {
                            if (Otvet[j].equals("*")) {
                            } else {
                                Verno--;
                            }
                        }

                        for (int j = 0; j < Razm; j++)
                            System.out.print(Otvet[j]);

                        if (Verno == 0) {
                            System.out.println();
                            System.out.println("Вы Угадали");
                            System.exit(0);
                        }
                    }
                    Picture = 0;
                }
            }
        }
   }

    public void paint(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(50, 20, 10, 300); //опорная стойка
        g.setColor(Color.red);
        g.fillRect(40, 20, 160, 10); //вытянутая
        g.setColor(Color.red);
        g.fillRect(180, 20, 10, 110); //петля
        g.setColor(Color.red);
        g.fillRect(40, 320, 30, 10); //туловище
        //человечек
        g.setColor(Color.blue);
        g.fillOval(160, 75, 50, 50); //голова
        g.setColor(Color.blue);
        g.fillRect(180, 125, 10, 75); //туловище
        g.setColor(Color.blue);
        g.fillRect(147, 130, 75, 10);// руки
        g.setColor(Color.blue);
        g.fillRect(172, 195, 10, 75); //левая нога
        g.setColor(Color.blue);
        g.fillRect(186, 195, 10, 75); //правая нога
    }
 /*   protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;


        g.setColor(Color.red);
        g.fillRect(50, 20, 10, 300); //опорная стойка
        g.setColor(Color.red);
        g.fillRect(40, 20, 160, 10); //вытянутая
        g.setColor(Color.red);
        g.fillRect(180, 20, 10, 110); //петля
        g.setColor(Color.red);
        g.fillRect(40, 320, 30, 10); //туловище
        //человечек
        g.setColor(Color.blue);
        g.fillOval(160, 75, 50, 50); //голова
        g.setColor(Color.blue);
        g.fillRect(180, 125, 10, 75); //туловище
        g.setColor(Color.blue);
        g.fillRect(147, 130, 75, 10);// руки
        g.setColor(Color.blue);
        g.fillRect(172, 195, 10, 75); //левая нога
        g.setColor(Color.blue);
        g.fillRect(186, 195, 10, 75); //правая нога
    }*/
}