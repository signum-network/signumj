package burst.kit.test;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.DefaultSchedulerAssigner;
import burst.kit.service.impl.GrpcBurstNodeService;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GrpcBurstNodeServiceTest extends BurstNodeServiceTest {
    @Override
    protected BurstNodeService getBurstNodeService() {
        return new GrpcBurstNodeService("localhost:6878", new DefaultSchedulerAssigner());
    }
}
