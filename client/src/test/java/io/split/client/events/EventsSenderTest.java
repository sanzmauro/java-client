package io.split.client.events;

import io.split.client.RequestDecorator;
import io.split.service.SplitHttpClient;
import io.split.service.SplitHttpClientImpl;
import io.split.telemetry.storage.TelemetryRuntimeProducer;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.URISyntaxException;

public class EventsSenderTest {

    private static final TelemetryRuntimeProducer TELEMETRY_RUNTIME_CONSUMER = Mockito.mock(TelemetryRuntimeProducer.class);
    private static final CloseableHttpClient CLOSEABLE_HTTP_CLIENT = Mockito.mock(CloseableHttpClient.class);

    @Test
    public void testDefaultURL() throws URISyntaxException {
        SplitHttpClient SPLIT_HTTP_CLIENT = SplitHttpClientImpl.create(CLOSEABLE_HTTP_CLIENT, new RequestDecorator(null));
        URI rootTarget = URI.create("https://api.split.io");
        EventsSender fetcher = EventsSender.create(SPLIT_HTTP_CLIENT, rootTarget, TELEMETRY_RUNTIME_CONSUMER);
        Assert.assertEquals("https://api.split.io/api/events/bulk", fetcher.getBulkEndpoint().toString());
    }

    @Test
    public void testCustomURLNoPathNoBackslash() throws URISyntaxException {
        SplitHttpClient SPLIT_HTTP_CLIENT = SplitHttpClientImpl.create(CLOSEABLE_HTTP_CLIENT, new RequestDecorator(null));
        URI rootTarget = URI.create("https://kubernetesturl.com");
        EventsSender fetcher = EventsSender.create(SPLIT_HTTP_CLIENT, rootTarget, TELEMETRY_RUNTIME_CONSUMER);
        Assert.assertEquals("https://kubernetesturl.com/api/events/bulk", fetcher.getBulkEndpoint().toString());
    }

    @Test
    public void testCustomURLAppendingPath() throws URISyntaxException {
        SplitHttpClient SPLIT_HTTP_CLIENT = SplitHttpClientImpl.create(CLOSEABLE_HTTP_CLIENT, new RequestDecorator(null));
        URI rootTarget = URI.create("https://kubernetesturl.com/split/");
        EventsSender fetcher = EventsSender.create(SPLIT_HTTP_CLIENT, rootTarget, TELEMETRY_RUNTIME_CONSUMER);
        Assert.assertEquals("https://kubernetesturl.com/split/api/events/bulk", fetcher.getBulkEndpoint().toString());
    }

    @Test
    public void testCustomURLAppendingPathNoBackslash() throws URISyntaxException {
        SplitHttpClient SPLIT_HTTP_CLIENT = SplitHttpClientImpl.create(CLOSEABLE_HTTP_CLIENT, new RequestDecorator(null));
        URI rootTarget = URI.create("https://kubernetesturl.com/split");
        EventsSender fetcher = EventsSender.create(SPLIT_HTTP_CLIENT, rootTarget, TELEMETRY_RUNTIME_CONSUMER);
        Assert.assertEquals("https://kubernetesturl.com/split/api/events/bulk", fetcher.getBulkEndpoint().toString());
    }
}