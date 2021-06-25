package burst.kit.test;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.CompositeBurstNodeService;
import burst.kit.service.impl.HttpBurstNodeService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore // TODO
public class CompositeBurstNodeServiceTest extends BurstNodeServiceTest {
    @Override
    protected BurstNodeService getBurstNodeService() {
        BurstNodeService http = new HttpBurstNodeService(TestVariables.HTTP_API_ENDPOINT, "burstkit4j-TEST");
        return new CompositeBurstNodeService(http);
    }
}
