package burst.kit.test;

import burst.kit.service.BurstNodeService;
import burst.kit.service.impl.GrpcBurstNodeService;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@Ignore // TODO
public class GrpcBurstNodeServiceTest extends BurstNodeServiceTest {
    @Override
    protected BurstNodeService getBurstNodeService() {
        return new GrpcBurstNodeService(TestVariables.GRPC_API_ENDPOINT);
    }
}
