
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import me.heizi.log_machine.Activity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class DataBaseTest {
    @get:Rule
    val activity = ActivityScenarioRule(Activity::class.java)
    @Test
    fun `应该获取Activity的实例成功`() {
        activity.scenario.onActivity {
            assert(it is Activity)
        }
    }

//    @Test
//    fun `获取fragment？`() {
//        onView(ViewMatchers.withId())
//    }

}