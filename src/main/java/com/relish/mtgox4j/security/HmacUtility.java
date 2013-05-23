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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Utility class for creating HMAC hashes.
 */
public class HmacUtility {
    private final Mac mac;

    /**
     * Initializes this {@link HmacUtility} with the given algorithm and secret.
     * 
     * @param algorithm
     *            the HMAC algorithm to use
     * @param secret
     *            the secret
     * @throws NoSuchAlgorithmException
     *             if an invalid algorithm was passed in
     * @throws InvalidKeyException
     *             if there was a problem initializing the {@link Mac} with the
     *             given secret and algorithm
     */
    public HmacUtility(final String algorithm, final byte[] secret)
            throws NoSuchAlgorithmException, InvalidKeyException {
        mac = Mac.getInstance(checkNotNull(algorithm));

        final SecretKeySpec keySpec = new SecretKeySpec(checkNotNull(secret),
                mac.getAlgorithm());
        mac.init(keySpec);
    }

    /**
     * Create an HMAC hash of the provided msg.
     * 
     * @param msg
     *            the msg to hash
     * @return the HMAC hash
     */
    public byte[] createHmac(final String msg) {
        return mac.doFinal(checkNotNull(msg).getBytes());
    }
}
