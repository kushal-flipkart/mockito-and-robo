package kushal.xyz.mockitoandrobodemo;

/**
 * Created by anirudh.r on 16/03/16 at 10:38 PM.
 */

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.FileOutputStream;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by anirudh.r on 16/03/16 at 9:55 PM.
 */
public class MockitoTestDemo {

    @Mock
    private Context fakeContext;
    @Mock
    private FileOutputStream fakeOutputStream;
    private CacheManager cacheManager;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        cacheManager = new FileCacheManager(fakeContext);
    }

    @Test
    public void verifyOutputStreamCalls() throws Exception {
        String json = "{'json':'anyJson'}";
        when(fakeContext.openFileOutput("jsonModel",
                Context.MODE_PRIVATE)).thenReturn(fakeOutputStream);
        cacheManager.put("jsonModel", json);
        Mockito.verify(fakeOutputStream, times(1)).write(json.getBytes());
        Mockito.verify(fakeOutputStream, times(1)).close();
    }
}