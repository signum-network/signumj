package signumj.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.sun.net.httpserver.HttpServer;

import signumj.entity.SignumAddress;
import signumj.entity.SignumTimestamp;
import signumj.entity.response.Transaction;
import signumj.service.NodeService;
import signumj.service.impl.HttpNodeService;

/**
 * Checks the query parameters HttpNodeService sends for getAccountTransactions,
 * against a local server instead of a public node.
 */
@RunWith(JUnit4.class)
public class HttpNodeServiceAccountTransactionsTest {

    private static final SignumAddress ACCOUNT = TestVariables.EXAMPLE_ACCOUNT_ID;

    private HttpServer server;
    private final AtomicReference<String> lastQuery = new AtomicReference<>();
    private NodeService nodeService;

    @Before
    public void setUp() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            lastQuery.set(exchange.getRequestURI().getRawQuery());
            byte[] body = "{\"transactions\":[]}".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        server.start();
        nodeService = new HttpNodeService("http://127.0.0.1:" + server.getAddress().getPort() + "/",
                TestVariables.TEST_USER_AGENT);
    }

    @After
    public void tearDown() {
        server.stop(0);
    }

    @Test
    public void getAccountTransactions_GivenSinceTimestamp_SendsTimestamp() throws Exception {
        Transaction[] transactions = nodeService.getAccountTransactions(ACCOUNT,
                SignumTimestamp.fromBurstTimestamp(123456789), 0, 9, true).blockingGet();

        Map<String, String> query = lastQuery();
        assertEquals(0, transactions.length);
        assertEquals("getAccountTransactions", query.get("requestType"));
        assertEquals(ACCOUNT.getID(), query.get("account"));
        assertEquals("123456789", query.get("timestamp"));
        assertEquals("0", query.get("firstIndex"));
        assertEquals("9", query.get("lastIndex"));
        assertEquals("true", query.get("includeIndirect"));
        assertFalse(query.containsKey("type"));
        assertFalse(query.containsKey("subtype"));
    }

    @Test
    public void getAccountTransactions_GivenSinceTimestampAndType_SendsTimestampAndType() throws Exception {
        nodeService.getAccountTransactions(ACCOUNT, SignumTimestamp.fromBurstTimestamp(42), null, null, false, 2, 1)
                .blockingGet();

        Map<String, String> query = lastQuery();
        assertEquals("42", query.get("timestamp"));
        assertEquals("2", query.get("type"));
        assertEquals("1", query.get("subtype"));
        assertEquals("false", query.get("includeIndirect"));
        assertFalse(query.containsKey("firstIndex"));
        assertFalse(query.containsKey("lastIndex"));
    }

    @Test
    public void getAccountTransactions_GivenNullSinceTimestamp_SendsNoTimestamp() throws Exception {
        nodeService.getAccountTransactions(ACCOUNT, null, 0, 9, null).blockingGet();

        Map<String, String> query = lastQuery();
        assertFalse(query.containsKey("timestamp"));
        assertEquals("false", query.get("includeIndirect"));
    }

    @Test
    public void getAccountTransactions_GivenNoSinceTimestamp_KeepsPreviousParameters() throws Exception {
        nodeService.getAccountTransactions(ACCOUNT, 5, 10, true, 0, 0).blockingGet();

        Map<String, String> query = lastQuery();
        assertFalse(query.containsKey("timestamp"));
        assertEquals("0", query.get("type"));
        assertEquals("0", query.get("subtype"));
        assertEquals("5", query.get("firstIndex"));
        assertEquals("10", query.get("lastIndex"));
        assertTrue(query.containsKey("account"));
    }

    private Map<String, String> lastQuery() throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        for (String pair : lastQuery.get().split("&")) {
            int eq = pair.indexOf('=');
            params.put(URLDecoder.decode(pair.substring(0, eq), "UTF-8"),
                    URLDecoder.decode(pair.substring(eq + 1), "UTF-8"));
        }
        return params;
    }
}
