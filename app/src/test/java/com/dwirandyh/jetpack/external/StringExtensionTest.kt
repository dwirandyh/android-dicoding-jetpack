package com.dwirandyh.jetpack.external

import org.junit.Assert.assertEquals
import org.junit.Test

class StringExtensionTest {

    @Test
    fun dateFormatTest() {
        val dateString = "1996-03-19"
        val result = dateString.convertToDate("YYYY-mm-dd")
        assertEquals("Sun Dec 31 00:03:00 WIB 1995", result.toString())
    }
}