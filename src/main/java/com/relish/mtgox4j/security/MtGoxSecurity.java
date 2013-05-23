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
package com.relish.mtgox4j.security;

import static com.google.common.base.Preconditions.checkNotNull;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.google.common.io.BaseEncoding;
import com.relish.mtgox4j.MtGoxClient;
import com.sun.jersey.api.client.Client;

/**
 * Performs secure POSTs to MtGox.
 * 
 * <H1>Security</H1>
 * 
 * <p>
 * In order to use the API, you must obtain an API key and secret. To do this,
 * login to https://mtgox.com/security, and open the Advanced API Key Creation
 * section. You can give your API key a name, and specify which permissions
 * (rights) it will have. Once you have customised it to your liking, click
 * Create Key. Make sure to copy down your API secret, as the website will not
 * show you it again after this point.
 * </p>
 * 
 * <p>
 * The API keys you have created are shown under Current API Keys after you
 * refresh the page. From here, you can retrieve your API key if you forget it,
 * and you can change what rights it has at any time. If you believe it has been
 * compromised, you should immediately revoke the api key by clicking on the red
 * cross.
 * </p>
 * 
 * <p>
 * Your API key and secret will be used to authenticate with MtGox's servers.
 * This is achieved via the use of two HTTP headers: Rest-Key and Rest-Sign:
 * </p>
 * 
 * <p>
 * Rest-Key is your API key, Rest-Sign is an HMAC hash constructed from your API
 * secret, your API method path, your post data, and uses the SHA-512 algorithm.
 * The Rest-Sign is constructed as follows:
 * </p>
 * 
 * <ol>
 * <li>Your API secret has been base-64 encoded, decode it</li>
 * <li>Join your method path, the NULL \0 character, and your post data into a
 * string</li>
 * <li>Generate an HMAC hash string, using your decoded secret as the secret,
 * SHA-512 as the hash function, and your constructed string as the message</li>
 * <li>Encode this HMAC string using base-64</li>
 * </ol>
 */
public class MtGoxSecurity {
    private static final BaseEncoding BASE_64_CODEC = BaseEncoding.base64();
    private static final String HMAC_SHA_512_ALGORITHM = "HmacSHA512";

    private final HmacUtility hmacUtility;
    private final String apiKey;

    /**
     * @param apiKey
     *            your MtGox api key
     * @param apiSecret
     *            your MtGox api secret (still encoded)
     */
    public MtGoxSecurity(final String apiKey, final String apiSecret) {
        try {
            hmacUtility = new HmacUtility(HMAC_SHA_512_ALGORITHM,
                    BASE_64_CODEC.decode(checkNotNull(apiSecret)));
        } catch (NoSuchAlgorithmException e) {
            // TODO: Log internal error
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            // TODO: Log user error
            throw new RuntimeException(e);
        }

        this.apiKey = checkNotNull(apiKey);
    }

    /**
     * Send a secured POST to MtGox.
     * 
     * <H1>Requests</H1>
     * 
     * <p>
     * Each request consists of a path, any arguments, and a nonce value. The
     * different methods you can use are defined by their paths and the
     * arguments they accept. The path is the part of the HTTP URL after the
     * base.
     * </p>
     * 
     * Base: https://data.mtgox.com/api/2/ <br>
     * Path: BTCUSD/money/ticker <br>
     * 
     * <p>
     * The nonce value is important to prevent duplicated requests. In short,
     * each request should be accompanied by a unique nonce value in the post
     * data, and this value should be an integer and larger than any value sent
     * previously. One way to accomplish this is by using millisecond or
     * microsecond unix time, e.g. if your computer supports microsecond
     * resolution. This should be added to your argument list, and then
     * submitted using url form encoding.
     * </p>
     * 
     * @param restClient
     *            the {@link Client} to use for the POST
     * @param path
     *            the resource path (don't include MtGoxClient.BASE_URL or
     *            currency)
     * @param responseType
     *            the type of response
     * @param postData
     *            the data to POST to MtGox if required
     * @return The response
     */
    public <T> T sendSecurePost(final Client restClient, final String path,
            final Class<T> responseType, final Optional<String> postData) {
        final String nonce = "nonce=" + System.currentTimeMillis();
        final String postDataWithNonce = postData.isPresent() ? postData.get()
                + "&" + nonce : nonce;
        final String toBeHashed = checkNotNull(path) + "\0" + postDataWithNonce;
        final byte[] hash = hmacUtility.createHmac(toBeHashed);
        final String encodedHash = BASE_64_CODEC.encode(hash);

        return restClient
                .resource(MtGoxClient.BASE_URL + path)
                .header("Rest-Key", apiKey)
                .header("Rest-Sign", encodedHash)
                .entity(postDataWithNonce,
                        MediaType.APPLICATION_FORM_URLENCODED)
                .post(checkNotNull(responseType));
    }
}
