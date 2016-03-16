package kushal.xyz.mockitoandrobodemo;

import android.widget.Button;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;

/**
 * Created by anirudh.r on 16/03/16 at 10:38 PM.
 */
@RunWith(RobolectricGradleTestRunner.class)
public class RobolectricTestDemo {

    @Test
    public void testButtonOnClicked() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        Button button = (Button) mainActivity.findViewById(R.id.robolectric_btn);
        TextView textView = (TextView) mainActivity.findViewById(R.id.textView);

        button.performClick();
        Assert.assertEquals(textView.getText().toString(), "Hi. Testing Robolectric");
    }
}
