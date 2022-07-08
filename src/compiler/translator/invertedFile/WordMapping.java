package compiler.translator.invertedFile;

import java.io.Serializable;

public class WordMapping implements Serializable {
    private int file;
    private int amount;

    public WordMapping(int file) {
        this.file = file;
        this.amount = 1;
    }

    public int getFile() {
        return this.file;
    }

    public int getAmount() {
        return this.amount;
    }

    public void addAmount() {
        this.amount += 1;
    }

}
