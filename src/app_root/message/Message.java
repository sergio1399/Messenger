package app_root.message;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sergi on 10.04.2018.
 */
public class Message implements Serializable {
    private String name;
    private String text;
    private Date dt;

    public Message() {
    }

    public Message(String name, String text, Date dt) {
        this.name = name;
        this.text = text;
        this.dt = dt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }
}
