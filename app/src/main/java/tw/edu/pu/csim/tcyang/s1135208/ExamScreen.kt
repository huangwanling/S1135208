package tw.edu.pu.csim.tcyang.s1135208

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExamScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 中間圓形 happy logo
            Image(
                painter = painterResource(id = R.drawable.happy),
                contentDescription = null,
                modifier = Modifier
                    .size(180.dp)
                    .padding(bottom = 10.dp) // 跟老師範例一樣的間距
            )

            Text("瑪利亞基金會服務大考驗", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            Text("作者：資管二A 黃婉凌", fontSize = 15.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            Text("螢幕大小：1080.0×1920.0", fontSize = 15.sp, color = Color.Black)
            Spacer(modifier = Modifier.height(10.dp))
            Text("成績：0分", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Red)
        }
    }
}