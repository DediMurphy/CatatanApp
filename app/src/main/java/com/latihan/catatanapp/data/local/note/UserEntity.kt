package com.latihan.catatanapp.data.local.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entitas User untuk tabel "user" dalam database Room.
 *
 * @property id ID unik untuk setiap pengguna, dihasilkan secara otomatis.
 * @property username Nama pengguna.
 * @property email Alamat email pengguna.
 * @property password Kata sandi pengguna.
 */
@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String,
)
