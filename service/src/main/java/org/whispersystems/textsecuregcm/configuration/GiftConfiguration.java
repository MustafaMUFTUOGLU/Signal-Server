/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.whispersystems.textsecuregcm.configuration;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.whispersystems.textsecuregcm.util.ExactlySize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GiftConfiguration {

    /*
     *     long level,
    @NotNull Duration expiration,
    @Valid @NotNull Map<@NotEmpty String, @DecimalMin("0.01") @NotNull BigDecimal> currencies,
    @NotEmpty String badge) {
     */
    private final long level;
    private final Duration expiration;
    private final Map<String, BigDecimal> currencies;
    private final String badge;

    @JsonCreator
    public GiftConfiguration(
        @JsonProperty("level") long level,
        @JsonProperty("expiration") Duration expiration,
        @JsonProperty("currencies") Map<String, BigDecimal> currencies,
        @JsonProperty("badge") String badge) {
        this.level = level;
        this.expiration = expiration;
        this.currencies = currencies;
        this.badge = badge;
    }

    public long getLevel() {
        return level;
    }

    @NotNull
    public Duration getExpiration() {
        return expiration;
    }

    @Valid
    @NotNull
    public Map<@NotEmpty String,  @DecimalMin("0.01") @NotNull BigDecimal> getCurrencies() {
        return currencies;
    }

    @NotEmpty
    public String getBadge() {
        return badge;
    }
}
