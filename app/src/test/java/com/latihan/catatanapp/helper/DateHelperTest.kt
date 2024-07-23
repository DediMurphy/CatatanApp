package com.latihan.catatanapp.helper

import org.junit.Assert.*

import org.junit.Test
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateHelperTest {

    /**
     * Menguji metode `getCurrentDate` dari `DateHelper` untuk memastikan bahwa tanggal yang dihasilkan
     * sesuai dengan format yang diharapkan.
     */
    @Test
    fun getCurrentDate() {
        val dateFormat: DateFormat = SimpleDateFormat("yyyy/mm/dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        val expectedDate = dateFormat.format(date)
        assertEquals(expectedDate, DateHelper.getCurrentDate())
    }
}