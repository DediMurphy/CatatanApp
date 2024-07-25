package com.latihan.catatanapp.utils

import com.latihan.catatanapp.data.local.note.Note

object DataDummy {
    fun generateDummyNewsEntity(): List<Note> {
        val newsList = ArrayList<Note>()
        for (i in 0..10) {
            val news = Note(
                1,
                "membuat program",
                "melakukan Unit test",
                "2024/07/10 01:12:12" ,
            )
            newsList.add(news)
        }
        return newsList
    }
}