package realm.io.realmsupportsandbox;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Nabil on 15/02/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("myrealm.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();

        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
    }
}
