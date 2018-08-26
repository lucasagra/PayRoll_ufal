import control.Control;
import data.Data;

import java.io.*;

public class Main {

    private static void save(Data data) {
        try {
            FileOutputStream fos = new FileOutputStream("data.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Data load(Data data) {
        try {
            FileInputStream fis = new FileInputStream("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (Data) ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            System.out.println("File doesn't found, creating a new data file.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.getCause();
            e.printStackTrace();
        }

        return data;
    }

    public static void main(String[] args) {
        Data data = load(new Data());
        new Control().main(data);
        save(data);
    }
}
