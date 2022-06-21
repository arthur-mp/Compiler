package compiler.lexicon.error;

import java.util.List;

public class ErrorLexicon extends RuntimeException {

    private List<String> words;

    public ErrorLexicon(List<String> words, String message) {
        super(message);
        this.words = words;
    }

    public String getWords() {
        return words.toString();
    }
}
