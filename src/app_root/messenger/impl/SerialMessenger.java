package app_root.messenger.impl;

import app_root.message.Message;
import app_root.messenger.Messenger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sergi on 10.04.2018.
 */
public class SerialMessenger implements Messenger {

    private String fileName;

    public SerialMessenger(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void store(Message message) throws IOException, ClassNotFoundException {
        List<Message> list = new ArrayList<>();
        //проверяем есть ли уже файл и читаем из него
        File file = new File(fileName);
        if(file.exists()) {
            FileInputStream fiStream = new FileInputStream(fileName);
            ObjectInputStream objectStream = new ObjectInputStream(fiStream);
            Object object = objectStream.readObject();
            list = (ArrayList<Message>) object;
            fiStream.close();
            objectStream.close();
        }
        list.add(message);
        FileOutputStream fileOutput = new FileOutputStream(fileName);
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);
        outputStream.writeObject(list);
        fileOutput.close();
        outputStream.close();
    }

    public List<Message> find(String name) throws IOException, ClassNotFoundException {

        List<Message> list = new ArrayList<>();
        FileInputStream fiStream = new FileInputStream(fileName);
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);
        Object object = objectStream.readObject();
        list = (ArrayList<Message>) object;
        fiStream.close();
        objectStream.close();
        return list.stream().filter((m)-> m.getName().equals(name)).collect(Collectors.toList());

    }
}
