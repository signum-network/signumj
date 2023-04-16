package signumj.test;

import signumj.service.NodeService;
import signumj.service.impl.CompositeBurstNodeService;
import signumj.service.impl.HttpBurstNodeService;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore // for faster automated build
public class CompositeBurstNodeServiceTest extends NodeServiceTest {
    @Override
    protected NodeService getNodeService() {
        NodeService http = new HttpBurstNodeService(TestVariables.HTTP_NODE, TestVariables.TEST_USER_AGENT);
        return new CompositeBurstNodeService(http);
    }
}
