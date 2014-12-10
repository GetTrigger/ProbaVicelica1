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
    private String Bykvu="";//Массив для введенных букв
    private char[] Zagadka;//Массив - Загаданное слово
    private char[] Otvet;//Массив Отгаданное
    private int petCount = 0;
    private int Verno=0;
    private boolean Win=false; //переменная означающая победу
    private boolean Povtor=false; // буква уже была названа
    private boolean CheckSlovo=false; //для проверки размера одной буквы
    private boolean Picture=false;
    public Scene() {
        setPreferredSize(new Dimension(600, 400));

        String[] Naborslov = {"слон", "молоко", "пчела", "птичка", "собака"};//Массив слов на Отгадывание
        int Nomer = new Random().nextInt(5); // Выбираем слово
        String NewZagad = Naborslov[Nomer];
        System.out.println(NewZagad); //помещаем в переменную
        Razm = NewZagad.length();

        Zagadka = new char[Razm];//Массив - Загаданное слово
        Otvet = new char[Razm];//Массив Отгаданное
        System.out.println(Razm);

        for (int i = 0; i < Zagadka.length; i++) {
            char a = NewZagad.charAt(i);
            Zagadka[i] = a;
        }
        String Zvezda = "*";
        char CharZv = Zvezda.charAt(0);
        for (int i = 0; i < Otvet.length; i++) Otvet[i] =CharZv;
    }

    private void addError() {
        repaint();
    }

    private void checkLetter(String One) {
        Verno = Razm; //Стартовое значение
        if (One.length()>1){ // проверка на ввод 1 буквы
            CheckSlovo=true;
            addError();
        }else{

            if (Bykvu.contains(One)) {//Цикл на Проверку была ли буква названна до этого введена буква
                Povtor=true;
                addError();
            } else {
                    //Занесение буквы и увелечение счетчика
                    Bykvu += One;

                    //Работа со словом
                char OneChar = One.charAt(0);
                    for (int j = 0; j < Razm; j++) {
                        if (Zagadka[j]==(OneChar)) {
                            Otvet[j] = OneChar;
                            Picture=true;
                            addError();
                        }
                    }
                    if (Picture == false) {//буква не угадана
                        petCount++;
                        addError(); // буквы в слове нет рисуем виселицу
                    }
                    //Сравнение ответа и Загадки
                String Zvezda = "*";
                char CharZv = Zvezda.charAt(0);
                    for (int j = 0; j < Razm; j++) {
                        if (Otvet[j]==(CharZv)) {
                        } else {
                            Verno--;// уменьшение количества неизвестных букв
                        }
                    }

                    if (Verno == 0) {
                        Win=true;
                        addError();
                    }
            }
        }
    }

    public static void main(String[] args) {
        //рисуем Виселицу
        JFrame frame = new JFrame("Виселица");
        final Scene scene = new Scene();
        final JTextField input = new JTextField();
        input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                scene.CheckSlovo=false;
                scene.Picture=false;
                scene.Povtor=false;
                String One = input.getText();
                scene.checkLetter(One);
                if (scene.petCount >= 9) {
                    input.setVisible(false);
                }
                if (scene.Win==true){
                    input.setVisible(false);
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
        int SdvigSlova=200;
        g.setColor(Color. BLACK);
        Font fontJust = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
        g.setFont(fontJust);
        for (int j = 0; j < Razm; j++) {
        String displayString = new String(Otvet); // преобразование char[] в String
        g.drawString(displayString, 200, 370);
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
            g.setColor(Color. BLUE);//вы ввели букву большого размера))
            Font fontWin = new Font("Tahoma", Font.BOLD|Font.ITALIC, 30);
            g.setFont(fontWin);
            g.drawString("Ошибка! Введите букву",60, 50);
        }
        if (Povtor==true){ //сообщение о том что буква введена второй раз
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