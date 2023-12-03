/**
 * Создать свой Java Maven/Gradle проект;
 * Реализовать прикладную задачу - приложение для демонстрации парадокса Монти Холла;
 * Можно добавить любые библиотеки которые считают необходимыми
 * Результаты должны быть сохранены в HashMap (шаг цикла -> результат)
 * Необходимо вывести статистику по результату - количество позитивных и негативных результатов,
 * процент от общего количества шагов цикла.
 */

package org.example;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        int[] doors = new int[3];  //Массив дверей
        //0 - за дверью баран
        //1 - за дверью автомобиль
        int choicer; //Выбор игрока
        int computer; //Выбор двери компьютером
        int yesNo; // 0 - NO, 1 - YES - Поменять выбор игроку или нет
        Random rand = new Random();

        Multimap<Integer,Integer> winLooseHashMap = HashMultimap.create();
        //Ключ - номер попытки, значение - 0 (проигрыш) или 1 (победа)

        for (int count = 0; count < 1000; count++) {
            for (int i = 0; i < 3; i++) {
                doors[i] = 0;
            }
            doors[rand.nextInt(0, 3)] = 1; //Определили дверь, за которой автомобиль
//            System.out.println(Arrays.toString(doors));

            choicer = rand.nextInt(0, 3); //Выбор игрока
//            System.out.println("You choose a door number " + choicer);

            computer = choicer; //Выбор двери компьютером
            while (computer == choicer) {
                computer = rand.nextInt(0, 3);
                if (doors[computer] == 1) {
                    computer = choicer;
                }
            }
//            System.out.println("The bad door is number " + computer);
//            System.out.println("Do you want to make a new choice?");

            yesNo = rand.nextInt(0, 2); // 0 - NO, 1 - YES
//            System.out.println("YES or NO: " + yesNo);
            if (yesNo == 0) {
                if (doors[choicer] == 1) {
//                    System.out.println("CONGRATULATION! You won the car!");
                    winLooseHashMap.put(count, 1);
                } else {
//                    System.out.println("SORRY! You won a ram!");
                    winLooseHashMap.put(count, 0);
                }
            } else {
                if (choicer == 0) {
                    if (computer == 1) {
                        if (doors[2] == 1) {
//                            System.out.println("CONGRATULATION! You won the car!");
                            winLooseHashMap.put(count, 1);
                        } else {
//                            System.out.println("SORRY! You won a ram!");
                            winLooseHashMap.put(count, 0);
                        }
                    } else {
                        if (doors[1] == 1) {
//                            System.out.println("CONGRATULATION! You won the car!");
                            winLooseHashMap.put(count, 1);
                        } else {
//                            System.out.println("SORRY! You won a ram!");
                            winLooseHashMap.put(count, 0);
                        }
                    }
                } else {
                    if (choicer == 1) {
                        if (computer == 0) {
                            if (doors[2] == 1) {
//                                System.out.println("CONGRATULATION! You won the car!");
                                winLooseHashMap.put(count, 1);
                            } else {
//                                System.out.println("SORRY! You won a ram!");
                                winLooseHashMap.put(count, 0);
                            }
                        } else {
                            if (doors[0] == 1) {
//                                System.out.println("CONGRATULATION! You won the car!");
                                winLooseHashMap.put(count, 1);
                            } else {
//                                System.out.println("SORRY! You won a ram!");
                                winLooseHashMap.put(count, 0);
                            }
                        }

                    } else {
                        if (computer == 0) {
                            if (doors[1] == 1) {
//                                System.out.println("CONGRATULATION! You won the car!");
                                winLooseHashMap.put(count, 1);
                            } else {
//                                System.out.println("SORRY! You won a ram!");
                                winLooseHashMap.put(count, 0);
                            }
                        } else {
                            if (doors[0] == 1) {
//                                System.out.println("CONGRATULATION! You won the car!");
                                winLooseHashMap.put(count, 1);
                            } else {
//                                System.out.println("SORRY! You won a ram!");
                                winLooseHashMap.put(count, 0);
                            }
                        }
                    }
                }
            }
        }

        long countWin = winLooseHashMap.values().stream().filter(k -> k == 1).count();
        long countLoose = winLooseHashMap.values().stream().filter(k -> k == 0).count();

        System.out.println("Количество побед для 1000 попыток: " + countWin + " (" + ((double)countWin/1000)*100 + "%)");
        System.out.println("Количество поражений для 1000 попыток: " + countLoose + " (" + ((double)countLoose/1000)*100 + "%)");
    }
}