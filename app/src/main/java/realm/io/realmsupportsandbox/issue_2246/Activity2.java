package realm.io.realmsupportsandbox.issue_2246;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import io.realm.Realm;
import realm.io.realmsupportsandbox.R;

/**
 * Created by Nabil on 15/02/2016.
 */
public class Activity2 extends AppCompatActivity {
    private static final String TAG = "ISSUE_2246";
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        realm = Realm.getDefaultInstance();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, Activity1.class);
                startActivity(intent);

            }
        });
        Log.i(TAG, "Activity 2 onCreate Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Activity 2 onResume isRealmClosed " + realm.isClosed());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        Log.i(TAG, "Activity 2 OnDestroy Called");
    }
}
