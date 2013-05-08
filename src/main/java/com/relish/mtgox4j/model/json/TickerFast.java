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
* JSON object representing the {@link TickerFast} Response from MtGox.
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
public class TickerFast implements Serializable {
    private static final long serialVersionUID = -895916240211663866L;

    private String result;
    private TickerFastData data;

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public TickerFastData getData() {
        return data;
    }

    public void setData(final TickerFastData data) {
        this.data = data;
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
                this.result, 
                this.data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
        if (null == obj || !(obj instanceof TickerFast)) {
            return false;
        }

        TickerFast that = (TickerFast) obj;
        return Objects.equal(this.result, that.result)
                && Objects.equal(this.data, that.data);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("result", result)
                .add("data", data)
                .toString();
    }
}
