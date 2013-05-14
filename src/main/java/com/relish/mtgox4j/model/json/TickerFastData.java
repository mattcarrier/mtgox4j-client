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

import java.io.Serializable;

import com.google.common.base.Objects;

//@formatter:off
/**
* JSON object representing the 'data' portion of the {@link TickerFast} Response from MtGox.
* 
* last_local -- is the last trade in your selected auxiliary currency
* last_orig  -- is the last trade (any currency)
* last_all   -- is that last trade converted to the auxiliary currency
* last       -- is the same as last_local
* now        -- is the unix timestamp, but with a resolution of 1 microsecond
* 
* Example {@link TickerFast} Object:
{
  "result": "success",
  "data": {
    "last_local": {
        "value": "97.97038",
        "value_int": "9797038",
        "display": "$97.97",
        "display_short": "$97.97",
        "currency": "USD"
    },
    "last": {
        "value": "97.97038",
        "value_int": "9797038",
        "display": "$97.97",
        "display_short": "$97.97",
        "currency": "USD"
    },
    "last_orig": {
        "value": "97.97038",
        "value_int": "9797038",
        "display": "$97.97",
        "display_short": "$97.97",
        "currency": "USD"
    },
    "last_all": {
        "value": "97.97038",
        "value_int": "9797038",
        "display": "$97.97",
        "display_short": "$97.97",
        "currency": "USD"
    },
    "buy": {
        "value": "98.53741",
        "value_int": "9853741",
        "display": "$98.54",
        "display_short": "$98.54",
        "currency": "USD"
    },
    "sell": {
        "value": "98.69847",
        "value_int": "9869847",
        "display": "$98.70",
        "display_short": "$98.70",
        "currency": "USD"
    },
    "now": "1367550485692271"
  }
}
*/
//@formatter:on
public class TickerFastData implements Serializable {
    private static final long serialVersionUID = 1L;

    private TickerFastInnerData last_local;
    private TickerFastInnerData last;
    private TickerFastInnerData last_orig;
    private TickerFastInnerData last_all;
    private TickerFastInnerData buy;
    private TickerFastInnerData sell;
    private Long now;

    public TickerFastInnerData getLast_local() {
        return last_local;
    }

    public void setLast_local(final TickerFastInnerData last_local) {
        this.last_local = last_local;
    }

    public TickerFastInnerData getLast() {
        return last;
    }

    public void setLast(final TickerFastInnerData last) {
        this.last = last;
    }

    public TickerFastInnerData getLast_orig() {
        return last_orig;
    }

    public void setLast_orig(final TickerFastInnerData last_orig) {
        this.last_orig = last_orig;
    }

    public TickerFastInnerData getLast_all() {
        return last_all;
    }

    public void setLast_all(final TickerFastInnerData last_all) {
        this.last_all = last_all;
    }

    public TickerFastInnerData getBuy() {
        return buy;
    }

    public void setBuy(final TickerFastInnerData buy) {
        this.buy = buy;
    }

    public TickerFastInnerData getSell() {
        return sell;
    }

    public void setSell(final TickerFastInnerData sell) {
        this.sell = sell;
    }

    public Long getNow() {
        return now;
    }

    public void setNow(final Long now) {
        this.now = now;
    }

//@formatter:off
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public final int hashCode() {
        return Objects.hashCode(
                this.buy, 
                this.last, 
                this.last_all,
                this.last_local, 
                this.last_orig, 
                this.now, 
                this.sell);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof TickerFastData)) {
            return false;
        }

        TickerFastData that = (TickerFastData) obj;
        return Objects.equal(this.buy, that.buy)
                && Objects.equal(this.last, that.last)
                && Objects.equal(this.last_all, that.last_all)
                && Objects.equal(this.last_local, that.last_local)
                && Objects.equal(this.last_orig, that.last_orig)
                && Objects.equal(this.now, that.now)
                && Objects.equal(this.sell, that.sell);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("buy", buy)
                .add("last", last)
                .add("last_all", last_all)
                .add("last_local", last_local)
                .add("last_orig", last_orig)
                .add("now", now)
                .add("sell", sell)
                .toString();
    }
}
