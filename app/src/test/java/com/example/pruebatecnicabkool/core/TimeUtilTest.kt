package com.example.pruebatecnicabkool.core

import org.junit.Test

class TimeUtilTest {

    @Test
    fun `0 returns Not available`() {
        val result = TimeUtil.getLaunchDateFromTimeStamp(0L)

        assert(result == "Not available")
    }

    @Test
    fun `not 0 returns yyyyMMdd`() {
        val pattern = Regex("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])")
        val result = TimeUtil.getLaunchDateFromTimeStamp(1654534376L)

        assert(pattern.containsMatchIn(result))
    }
}