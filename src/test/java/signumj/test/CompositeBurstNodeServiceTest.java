package signumj.test;

import signumj.service.NodeService;
import signumj.service.impl.CompositeBurstNodeService;
import signumj.service.impl.HttpBurstNodeService;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore // TODO
public class CompositeBurstNodeServiceTest extends SignumNodeServiceTest {
    @Override
    protected NodeService getBurstNodeService() {
        NodeService http = new HttpBurstNodeService(TestVariables.HTTP_NODE, TestVariables.TEST_USER_AGENT);
        return new CompositeBurstNodeService(http);
    }
}
