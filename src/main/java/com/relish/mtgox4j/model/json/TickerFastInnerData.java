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
* JSON object representing the segmented 'data' portions of the {@link TickerFast} Response from MtGox.
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
public class TickerFastInnerData implements Serializable {
    private static final long serialVersionUID = -8095785773908549233L;

    private String value;
    private String value_int;
    private String display;
    private String display_short;
    private String currency;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getValue_int() {
        return value_int;
    }

    public void setValue_int(final String value_int) {
        this.value_int = value_int;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(final String display) {
        this.display = display;
    }

    public String getDisplay_short() {
        return display_short;
    }

    public void setDisplay_short(final String display_short) {
        this.display_short = display_short;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
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
                this.currency, 
                this.display,
                this.display_short, 
                this.value, 
                this.value_int);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof TickerFastInnerData)) {
            return false;
        }

        TickerFastInnerData that = (TickerFastInnerData) obj;
        return Objects.equal(this.currency, that.currency) 
                && Objects.equal(this.display, that.display)
                && Objects.equal(this.display_short, that.display_short)
                && Objects.equal(this.value, that.value)
                && Objects.equal(this.value_int, that.value_int);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("currency", currency)
                .add("display", display)
                .add("display_short", display_short)
                .add("value", value)
                .add("value_int", value_int)
                .toString();
    }
}
