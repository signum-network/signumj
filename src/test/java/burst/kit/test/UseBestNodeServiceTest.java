package burst.kit.test;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.HttpBurstNodeService;
import burst.kit.service.impl.UseBestNodeService;

@RunWith(JUnit4.class)
@Ignore // for faster automated build
public class UseBestNodeServiceTest extends BurstNodeServiceTest {
	
	private static UseBestNodeService instance;
	
    @Override
    protected BurstNodeService getBurstNodeService() {
    	if(instance == null) {
    		ArrayList<BurstNodeService> nodeList = new ArrayList<BurstNodeService>();
    		nodeList.add(new HttpBurstNodeService(TestVariables.HTTP_NODE, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpBurstNodeService(TestVariables.HTTP_NODE2, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpBurstNodeService(TestVariables.HTTP_NODE3, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpBurstNodeService(TestVariables.HTTP_NODE4, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpBurstNodeService(TestVariables.HTTP_NODE5, TestVariables.TEST_USER_AGENT));
    		nodeList.add(new HttpBurstNodeService(TestVariables.HTTP_NODE6, TestVariables.TEST_USER_AGENT));
    		instance =  new UseBestNodeService(true, nodeList);
    	}
 		return instance;
    }
}
