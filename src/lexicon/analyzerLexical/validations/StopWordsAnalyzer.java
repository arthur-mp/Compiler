package lexicon.analyzerLexical.validations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class StopWordsAnalyzer {
    List<String> listStopWords;

    public StopWordsAnalyzer() {
        setUpListStopWords();
    }

    public List<String> analyzer(List<String> wordsFile) {
        List<String> toRemoveList = new ArrayList<>();

        for (String word : wordsFile) {
            if (this.listStopWords.contains(word.toLowerCase())) {
                toRemoveList.add(word);
            }
        }
        ;

        wordsFile.removeAll(toRemoveList);

        return wordsFile;
    }

    private void setUpListStopWords() {
        try {
            File doc = new File("./document/stopWords.txt");

            byte[] fileContent = Files.readAllBytes(doc.toPath());

            String words = new String(fileContent, "UTF-8");

            String[] stopWords = words.split("\n");

            this.listStopWords = new ArrayList<>();

            for (int i = 0; i < stopWords.length; i++) {
                if (stopWords[i] != "")
                    this.listStopWords.add(stopWords[i].strip());
            }

        } catch (IOException e) {
            String error = format("Erro: %s", e.getMessage());
            System.out.println(error);
        }
    }

}
