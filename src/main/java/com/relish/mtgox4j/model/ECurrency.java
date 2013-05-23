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
package com.relish.mtgox4j.model;

import com.google.common.base.Objects;

//@formatter:off
/**
 * <H1>All supported currencies for MtGox.</H1>
 * <br>
 * <table>
 * <tr>
 * <th>Symbol</th>
 * <th>Name</th>
 * <th>Divisions</th>
 * <th>SF</th>
 * </tr>
 * <tr>
 * <td>BTC</td>
 * <td>Bitcoin</td>
 * <td>100,000,000</td>
 * <td>1e8</td>
 * </tr>
 * <tr>
 * <td>USD</td>
 * <td>US Dollar</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>GBP</td>
 * <td>Great British Pound</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>EUR</td>
 * <td>Euro</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>JPY</td>
 * <td>Japanese Yen</td>
 * <td>1000</td>
 * <td>1e3</td>
 * </tr>
 * <tr>
 * <td>AUD</td>
 * <td>Australian Dollar</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>CAD</td>
 * <td>Canadian Dollar</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>CHF</td>
 * <td>Swiss Franc</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>CNY</td>
 * <td>Chinese Yuan</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>DKK</td>
 * <td>Danish Krone</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>HKD</td>
 * <td>Hong Kong Dollar</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>PLN</td>
 * <td>Polish Zloty</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>RUB</td>
 * <td>Russian Rouble</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>SEK</td>
 * <td>Swedish Krona</td>
 * <td>1000</td>
 * <td>1e3</td>
 * </tr>
 * <tr>
 * <td>SGD</td>
 * <td>Singapore Dollar</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * <tr>
 * <td>THB</td>
 * <td>Thai Baht</td>
 * <td>100,000</td>
 * <td>1e5</td>
 * </tr>
 * </table>
 * 
 * <p>
 * Note the divisions column (the SF column is the same, but gives each in standard form with the number of 0s). 
 * When communicating with the MtGox server, you will often have the opportunity to work with ints or floats. 
 * The float values are deprecated however, and ints are recommended as they are precise. The divisions tells 
 * you what units each of these values is in. So if, for example, you request a bitcoin value, and get a response 
 * of {'value_int': 69655509977}, then the amount in bitcoins is 69655509977/1e8, or 696.55509977 BTC. Of course 
 * there is no requirement that you perform these conversions, but you should be aware of the differences in value.
 * </p>
 */
//@formatter:on
public enum ECurrency {
//@formatter:off
    BTC("Bitcoin",             100000000), 
    USD("US Dollar",           100000), 
    GBP("Great British Pound", 100000), 
    EUR("Euro",                100000), 
    JPY("Japanese Yen",        1000), 
    AUD("Australian Dollar",   100000), 
    CAD("Canadian Dollar",     100000), 
    CHF("Swiss Franc",         100000), 
    CNY("Chinese Yuan",        100000), 
    DKK("Danish Krone",        100000), 
    HKD("Hong Kong Dollar",    100000), 
    PLN("Polish Zloty",        100000), 
    RUB("Russian Rouble",      100000), 
    SEK("Swedish Krona",       1000), 
    SGD("Singapore Dollar",    100000), 
    THB("Thai Baht",           100000);
//@formatter:on

    private final String name;
    private final int divisions;

    /**
     * @param name
     *            the full name of the currency
     * @param divisions
     *            the integer division required for each currency
     */
    private ECurrency(final String name, final int divisions) {
        this.name = name;
        this.divisions = divisions;
    }

    public String getName() {
        return name;
    }

    public int getDivisions() {
        return divisions;
    }

//@formatter:off
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("abbreviation", this.name())
                .add("name", name)
                .add("divisions", divisions)
                .toString();
    }
}
