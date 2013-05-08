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

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import static com.google.common.base.Preconditions.checkNotNull;
import com.relish.mtgox4j.model.ECurrency;
import com.relish.mtgox4j.model.json.TickerFast;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Provides access to MtGox's v2 API.
 */
public class MtGoxClient {
    private final Client restClient;
    private final String baseUrl;

    /**
     * @param currency
     *            the currency to convert BTC to
     * @throws NullPointerException
     *             if currency is null
     */
    public MtGoxClient(final ECurrency currency) {
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);
        restClient = Client.create(cc);

        StringBuilder bldr = new StringBuilder(
                "https://data.mtgox.com/api/2/BTC");
        bldr.append(checkNotNull(currency).name());
        bldr.append("/");
        baseUrl = bldr.toString();
    }

//@formatter:off
    /**
     * GET[https://data.mtgox.com/api/2/BTC{currency}/money/ticker_fast]
     * Get the most recent information for a currency pair. 
     * 
     * @return the JSON response
     * @see TickerFast
     */
//@formatter:on
    public TickerFast getTickerFast() {
        return restClient.resource(baseUrl + "money/ticker_fast").get(
                TickerFast.class);
    }
}
