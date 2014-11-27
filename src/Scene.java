/**
 * Created by Devil Trigger S on 25.11.2014.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Scene extends JComponent {

    private int Razm; // Размер загаданного слова
    private int Schet = 0; // счетчик
    private String[] Bykvu = new String[10]; //Массив для введенных букв
    private String[] Zagadka;//Массив - Загаданное слово
    private String[] Otvet; //Массив Отгаданное
    private int petCount = 0;

    public Scene() {
        setPreferredSize(new Dimension(600, 400));

        for (int i = 0; i < Bykvu.length; i++) Bykvu[i] = "*";

        String[] Naborslov = {"слон", "молоко", "пчела", "птичка", "собака"};//Массив слов на Отгадывание
        int Nomer = new Random().nextInt(4) + 1; // Выбираем слово
        String NewZagad = Naborslov[Nomer];
        System.out.println(NewZagad); //помещаем в переменную
        Razm = NewZagad.length();

        Zagadka = new String[Razm];//Массив - Загаданное слово
        Otvet = new String[Razm]; //Массив Отгаданное
        System.out.println(Razm);

        for (int i = 0; i < Zagadka.length; i++) {
            char a = NewZagad.charAt(i);
            String NotChar = Character.toString(a);
            Zagadka[i] = NotChar;
        }

        for (int i = 0; i < Otvet.length; i++) Otvet[i] = "*";
    }

    private void addError() {
        petCount++;
        repaint();
    }

    private int checkLetter(String One) {
        int Picture = 0;
        int Verno = Razm; //Стартовое значение
        for (int i = 0; i < Bykvu.length; i++) { //Цикл на Проверку была ли буква названна до этого
            if (Bykvu[i].equals(One)) {
                return 1;
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
                        addError(); // 2
                        return 2;
                    }
                    //Сравнение ответа и Загадки
                    for (int j = 0; j < Razm; j++) {
                        if (Otvet[j].equals("*")) {
                        } else {
                            Verno--;
                        }
                    }

                    for (int j = 0; j < Razm; j++) {
                        System.out.print(Otvet[j]); // 3
                    }
                    System.out.println();

                    if (Verno == 0) {
                        return 4;
                    } else {
                        return 3;
                    }
                }
                Picture = 0;
            }
        }
        return 0;
    }

    public static void main(String[] args) {

        //рисуем Виселицу
        JFrame frame = new JFrame("Виселица");
        final Scene scene = new Scene();
        final JTextField input = new JTextField();
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String One = input.getText();
                System.out.println("Text input: " + One);
                int ok = scene.checkLetter(One);
                // проверить букву
                switch (ok) {
                    case 1:
                        System.out.println("Такая Буква Уже называлась"); // todo
                        break;
                    case 2:
                        break;
                    case 3:
                        // todo: вывести текст в окне вместо консоли
                        break;
                    case 4:
                        System.out.println();
                        System.out.println("Вы Угадали"); // todo
                        System.exit(0);
                        break;

                }
            }
        });
        frame.add(scene, BorderLayout.CENTER);
        frame.add(input, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

//        while (true) {
//
//            System.out.println(" ");
//            System.out.println("Введите Букву");
//            Scanner in = new Scanner(System.in);//Ввод бкувы
//            String One = in.nextLine();
//
//            int ok = scene.checkLetter(One);
//            switch (ok) {
//                case 1:
//                    System.out.println("Такая Буква Уже называлась");
//                    break;
//                case 2:
//                    break;
//                case 3:
//                    // todo: вывести текст в окне вместо консоли
//                    break;
//                case 4:
//                    System.out.println();
//                    System.out.println("Вы Угадали");
//                    System.exit(0);
//                    break;
//
//            }
//        }
    }


    protected void paintComponent(Graphics g) {
        //       super.paintComponent(g);
        if (petCount == 0)
            return;
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        if (petCount >= 1) {
            g.setColor(Color.red);
            g.fillRect(50, 20, 10, 300);
        } //опорная стойка
        if (petCount >= 2) {
            g.setColor(Color.red);
            g.fillRect(40, 20, 160, 10);
        } //вытянутая
        if (petCount >= 3) {
            g.setColor(Color.red);
            g.fillRect(180, 20, 10, 110);
        } //петля
        if (petCount >= 4) {
            g.setColor(Color.red);
            g.fillRect(40, 320, 30, 10);
        }//туловище
        //человечек
        if (petCount >= 5) {
            g.setColor(Color.blue);
            g.fillOval(160, 75, 50, 50);
        } //голова
        if (petCount >= 6) {
            g.setColor(Color.blue);
            g.fillRect(180, 125, 10, 75);
        } //туловище
        if (petCount >= 7) {
            g.setColor(Color.blue);
            g.fillRect(147, 130, 75, 10);
        }// руки
        if (petCount >= 8) {
            g.setColor(Color.blue);
            g.fillRect(172, 195, 10, 75);
        } //левая нога
        if (petCount >= 9) {
            g.setColor(Color.blue);
            g.fillRect(186, 195, 10, 75);
        } //правая нога
    }
}