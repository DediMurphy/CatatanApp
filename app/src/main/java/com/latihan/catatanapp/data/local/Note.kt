package com.latihan.catatanapp.data.local


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Data class representing a note entity.
 * @property id The unique identifier for the note.
 * @property title The title of the note.
 * @property description The description of the note.
 * @property date The date of the note.
 */
@Entity
@Parcelize
data class Note(

    // primary key
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null,

    @ColumnInfo(name = "date")
    var date: String? = null
) : Parcelable