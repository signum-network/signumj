package burst.kit.test;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.HttpBurstNodeService;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HttpBurstNodeServiceTest extends BurstNodeServiceTest {
    @Override
    protected BurstNodeService getBurstNodeService() {
        return new HttpBurstNodeService(TestVariables.HTTP_NODE, TestVariables.TEST_USER_AGENT);
    }
}
