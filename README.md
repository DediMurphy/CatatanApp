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

## Penggunaan
### Contoh Penggunaan
```kotlin
// Contoh penggunaan modul untuk menambah catatan
val note = Note(title = "Sample Title", description = "Sample Description")
noteAddUpdateViewModel.insert(note)
