
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import compiler.lexicon.analyzerLexical.AnalyzerLexical;
import compiler.translator.Translator;

public class App {
    public static void main(String[] args) {
        Scanner textScanner = new Scanner(System.in, "CP850");

        greetings();

        String words = textScanner.nextLine();

        List<Character> wordList = words.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

        AnalyzerLexical analyzerLexical = new AnalyzerLexical();

        analyzerLexical.analyzer(wordList);


        Translator translator = new Translator();

        translator.getAnswers(analyzerLexical.getListSimbols());

    }

    public static void greetings() {
        System.out.println("********** Chat Bot Acer **********");
        System.out.println();
        System.out.println("Desenvolvido por: Arthur Morais Pimentel");
        System.out.println();
        System.out.println("Digite o que desejar, dado o cenário de  uma empresa de tecnologia, Acer!");
        System.out.println();
        System.out.println("*************************************");
        System.out.println();
    }

}
