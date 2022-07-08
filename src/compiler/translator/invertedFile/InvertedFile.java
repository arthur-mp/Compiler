package compiler.translator.invertedFile;

import java.util.Map;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvertedFile implements Serializable {
    private Map<String, List<WordMapping>> invertedDocument;

    public InvertedFile() {
        invertedDocument = new HashMap<>();
    }

    public List<WordMapping> getListMap(String word) {
        return invertedDocument.get(word);
    }

    public void setMap(String word, int file) {
        if (invertedDocument.containsKey(word)) {
            List<WordMapping> currentListWord = invertedDocument.get(word);

            WordMapping wordMap = containsFile(file, currentListWord);

            if (wordMap != null) {
                int index = currentListWord.indexOf(wordMap);

                if (index != -1)
                    currentListWord.get(index).addAmount();
            } else {
                WordMapping newWordMap = new WordMapping(file);
                currentListWord.add(newWordMap);
            }

            invertedDocument.put(word, currentListWord);

        } else {
            WordMapping wordMapping = new WordMapping(file);

            List<WordMapping> newListWord = new ArrayList<WordMapping>();
            newListWord.add(wordMapping);

            invertedDocument.put(word, newListWord);
        }
    }

    private WordMapping containsFile(int file, List<WordMapping> currentListWord) {
        for (WordMapping wordMap : currentListWord) {
            if (wordMap.getFile() == file) {
                return wordMap;
            }
        }

        return null;
    }

}
