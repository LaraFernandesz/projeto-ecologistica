package com.ecologistica.ui;

import java.util.Scanner;

public class ConsoleIO {

    private static final Scanner scanner = new Scanner(System.in);

    public static int readInt(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                erro("Digite um número válido.");
            }
        }
    }

    public static String readString(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static double readDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                erro("Digite um número decimal válido.");
            }
        }
    }

    public static void sucesso(String msg) {
        System.out.println("✔ " + msg);
    }

    public static void erro(String msg) {
        System.out.println("✖ " + msg);
    }

    public static void aviso(String msg) {
        System.out.println("⚠ " + msg);
    }
}