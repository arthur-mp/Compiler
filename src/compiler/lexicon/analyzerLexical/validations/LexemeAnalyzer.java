package compiler.lexicon.analyzerLexical.validations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class LexemeAnalyzer {

    private Map<String, String> listLexemas;

    public LexemeAnalyzer() {
        setUpTableLexemas();
    }

    public List<String> analyze(List<String> words) {

        for (int i = 0; i < words.size(); i++) {
            String newWord = validKeyWordsLexeme(words.get(i).toLowerCase());

            if (newWord != null) {
                words.set(i, newWord);
            }

        }

        return words;
    }

    private String validKeyWordsLexeme(String word) {

        return this.listLexemas.get(word);
    }

    private void setUpTableLexemas() {
        try {
            File doc = new File("src/documents/words/lexemas.words.txt");

            byte[] fileContent = Files.readAllBytes(doc.toPath());

            String words = new String(fileContent, "UTF-8");

            String[] lexemas = words.split("\n");

            this.listLexemas = new HashMap<String, String>();

            String[] lexema;

            for (int i = 0; i < lexemas.length; i++) {
                if (!lexemas[i].equals("\r")) {
                    lexema = lexemas[i].split(";");
                    this.listLexemas.put(lexema[0].strip(), lexema[1].strip());
                }
            }

        } catch (IOException e) {
            String error = format("Erro: %s", e.getMessage());
            System.out.println(error);
        }
    }
}
