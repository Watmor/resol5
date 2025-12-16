package com.example.resol5

import android.os.Bundle
import android.util.Log // Import để ghi Log kiểm tra
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.resol5.UI.Home.HomeScreen
// LƯU Ý: Kiểm tra kỹ dòng import Song này, nếu nó báo đỏ hãy xóa đi và gõ lại để gợi ý tự động
import com.example.resol5.Model.Song
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Gọi hàm kiểm tra dữ liệu ngay khi App mở lên
        testFirestoreData()

        setContent {
            // Đặt màu thanh trạng thái
            window.statusBarColor = getColor(R.color.black)

            // Hiển thị giao diện chính
            HomeScreen()
        }
    }

    // --- HÀM KIỂM TRA KẾT NỐI FIREBASE ---
    private fun testFirestoreData() {
        // Khởi tạo Firestore
        val db = FirebaseFirestore.getInstance()

        // Truy cập vào Collection "Songs" (Chữ S viết hoa phải khớp với trên web)
        db.collection("Songs")
            .get()
            .addOnSuccessListener { documents ->
                // Nếu lấy dữ liệu thành công:
                if (documents.isEmpty) {
                    Log.d("KiemTra", "Kết nối thành công nhưng không có bài hát nào!")
                } else {
                    for (document in documents) {
                        // Chuyển đổi dữ liệu về dạng class Song
                        val song = document.toObject(Song::class.java)

                        // Ghi log ra màn hình console để kiểm tra
                        Log.d("KiemTra", "-----------------------------")
                        Log.d("KiemTra", "Đã lấy được bài: ${song.TieuDe}")
                        Log.d("KiemTra", "Ca sĩ: ${song.CaSi}")
                        Log.d("KiemTra", "Link nhạc: ${song.Nhac}")
                    }
                }
            }
            .addOnFailureListener { exception ->
                // Nếu thất bại (ví dụ: sai file google-services.json hoặc chưa có mạng)
                Log.e("KiemTra", "Lỗi kết nối Firebase: ", exception)
            }
    }
}