package com.example.pemesanantiket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.pemesanantiket.ui.theme.PemesananTiketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PemesananTiketTheme {
                TicketOrderScreen()
            }
        }
    }
}

@Composable
fun TicketOrderScreen() {
    val context = LocalContext.current
    val hargaTiket = 50000
    var jumlahTiket by remember { mutableStateOf(1) }

    val totalBayar = hargaTiket * jumlahTiket

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Pemesanan Tiket",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Harga Tiket",
            fontSize = 18.sp
        )

        Text(
            text = "Rp $hargaTiket",
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Jumlah Tiket",
            fontSize = 18.sp
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Button(
                onClick = {
                    if (jumlahTiket > 1) {
                        jumlahTiket--
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            ) {
                Text("-")
            }

            Text(
                text = "$jumlahTiket",
                fontSize = 22.sp,
                modifier = Modifier.padding(horizontal = 24.dp)
            )

            Button(
                onClick = {
                    jumlahTiket++
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Total Bayar",
            fontSize = 18.sp
        )

        Text(
            text = "Rp $totalBayar",
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val intent = Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse("mailto:test@gmail.com")
                )

                intent.putExtra(
                    Intent.EXTRA_SUBJECT,
                    "Konfirmasi Pemesanan Tiket"
                )

                intent.putExtra(
                    Intent.EXTRA_TEXT,
                    """
            Saya ingin melakukan pemesanan tiket.

            Harga Tiket : Rp$hargaTiket
            Jumlah Tiket : $jumlahTiket
            Total Bayar : Rp$totalBayar
            """.trimIndent()
                )

                context.startActivity(intent)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Konfirmasi Pemesanan Tiket")
        }
    }
}
