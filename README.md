# CatatanApp

## Deskripsi
Aplikasi ini dirancang untuk mengelola catatan, termasuk menambahkan, memperbarui, dan menghapus catatan. Modul ini 
menggunakan arsitektur MVVM dan mendukung operasi offline dengan Room sebagai basis datanya.

## Instalasi
### Prasyarat
- Android Studio versi terbaru
- Gradle versi terbaru
- Kotlin plugin
- Android SDK versi 21 atau lebih tinggi

### Langkah Instalasi
1. Clone repositori ini:
    ```bash
    git clone https://github.com/username/repository.git
    ```
2. Buka proyek di Android Studio.
3. Sinkronkan proyek dengan Gradle.
4. Jalankan aplikasi pada perangkat atau emulator.

## Fitur
- Menambahkan catatan baru
- Memperbarui catatan yang ada
- Menghapus catatan
- Mengurutkan catatan berdasarkan judul dan tanggal
- Mendukung operasi offline dengan Room

## Penggunaan
### Contoh Penggunaan
```kotlin
// Modul untuk menambah catatan
val note = Note(title = "Sample Title", description = "Sample Description")
noteAddUpdateViewModel.insert(note)

// Modul untuk menghapus catatan
val note = Note(title = "Sample Title", description = "Sample Description")
noteAddUpdateViewModel.insert(note)
noteAddUpdateViewModel.delete(note)

// Mengambil semua catatan dari basis data
mainViewModel.getAllNotes().observe(this)  { noteList -> 
   // Update UI dengan daftar catatan
   adapter.setListNotes(noteList) 
}

// Mengambil semua catatan dari basis data berdasarkan judul
mainViewModel.getSortedNotesByTitle().observe(this)  { noteList -> 
   // Update UI dengan daftar catatan
   adapter.setListNotes(noteList) 
}

// Mengambil catatan yang diurutkan berdasarkan tanggal
mmainViewModel.getNoteByDate().observe(this)  { noteList -> 
   // Update UI dengan daftar catatan
   adapter.setListNotes(noteList) 
}

