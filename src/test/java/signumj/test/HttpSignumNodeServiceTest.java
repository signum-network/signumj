package signumj.test;

import signumj.service.NodeService;
import signumj.service.impl.HttpBurstNodeService;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HttpSignumNodeServiceTest extends SignumNodeServiceTest {
    @Override
    protected NodeService getBurstNodeService() {
        return new HttpBurstNodeService(TestVariables.HTTP_NODE, TestVariables.TEST_USER_AGENT);
    }
}
