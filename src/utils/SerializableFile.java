package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializableFile {

    // Serialização: gravando o objetos no arquivo binário "nameArq"
    public static void writeFileBinary(Object lista) {
        File arq = new File("./src/documents/invertedFile/file.dat");
        try {
            arq.delete();
            arq.createNewFile();

            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(lista);
            objOutput.close();

        } catch (IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }

    // Desserialização: recuperando os objetos gravados no arquivo binário "nomeArq"
    public static Object readFileBinary() {
        Object object = new Object();
        try {
            File arq = new File("./src/documents/invertedFile/file.dat");
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                object = (Object) objInput.readObject();
                objInput.close();
            }
        } catch (IOException erro1) {
            System.out.printf("Erro: %s", erro1.getMessage());
        } catch (ClassNotFoundException erro2) {
            System.out.printf("Erro: %s", erro2.getMessage());
        }

        return (object);
    }
}
