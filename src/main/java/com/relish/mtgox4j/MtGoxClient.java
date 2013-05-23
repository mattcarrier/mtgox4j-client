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
import static com.google.common.base.Preconditions.checkState;

import com.google.common.base.Optional;
import com.relish.mtgox4j.model.ECurrency;
import com.relish.mtgox4j.model.json.TickerFast;
import com.relish.mtgox4j.security.MtGoxSecurity;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Provides access to MtGox's v2 API.
 */
public class MtGoxClient {
    public static final String BASE_URL = "https://data.mtgox.com/api/2/";

    private static final String MONEY_TICKER_FAST_PATH = "money/ticker_fast";
    private static final String MONEY_INFO_PATH = "money/info";

    private final Client restClient;
    private final String currencyPath;
    private final Optional<MtGoxSecurity> security;

    /**
     * Provides access to secured MtGox resources as well as unsecured if apiKey
     * and apiSecret are provided. If they are not provided then only unsecured
     * resources will be available.
     * 
     * @param currency
     *            the currency to convert BTC to
     * @param apiKey
     *            your apiKey from MtGox
     * @param apiSecret
     *            your encoded apiSecret (your apiSecret directly from MtGox is
     *            already encoded)
     * @throws NullPointerException
     *             if currency is null
     */
    public MtGoxClient(final ECurrency currency, final Optional<String> apiKey,
            final Optional<String> apiSecret) {
        final ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);
        restClient = Client.create(cc);

        currencyPath = "BTC" + checkNotNull(currency).name() + "/";

        if (apiKey.isPresent() && apiSecret.isPresent()) {
            security = Optional.of(new MtGoxSecurity(apiKey.get(), apiSecret
                    .get()));
        } else {
            security = Optional.absent();
        }
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
        return restClient.resource(
                BASE_URL + currencyPath + MONEY_TICKER_FAST_PATH).get(
                TickerFast.class);
    }

//@formatter:off
    /**
     * TODO: Create JSON model objects
     * 
     * POST[https://data.mtgox.com/api/2/BTC{currency}/money/info]
     * Get up-to-date information regarding your finances on MtGox.
     * 
     * @return the JSON response
     * @throws IllegalStateException 
     *              if this {@link MtGoxClient} was initialized without a 
     *              security context (apiKey and apiSecret)
     */
//@formatter:on
    public String getMoneyInfo() {
        checkState(
                security.isPresent(),
                "Secured resource was requested without a security context available. "
                        + "Please initialize the MtGoxClient with a security context for secured resource access.");

        return security.get().sendSecurePost(restClient,
                currencyPath + MONEY_INFO_PATH, String.class,
                Optional.<String> absent());
    }
}
