package compiler.lexicon.analyzerLexical.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import compiler.lexicon.error.*;

public class AlphabetAnalyzer {

    public List<String> analyzer(List<Character> wordsFile) {
        List<String> words = new ArrayList<>();

        StringBuilder word = new StringBuilder();

        for (int i = 0; i < wordsFile.size(); i++) {

            if (validSimbols(wordsFile.get(i))) {

                if (!word.isEmpty())
                    words.add(word.toString());

                word.setLength(0);

            } else {
                word.append(wordsFile.get(i));

                if ((i + 1) == wordsFile.size())
                    words.add(word.toString());
            }

        }

        List<String> invalidWords = validAlphabet(words);

        if (!invalidWords.isEmpty()) {
            throw new ErrorLexicon(invalidWords, ErrorTypes.ALPHABET_ERROR);
        }
        return words;
    }

    private List<String> validAlphabet(List<String> words) {
        List<String> invalidWords = new ArrayList<>();

        words.forEach(word -> {

            List<Character> charlist = word.chars().mapToObj(letter -> (char) letter).collect(Collectors.toList());

            for (int i = 0; i < charlist.size(); i++) {
                if (!validCharacter(charlist.get(i))) {
                    invalidWords.add(word);
                    break;
                }
            }
        });

        return invalidWords;
    }

    private boolean validCharacter(char character) {

        if ((32 <= character && character <= 126) ||
                (192 <= character && character <= 207) ||
                (210 <= character && character <= 214) ||
                (217 <= character && character <= 220) ||
                (224 <= character && character <= 239) ||
                (242 <= character && character <= 246) ||
                (249 <= character && character <= 252) ||
                (character == 176))
            return true;

        return false;

    }

    private boolean validSimbols(char simbol) {

        if ((simbol == 10) ||
                (simbol == 13) ||
                (simbol == 32) ||
                (simbol == 33) ||
                (simbol == 44) ||
                (simbol == 46) ||
                (simbol == 63))
            return true;

        return false;
    }
}
