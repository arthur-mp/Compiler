package lexicon;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import lexicon.analyzerLexical.AnalyzerLexical;

public class App {
    public static void main(String[] args) {
        Scanner intScanner = new Scanner(System.in);
        Scanner textScanner = new Scanner(System.in, "CP850");

        print();

        int option = 0;

        while (option != 3) {
            option = intScanner.nextInt();

            switch (option) {
                case 1:
                    String words = textScanner.nextLine();

                    List<Character> wordList = words.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

                    AnalyzerLexical analyzerLexical = new AnalyzerLexical();

                    analyzerLexical.analyzer(wordList);

                    analyzerLexical.printLists();

                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException ex) {
                    }

                    print();

                    break;

                case 2:

                    help();

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                    }

                    print();

                    break;

                case 3:

                    bye();

                    break;
            }
        }

    }

    public static void print() {
        System.out.println("********** Chat Bot Acer **********");
        System.out.println();
        System.out.println("Opções:");
        System.out.println("(1) Digitar");
        System.out.println("(2) Ajuda");
        System.out.println("(3) Sair");
        System.out.println("*************************************");
        System.out.println();
    }

    public static void help() {
        System.out.println("*************************************");
        System.out.println();
        System.out.println("Modelos de informações fornecidas pelo usuário:");
        System.out.println("=> Meu monitor está piscando!");
        System.out.println("=> Meu notebook é gamer");
        System.out.println();
        System.out.println("Modelos de perguntas feitas pelo usuário:");
        System.out.println("=> Como instalar o drive de áudio no notebook gamer?");
        System.out.println("=> Qual o valor de um notebook profissional?");
        System.out.println();
        System.out.println("Espero ter ajudado!");
        System.out.println("*************************************");
        System.out.println();
    }

    public static void bye() {
        System.out.println("*************************************");
        System.out.println();
        System.out.println("Até a próxima!");
        System.out.println();
        System.out.println("*************************************");
        System.out.println();
    }

}
