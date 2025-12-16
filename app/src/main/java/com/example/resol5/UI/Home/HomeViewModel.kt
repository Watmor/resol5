package com.example.resol5.UI.Home
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resol5.Data.Repository.SongRepository
import com.example.resol5.Model.Song
import kotlinx.coroutines.launch
class HomeViewModel {
    class HomeViewModel : ViewModel() {
        private val repository = SongRepository()

        // Danh sách chứa nhạc để UI hiển thị
        private val _songs = mutableStateListOf<Song>()
        val songs: List<Song> get() = _songs

        init {
            // Mở lên là đi lấy nhạc ngay
            fetchSongs()
        }

        private fun fetchSongs() {
            viewModelScope.launch {
                val list = repository.getSongs()
                _songs.clear()
                _songs.addAll(list)
            }
        }
    }
}

