package app_root;

import app_root.message.Message;
import app_root.messenger.Messenger;
import app_root.messenger.impl.SerialMessenger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sergi on 10.04.2018.
 */
public class Application {

    public static void main(String[] args) {
        Messenger messenger;
        //файл сериализации сообщений может задаваться аргументом командной строки
        if(args.length == 1)
            messenger = new SerialMessenger(args[0]);
        else
        //если нет то используется предопределенный
            messenger = new SerialMessenger("msg.dat");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Добро пожаловать в Мессенджер!");
            int choice = 0;
            CYCLE:
            while (true)
            {
                System.out.println("Выберите действие:");
                System.out.println("1 - Добавить сообщение.");
                System.out.println("2 - Найти сообщения.");
                System.out.println("3 - Выход.");
                try {
                    choice = Integer.valueOf(reader.readLine());
                }
                catch (NumberFormatException e){
                    System.out.println("Неправильный формат ввода.");
                    continue;
                }

                switch (choice){
                    case 1:
                        makeMessage(messenger, reader);
                        break;
                    case 2:
                        viewMessages(messenger, reader);
                        break;
                    case 3:
                        break CYCLE;
                    default:
                        System.out.println("Неопределенный выбор.");
                        break;
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Файл не найден.");
        }
        catch (ClassNotFoundException e){
            System.out.println("Ошибка формата данных.");
        }
        catch (IOException e) {
            System.out.println("Ошибка ввода/вывода.");
        }

    }

    public static void makeMessage(Messenger messenger, BufferedReader reader) throws IOException, ClassNotFoundException {
        System.out.println("Введите имя адресата:");
        String name = reader.readLine();
        System.out.println("Введите текст сообщения:");
        String text = reader.readLine();
        messenger.store(new Message(name, text, new Date()));
    }

    public static void viewMessages(Messenger messenger, BufferedReader reader) throws IOException, ClassNotFoundException{
        System.out.println("Введите имя для поиска:");
        String name = reader.readLine();
        List<Message> list = messenger.find(name);
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        System.out.printf("Сообщения для %s:\n", name);
        for (Message message: list) {
            System.out.println("Дата: " + formatter.format(message.getDt()));
            System.out.println(message.getText());
            System.out.println();
        }
    }

}
