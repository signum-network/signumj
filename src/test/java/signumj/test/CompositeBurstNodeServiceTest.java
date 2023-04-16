package signumj.test;

import signumj.service.NodeService;
import signumj.service.impl.CompositeNodeService;
import signumj.service.impl.HttpNodeService;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore // for faster automated build
public class CompositeBurstNodeServiceTest extends NodeServiceTest {
    @Override
    protected NodeService getNodeService() {
        NodeService http = new HttpNodeService(TestVariables.HTTP_NODE, TestVariables.TEST_USER_AGENT);
        return new CompositeNodeService(http);
    }
}
