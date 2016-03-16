package kushal.xyz.mockitoandrobodemo;

import android.content.Context;

import com.google.common.io.Closer;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by anirudh.r on 16/03/16 at 9:52 PM.
 */
public class FileCacheManager implements CacheManager {

    Context context;

    public FileCacheManager(Context context) {
        this.context = context;
    }

    @Override
    public void put(String id, String json) throws IOException {
        Closer closer = Closer.create();
        try {
            FileOutputStream os = closer.register
                    (context.openFileOutput(id, Context.MODE_PRIVATE));
            os.write(json.getBytes());
        } catch (IOException e) {
            throw new IOException("Error writing in the cache " + e.getMessage());
        } finally {
            closer.close();
        }
    }
}
