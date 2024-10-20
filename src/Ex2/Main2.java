package Ex2;

import java.util.Scanner;

abstract class Beverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() {
        System.out.println("Вода кипит.");
    }

    void pourInCup() {
        System.out.println("Напиток налит в чашку.");
    }

    boolean customerWantsCondiments() {
        return true;
    }
}

class Tea extends Beverage {
    @Override
    void brew() {
        System.out.println("Заваривание чая.");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавление лимона.");
    }
}

class Coffee extends Beverage {
    @Override
    void brew() {
        System.out.println("Заваривание кофе.");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавление молока и сахара.");
    }

    @Override
    boolean customerWantsCondiments() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Хотите добавить молоко и сахар в кофе? (да/нет): ");
        String answer = scanner.nextLine();
        return answer.equalsIgnoreCase("да");
    }
}

class HotChocolate extends Beverage {
    @Override
    void brew() {
        System.out.println("Приготовление горячего шоколада.");
    }

    @Override
    void addCondiments() {
        System.out.println("Добавление взбитых сливок.");
    }
}

public class Main2 {
    public static void main(String[] args) {
        Beverage tea = new Tea();
        Beverage coffee = new Coffee();
        Beverage hotChocolate = new HotChocolate();

        System.out.println("Приготовление чая:");
        tea.prepareRecipe();

        System.out.println("\nПриготовление кофе:");
        coffee.prepareRecipe();

        System.out.println("\nПриготовление горячего шоколада:");
        hotChocolate.prepareRecipe();
    }
}
