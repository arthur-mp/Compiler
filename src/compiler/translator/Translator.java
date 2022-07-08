package compiler.translator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import compiler.lexicon.analyzerLexical.AnalyzerLexical;
import compiler.translator.invertedFile.InvertedFile;
import compiler.translator.invertedFile.WordMapping;
import utils.SerializableFile;

public class Translator {
    InvertedFile invertedFile;
    Map<Integer, Integer> fileMapping;

    public Translator() {
        this.invertedFile = new InvertedFile();
        this.fileMapping = new HashMap<>();
    }

    /*
     * 
     * @param input user
     */
    public void getAnswers(List<String> listSimbols) {
        this.createInvertedFile();
        int file = this.mappingWord(listSimbols);

        this.readAnswer(file);
    }

    private void createInvertedFile() {

        if (!existsInvertedFile()) {
            FileReader answer = null;
            BufferedReader reader = null;
            AnalyzerLexical analyzerLexical;
            List<String> listSimbols;
            int numberFiler = 1;

            while (true) {
                try {
                    answer = new FileReader("./src/documents/answers/" + numberFiler + ".txt");
                } catch (FileNotFoundException e) {
                    break;
                }

                reader = new BufferedReader(answer);

                try {
                    String words = reader.readLine();
                    List<Character> wordList = words.chars().mapToObj(e -> (char) e).collect(Collectors.toList());

                    analyzerLexical = new AnalyzerLexical();
                    analyzerLexical.analyzer(wordList);

                    listSimbols = new ArrayList<>(analyzerLexical.getListSimbols());

                    this.addWord(listSimbols, numberFiler);

                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }

                numberFiler++;
            }
            this.createSerializableFile();
        } else {
            this.readeSerializableFile();
        }
    }

    private boolean existsInvertedFile() {

        try {
            FileReader file = new FileReader("./src/documents/invertedFile/file.dat");
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    private void addWord(List<String> listSimbols, int file) {

        listSimbols.forEach(word -> this.invertedFile.setMap(word, file));

    }

    private void createSerializableFile() {
        SerializableFile.writeFileBinary(this.invertedFile);
    }

    private void readeSerializableFile() {
        this.invertedFile = (InvertedFile) SerializableFile.readFileBinary();
    }

    private int mappingWord(List<String> listSimbols) {

        List<WordMapping> file;

        for (String simbol : listSimbols) {
            file = this.invertedFile.getListMap(simbol);

            if (file != null) {
                for (WordMapping word : file) {
                    setFileMapping(word.getFile(), word.getAmount());
                }
            }
        }

        return getFileMapping();
    }

    private void setFileMapping(int file, int weight) {
        int oldValue = 0;
        if (this.fileMapping.containsKey(file)) {
            oldValue = this.fileMapping.get(file);
        }
        if (oldValue != 0) {
            this.fileMapping.put(file, oldValue + weight);
        } else {
            this.fileMapping.put(file, weight);
        }

    }

    private int getFileMapping(){
         int file = 0;
         int amount = 0;

        for (Map.Entry<Integer, Integer> element : this.fileMapping.entrySet()) {
            if(element.getValue() > amount){
                amount = element.getValue();
                file = element.getKey();
            }
        }

        return file;
    }

    private void readAnswer(int file){
        try{
            FileReader answer = new FileReader("./src/documents/answers/" + file + ".txt");
            BufferedReader reader = new BufferedReader(answer);

            try{
             String readAnswer = reader.readLine();
             System.out.println();
             System.out.println(readAnswer);

            }catch(Exception e){
                System.out.println("Error: " + e);
            }

        }catch(FileNotFoundException e){
            System.out.println("Erro ao entregar a resposta!");
        }

    }


}
