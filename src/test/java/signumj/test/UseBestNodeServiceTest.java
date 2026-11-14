package signumj.test;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import signumj.Constants;
import signumj.service.NodeService;
import signumj.service.impl.HttpNodeService;
import signumj.service.impl.UseBestNodeService;

@RunWith(JUnit4.class)
@Ignore // for faster automated build
public class UseBestNodeServiceTest extends NodeServiceTest {

	private static UseBestNodeService instance;

    @Override
    protected NodeService getNodeService() {
    	if(instance == null) {
    		ArrayList<NodeService> nodeList = new ArrayList<NodeService>();
    		nodeList.add(new HttpNodeService(Constants.HTTP_NODE_EUROPE1, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpNodeService(Constants.HTTP_NODE_EUROPE2, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpNodeService(Constants.HTTP_NODE_EUROPE, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpNodeService(Constants.HTTP_NODE_CANADA, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpNodeService(Constants.HTTP_NODE_BRAZIL, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpNodeService(Constants.HTTP_NODE_LOCAL, TestVariables.TEST_USER_AGENT));
    		instance =  new UseBestNodeService(true, nodeList);
    	}
 		return instance;
    }
}
