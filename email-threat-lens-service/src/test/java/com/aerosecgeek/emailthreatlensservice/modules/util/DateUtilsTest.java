package com.aerosecgeek.emailthreatlensservice.modules.util;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {
    @Test
    void givenDateOlder24Hours_whenIsOlderThan24Hours_thenTrue() {
        // given
        Date olderThan24Hours = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(25));

        // when
        var result = DateUtils.isOlderThan24Hours(olderThan24Hours);
        // then
        assertTrue(result);
    }

    @Test
    void givenDateNewer24Hours_whenIsOlderThan24Hours_thenFalse() {
        // given
        Date newerThan24Hours = new Date(System.currentTimeMillis() - TimeUnit.HOURS.toMillis(23));

        // when
        var result = DateUtils.isOlderThan24Hours(newerThan24Hours);
        // then
        assertFalse(result);
    }

}