
package model;

import java.util.Date;

public class View {

    private Date date;
    private int view;

    public View() {
    }

    public View(Date date, int view) {
        this.date = date;
        this.view = view;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "View{" + "date=" + date + ", view=" + view + '}';
    }

    

}
