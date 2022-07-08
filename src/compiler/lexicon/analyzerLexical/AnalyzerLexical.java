package compiler.lexicon.analyzerLexical;

import java.util.ArrayList;
import java.util.List;

import compiler.lexicon.analyzerLexical.validations.*;
import compiler.lexicon.error.*;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class AnalyzerLexical {
    public List<String> listTokens;
    public List<String> listSimbols;
    public List<String> listKeyWords;

    public AnalyzerLexical() {
        this.listTokens = new ArrayList<>();
        this.listSimbols = new ArrayList<>();
        this.listKeyWords = new ArrayList<>();
    }

    public List<String> getListSimbols() {
        return this.listSimbols;
    }

    public void analyzer(List<Character> wordsFile) {
        try {
            List<String> words = verificationAlphabet(wordsFile);

            words = removeStopWords(words);

            words = verificationLexemas(words);

            for (int i = 0; i < words.size(); i++) {
                words.set(i, words.get(i).toLowerCase());
            }

            listTokens = words;

            getKeyWords();

            // createListSimbols();
            listSimbols = words;

        } catch (ErrorLexicon e) {

            String error = format(e.getMessage(), e.getWords());
            System.out.println(error);

        }
    }

    private List<String> verificationAlphabet(List<Character> wordsFile) {
        AlphabetAnalyzer alphabetAnalyzer = new AlphabetAnalyzer();

        return alphabetAnalyzer.analyzer(wordsFile);
    }

    private List<String> removeStopWords(List<String> wordsFile) {
        StopWordsAnalyzer stopWordsAnalyzer = new StopWordsAnalyzer();

        return stopWordsAnalyzer.analyzer(wordsFile);
    }

    private List<String> verificationLexemas(List<String> words) {
        LexemeAnalyzer lexemeAnalyzer = new LexemeAnalyzer();

        return lexemeAnalyzer.analyze(words);
    }

    private void createListSimbols() {
        for (int i = 0; i < this.listTokens.size(); i++) {
            if (!verificationKeyWord(this.listTokens.get(i).toLowerCase()))
                this.listSimbols.add(this.listTokens.get(i));
        }
    }

    private void getKeyWords() {
        try {
            File doc = new File("src/documents/words/key.words.txt");

            byte[] fileContent = Files.readAllBytes(doc.toPath());

            String words = new String(fileContent, "UTF-8");

            String[] keyWords = words.split("\n");

            for (int i = 0; i < keyWords.length; i++) {
                if (!keyWords[i].equals("\r"))
                    this.listKeyWords.add(keyWords[i].strip());
            }

        } catch (IOException e) {
            String error = format("Erro: %s", e.getMessage());
            System.out.println(error);
        }
    }

    private boolean verificationKeyWord(String word) {
        return this.listKeyWords.contains(word);
    }

    public void printLists() {
        System.out.println("*************************************");
        System.out.println();
        System.out.println("Lista de Tokens:");
        System.out.println(this.listTokens.toString());
        System.out.println();
        System.out.println("Lista de Simbolos:");
        System.out.println(this.listSimbols.toString());
        System.out.println("*************************************");
    }
}
