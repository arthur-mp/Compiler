package lexicon.analyzerLexical;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import lexicon.analyzerLexical.validations.*;
import lexicon.error.*;

public class AnalyzerLexical {
    public List<String> listTokens;
    public List<String> listSimbols;
    public List<String> listKeyWords;

    public AnalyzerLexical() {
        this.listTokens = new ArrayList<>();
        this.listSimbols = new ArrayList<>();
        this.listKeyWords = new ArrayList<>();
    }

    public void analyzer(List<Character> wordsFile) {
        try {
            List<String> words = verificationLexical(wordsFile);

            words = removeStopWords(words);

            words = verificationLexemas(words);

            listTokens = words;

            setUpListKeyWords();

            createListSimbols();

        } catch (ErrorLexicon e) {

            String error = format(e.getMessage(), e.getWords());
            System.out.println(error);

        }
    }

    private List<String> verificationLexical(List<Character> wordsFile) {
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
            if (!verificationKeyWord(this.listTokens.get(i)))
                this.listSimbols.add(this.listTokens.get(i));
        }
    }

    private void setUpListKeyWords() {
        try {
            File doc = new File("./document/keyWords.txt");

            byte[] fileContent = Files.readAllBytes(doc.toPath());

            String words = new String(fileContent, "UTF-8");

            String[] stopWords = words.split("\n");

            for (int i = 0; i < stopWords.length; i++) {
                if (!stopWords[i].equals("\r"))
                    this.listKeyWords.add(stopWords[i].strip());
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
