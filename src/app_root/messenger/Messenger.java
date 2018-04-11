package app_root.messenger;

import app_root.message.Message;

import java.io.IOException;
import java.util.List;

/**
 * Created by sergi on 11.04.2018.
 */
public interface Messenger {

    void store(Message message) throws IOException, ClassNotFoundException;

    List<Message> find(String name) throws IOException, ClassNotFoundException;
}
