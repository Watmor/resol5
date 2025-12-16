package com.example.resol5.Data.Repository
import android.util.Log
import com.example.resol5.Model.Song
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class SongRepository {
    private val db = FirebaseFirestore.getInstance()

    suspend fun getSongs(): List<Song> {
        val songList = mutableListOf<Song>()
        try {
            val snapshot = db.collection("Songs").get().await()
            for (document in snapshot) {
                val song = document.toObject(Song::class.java)
                song.id = document.id
                songList.add(song)
            }
        } catch (e: Exception) {
            Log.e("LoiRepo", "Lỗi lấy nhạc: ", e)
        }
        return songList
    }
}