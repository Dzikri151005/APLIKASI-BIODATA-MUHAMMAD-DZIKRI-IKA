package com.example.aplikasibio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.aplikasibio.ui.theme.AplikasiBIOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AplikasiBIOTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    var showDetail by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White.copy(alpha = 0.9f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                val items = listOf("Biodata", "Tambah Pesan", "Hubungi")
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            when (index) {
                                0 -> Icon(Icons.Filled.Person, contentDescription = "Biodata")
                                1 -> Icon(Icons.Filled.Add, contentDescription = "Tambah Pesan")
                                2 -> Icon(Icons.Filled.Phone, contentDescription = "Hubungi")
                            }
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = {
                            showDetail = false // reset saat berpindah tab
                            selectedItem = index
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (showDetail) {
                DetailScreen(onBack = { showDetail = false })
            } else {
                when (selectedItem) {
                    0 -> BiodataScreen(onDetailClick = { showDetail = true })
                    1 -> AddMessageScreen()
                    2 -> ContactScreen()
                }
            }
        }
    }
}

@Composable
fun BiodataScreen(onDetailClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = rememberAsyncImagePainter("https://images.unsplash.com/photo-1522071820081-009f0129c71c?fit=crop&w=1200&h=800"),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xAAFFFFFF))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "M. Dzikri Habibullah",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E7D32),
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
            )

            Image(
                painter = rememberAsyncImagePainter("https://placehold.co/200x200/cccccc/000000?text=DZIKRI"),
                contentDescription = "Foto Dzikri Habibullah",
                modifier = Modifier
                    .size(200.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.9f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text("📌 Informasi Pribadi", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    InfoText("📅 Tanggal Lahir:", "15 Oktober 2025")
                    InfoText("📍 Alamat:", "Kp. Muruy, Ds. Mandalasari, Pandeglang, Banten")
                    InfoText("💼 Pekerjaan:", "Guru")
                    InfoText("🎯 Hobi:", "Jalan-jalan, Gym, Olahraga")

                    Spacer(modifier = Modifier.height(20.dp))

                    Text("📚 Riwayat Pendidikan", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
                    InfoText("🧒 SD:", "SDN Mandalasari 1")
                    InfoText("👦 MTS:", "MTS Dahuci")
                    InfoText("🧑 MAS:", "MAS Dahuci")

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = onDetailClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Lihat Detail", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                }
            }

            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@Composable
fun DetailScreen(onBack: () -> Unit) {
    val imageUrls = listOf(
        "https://source.unsplash.com/400x300/?travel",
        "https://source.unsplash.com/400x300/?gym",
        "https://source.unsplash.com/400x300/?sports"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F0F0))
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "✨ Kegiatan Favorit ✨",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF388E3C),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        imageUrls.forEach { url ->
            Image(
                painter = rememberAsyncImagePainter(url),
                contentDescription = "Foto Hobi",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(vertical = 8.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Teruslah belajar dan jangan mudah menyerah! 💪",
            fontSize = 18.sp,
            color = Color.DarkGray,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onBack) {
            Text("⬅ Kembali")
        }
    }
}

@Composable
fun InfoText(label: String, value: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp)) {
        Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = Color.Black)
        Text(text = value, fontSize = 16.sp, color = Color.DarkGray)
    }
}

@Composable
fun AddMessageScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Halaman Tambah Pesan (Belum Diimplementasikan)", fontSize = 20.sp)
    }
}

@Composable
fun ContactScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Halaman Hubungi (Belum Diimplementasikan)", fontSize = 20.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    AplikasiBIOTheme {
        MainScreen()
    }
}
