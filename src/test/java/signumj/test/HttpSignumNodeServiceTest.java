package signumj.test;

import signumj.Constants;
import signumj.service.NodeService;
import signumj.service.impl.HttpBurstNodeService;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HttpSignumNodeServiceTest extends NodeServiceTest {
    @Override
    protected NodeService getNodeService() {
        return new HttpBurstNodeService(Constants.HTTP_NODE_TESTNET, TestVariables.TEST_USER_AGENT);
    }
}
