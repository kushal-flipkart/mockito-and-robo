package kushal.xyz.mockitoandrobodemo;

import java.io.IOException;

/**
 * Created by anirudh.r on 16/03/16 at 9:56 PM.
 */
public interface CacheManager {
    void put(String id, String json) throws IOException;
}
