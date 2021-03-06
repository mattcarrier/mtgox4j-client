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
package com.relish.mtgox4j.model.json;

import static nl.jqno.equalsverifier.EqualsVerifier.forClass;
import nl.jqno.equalsverifier.Warning;

import org.junit.Test;

/**
 * Unit tests for .equals() and .hashCode() on all JSON model objects.
 */
public class EqualsAndHashcodeTest {
    @Test
    public void tickerFast() {
        forClass(TickerFast.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }

    @Test
    public void tickerFastData() {
        forClass(TickerFastData.class).suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }

    @Test
    public void tickerFastInnerData() {
        forClass(TickerFastInnerData.class).suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
