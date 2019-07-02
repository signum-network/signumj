package burst.kit.test;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.CompositeBurstNodeService;
import burst.kit.service.impl.DefaultSchedulerAssigner;
import burst.kit.service.impl.GrpcBurstNodeService;
import burst.kit.service.impl.HttpBurstNodeService;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CompositeBurstNodeServiceTest extends BurstNodeServiceTest {
    @Override
    protected BurstNodeService getBurstNodeService() {
        BurstNodeService http = new HttpBurstNodeService("https://wallet.burst-alliance.org:8125", "burstkit4j-TEST", new DefaultSchedulerAssigner());
        BurstNodeService grpc = new GrpcBurstNodeService("localhost:6878", new DefaultSchedulerAssigner());
        return new CompositeBurstNodeService(http, grpc);
    }
}
