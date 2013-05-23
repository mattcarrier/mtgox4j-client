/**
 * Copyright 2013 Matt Carrier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Matt Carrier (mcarrieruri@gmail.com)
 */
package com.relish.mtgox4j;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.base.Optional;
import com.relish.mtgox4j.model.ECurrency;
import com.relish.mtgox4j.model.json.TickerFast;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Unit tests for {@link MtGoxClient}.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Client.class)
public class MtGoxClientTest {
    @Mock
    private Client client;
    @Mock
    private WebResource resource;

    private MtGoxClient mtGoxClient;
    private TickerFast tFast;

    /**
     * Mock out Jersey {@link Client} to return stubbed {@link TickerFast}.
     * 
     * @throws JsonParseException
     *             if there was a problem parsing the stubbed json file
     * @throws JsonMappingException
     *             if there was a problem mapping the stubbed json file to
     *             {@link TickerFast}
     * @throws IOException
     *             if there was a problem reading the stubbed json file
     */
    @Before
    public void setup() throws JsonParseException, JsonMappingException,
            IOException {
        mockStatic(Client.class);
        when(Client.create(any(DefaultClientConfig.class))).thenReturn(client);

        ObjectMapper mapper = new ObjectMapper();
        tFast = mapper.readValue(this.getClass().getClassLoader()
                .getResourceAsStream("TickerFast.json"), TickerFast.class);

        when(resource.get(TickerFast.class)).thenReturn(tFast);
        when(
                client.resource("https://data.mtgox.com/api/2/BTCUSD/money/ticker_fast"))
                .thenReturn(resource);
    }

    /**
     * {@link NullPointerException} expected with a null currency.
     */
    @Test(expected = NullPointerException.class)
    public void nullCurrency() {
        mtGoxClient = new MtGoxClient(null, Optional.<String> absent(),
                Optional.<String> absent());
    }

    /**
     * The tickerFast method should return the stubbed {@link TickerFast} on
     * {@link ECurrency}.USD.
     */
    @Test
    public void tickerFast() {
        mtGoxClient = new MtGoxClient(ECurrency.USD,
                Optional.<String> absent(), Optional.<String> absent());
        assertEquals(tFast, mtGoxClient.getTickerFast());
    }
}
