package services.datastructure;

import java.util.Date;

public class EventData {
    private String title;
    private Date date;



    public EventData(String title, long date) {
        this.title = title;
        this.date = new Date(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
