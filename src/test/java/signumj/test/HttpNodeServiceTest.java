package signumj.test;

import signumj.Constants;
import signumj.service.NodeService;
import signumj.service.impl.HttpNodeService;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HttpNodeServiceTest extends NodeServiceTest {
    @Override
    protected NodeService getNodeService() {
        return new HttpNodeService(Constants.HTTP_NODE_TESTNET, TestVariables.TEST_USER_AGENT);
    }
}
