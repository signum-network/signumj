package burst.kit.test;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.HttpBurstNodeService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore // until we have a public node with 3.1.1
public class HttpBurstNodeServiceTest extends BurstNodeServiceTest {
    @Override
    protected BurstNodeService getBurstNodeService() {
        return new HttpBurstNodeService(TestVariables.HTTP_API_ENDPOINT, "burstkit4j-TEST");
    }
}
