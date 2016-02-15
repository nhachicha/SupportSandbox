package realm.io.realmsupportsandbox.issue_2263;

import io.realm.RealmObject;

/**
 * Created by Nabil on 12/02/2016.
 */
public class Message extends RealmObject {
    private String contact;
    private boolean isSend;
    private String text;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isSend() {
        return isSend;
    }

    public void setSend(boolean send) {
        isSend = send;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
