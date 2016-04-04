package kushal.xyz.mockitoandrobodemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    FileCacheManager fileCacheManager;

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setFileCacheManager(FileCacheManager fileCacheManager) {
        this.fileCacheManager = fileCacheManager;
    }

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandlerThread handlerThread = new HandlerThread("backgroundThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);

        fileCacheManager = new FileCacheManager(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.textView);
        setSupportActionBar(toolbar);

        Button performAction = (Button) findViewById(R.id.perform_action);
        assert performAction != null;
        performAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("Hello!!!");
            }
        });

        Button performAction1 = (Button) findViewById(R.id.perform_action1);
        assert performAction1 != null;
        performAction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Hello!!!");
                callCacheManager(handler, fileCacheManager);
            }
        });
    }

    public void callCacheManager(Handler handler, final FileCacheManager cacheManager) {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    cacheManager.put("ID", "{JSON Data}");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        callCacheManager(handler, fileCacheManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            textView.setText("Settings clicked");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
