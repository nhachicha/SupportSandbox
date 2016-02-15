package realm.io.realmsupportsandbox;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import realm.io.realmsupportsandbox.issue_2263.Message;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "REALM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The RealmConfiguration is created using the builder pattern.
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();

        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkLinkedQuery();
    }

    private void checkLinkedQuery() {
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.where(Message.class).findAll().clear();
        realm.commitTransaction();


        createMessage(realm, "nameA", "A msg 1" , false);
        createMessage(realm, "nameA", "A msg 2" , true);
        createMessage(realm, "nameA", "A msg 3" , true);
        createMessage(realm, "nameA", "A msg 4" , true);

        createMessage(realm, "nameB", "B msg 1" , false);
        createMessage(realm, "nameB", "B msg 2" , true);
        createMessage(realm, "nameB", "B msg 3", true);
        createMessage(realm, "nameB", "B msg 4", true);


        final RealmResults<Message> unSentMessagesToNameA = realm.where(Message.class).equalTo("contact", "nameA").equalTo("isSend", false).findAllAsync();
        unSentMessagesToNameA.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {

                printMessages(unSentMessagesToNameA, "onChange unSentMessagesToNameA");
            }
        });

        final RealmResults<Message> unSentMessagesToNameB = realm.where(Message.class).equalTo("contact", "nameB").equalTo("isSend", false).findAllAsync();
        unSentMessagesToNameB.addChangeListener(new RealmChangeListener() {
            @Override
            public void onChange() {
                printMessages(unSentMessagesToNameB, "onChange unSentMessagesToNameB");
            }
        });


        createMessage(realm, "nameA", "A msg 5" , false);
        createMessage(realm, "nameB", "B msg 5" , false);
    }

    private void printMessages(RealmResults<Message> messages, String title) {
        Log.i(TAG, title);
        for (Message message : messages) {
            Log.i(TAG, message.getContact() + " " + message.getText() + " " + message.isSend());
        }
    }

    private void createMessage(Realm realm, String contact, String text, boolean sent) {
        realm.beginTransaction();
        Message object = realm.createObject(Message.class);
        object.setContact(contact);
        object.setSend(sent);
        object.setText(text);
        realm.commitTransaction();
    }

}
