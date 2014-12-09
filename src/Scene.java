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
    private String[] Bykvu = new String[33]; //Массив для введенных букв
    private String[] Zagadka;//Массив - Загаданное слово
    private String[] Otvet; //Массив Отгаданное
    private int petCount = 0;
    private int Verno=0;
    private boolean Win=false; //переменная означающая победу
    private boolean Povtor=false; // буква уже была названа
    private boolean CheckSlovo=false; //для проверки размера одной буквы
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
        repaint();
    }

    private int checkLetter(String One) {
        boolean Picture = false;
        Verno = Razm; //Стартовое значение
        if (One.length()>1){
            CheckSlovo=true;
            addError();
            return 1;
        }

        for (int i = 0; i < Bykvu.length; i++) { //Цикл на Проверку была ли буква названна до этого
            if (Bykvu[i].equals(One)) {
                Povtor=true;
                addError();
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
                            Picture=true;
                            addError();
                        }
                    }
                    if (Picture == false) {//буква не угадана
                        petCount++;
                        addError(); // буквы в слове нет рисуем виселицу
                        return 2;
                    }
                    //Сравнение ответа и Загадки
                    for (int j = 0; j < Razm; j++) {
                        if (Otvet[j].equals("*")) {
                        } else {
                            Verno--;// уменьшение количества неизвестных букв
                        }
                    }

                    if (Verno == 0) {
                        Win=true;
                        addError();
                        return 3;
                    }
                }
                Picture = false;
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
                int ok = scene.checkLetter(One);
                // проверить букву
                switch (ok) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        //System.exit(0);
                        break;
                }
            }
        });
        frame.add(scene, BorderLayout.CENTER);
        frame.add(input, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    protected void paintComponent(Graphics g) {
        //       super.paintComponent(g);
        int SdvigSlova=200;
        g.setColor(Color. BLACK);
        Font fontJust = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
        g.setFont(fontJust);
        for (int j = 0; j < Razm; j++) {
            g.drawString(Otvet[j],SdvigSlova, 370);
            SdvigSlova=SdvigSlova+40;
        }

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        if (petCount >= 1) {
            g.setColor(Color.BLACK);
            g.fillRect(50, 20, 10, 300);
        } //опорная стойка
        if (petCount >= 2) {
            g.setColor(Color.BLACK);
            g.fillRect(40, 20, 160, 10);
        } //вытянутая
        if (petCount >= 3) {
            g.setColor(Color.BLACK);
            g.fillRect(180, 20, 10, 90);
        } //петля
        if (petCount >= 4) {
            g.setColor(Color.BLACK);
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
            g.fillRect(186, 195, 10, 75);//правая нога

            g.setColor(Color. RED);//вы проиграли сообщение
            Font fontLose = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
            g.setFont(fontLose);
            g.drawString("ВЫ ПРОИГРАЛИ",150, 150);
        }
        if (CheckSlovo==true){
            CheckSlovo=false;
            g.setColor(Color. BLUE);//вы ввели букву большого размера))
            Font fontWin = new Font("Tahoma", Font.BOLD|Font.ITALIC, 30);
            g.setFont(fontWin);
            g.drawString("Ошибка! Введите букву",60, 50);
        }
        if (Povtor==true){ //сообщение о том что буква введена второй раз
            Povtor=false;
            g.setColor(Color. BLUE);//вы ввели букву второй раз
            Font fontWin = new Font("Tahoma", Font.BOLD|Font.ITALIC, 30);
            g.setFont(fontWin);
            g.drawString("Такая Буква Уже называлась",60, 50);
        }

        if (Win==true){
            g.setColor(Color. GREEN);//вы выйграли сообщение
            Font fontWin = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
            g.setFont(fontWin);
            g.drawString("ВЫ ВЫЙГРАЛИ",150, 150);
        }
    }
}