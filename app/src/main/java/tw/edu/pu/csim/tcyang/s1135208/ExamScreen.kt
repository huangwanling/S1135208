package tw.edu.pu.csim.tcyang.s1135208

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// 引入 LocalDensity 才能進行 Dp 和像素的轉換
import androidx.compose.ui.platform.LocalDensity

@Composable
fun ExamScreen() {
    val vm = remember { ExamViewModel() }

    // 將 Box 替換為 BoxWithConstraints
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .onSizeChanged {
                // onSizeChanged 依然保留，用於獲取屏幕大小給 ViewModel 使用
                vm.screenWidth.value = it.width
                vm.screenHeight.value = it.height
                if (!vm.isFalling.value) vm.startFalling()
            }
    ) {
        // 獲取螢幕高度，使用 Dp 單位
        val screenHeightDp: Dp = this.maxHeight

        // 計算螢幕高度一半的位置 (Dp 單位)
        val halfScreenHeightDp: Dp = screenHeightDp / 2

        // 角色圖示的大小 (Dp 單位)
        val roleSizeDp = 300.dp

        // 計算嬰幼兒和兒童圖示所需的垂直偏移量。
        // 目標：圖示的底部對齊 halfScreenHeightDp。
        // Alignment.TopStart / Alignment.TopEnd 的原點是 Box 的左上角。
        // 我們使用 Alignment.TopStart 搭配 offsetY 來精確定位。
        val offsetTopToHalf: Dp = halfScreenHeightDp - roleSizeDp

        // happy logo（照你原本位置）
        // ... (保持不變)
        Image(
            painter = painterResource(R.drawable.happy),
            contentDescription = null,
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.TopCenter)
                .padding(top = 80.dp)
        )

        // 中間四行字 15sp（完全照你要求）
        // ... (保持不變)
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("瑪利亞基金會服務大考驗", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(Modifier.height(10.dp))
            Text("作者：資管二A 黃婉凌", fontSize = 15.sp, color = Color.Black)
            Spacer(Modifier.height(10.dp))
            Text("螢幕大小：1080.0×1920.0", fontSize = 15.sp, color = Color.Black)
            Spacer(Modifier.height(10.dp))
            Text("成績：${vm.score.value}分", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.Red)
        }

        // --- 第三題：四個角色精準對齊（完全照老師圖） ---

        // role0：嬰幼兒 → 左邊，底部對齊螢幕 1/2
        Image(
            painter = painterResource(R.drawable.role0),
            contentDescription = "嬰幼兒",
            modifier = Modifier
                .size(roleSizeDp)
                // 貼齊 Box 的左邊 (Start)
                .align(Alignment.TopStart)
                // 垂直偏移量：讓圖示的底部對齊螢幕一半
                .offset(x = 20.dp, y = offsetTopToHalf)
        )

        // role1：兒童 → 右邊，底部對齊螢幕 1/2
        Image(
            painter = painterResource(R.drawable.role1),
            contentDescription = "兒童",
            modifier = Modifier
                .size(roleSizeDp)
                // 貼齊 Box 的右邊 (End)
                .align(Alignment.TopEnd)
                // 垂直偏移量：讓圖示的底部對齊螢幕一半
                .offset(x = (-20).dp, y = offsetTopToHalf)
        )

        // role2：成人 → 左邊，底部對齊螢幕底部
        Image(
            painter = painterResource(R.drawable.role2),
            contentDescription = "成人",
            modifier = Modifier
                .size(roleSizeDp)
                // 貼齊 Box 的左邊 (Start)，底部 (Bottom)
                .align(Alignment.BottomStart)
                .offset(x = 20.dp) // 貼齊左邊後，再往右偏移 20.dp
        )

        // role3：一般民眾 → 右邊，底部對齊螢幕底部
        Image(
            painter = painterResource(R.drawable.role3),
            contentDescription = "一般民眾",
            modifier = Modifier
                .size(roleSizeDp)
                // 貼齊 Box 的右邊 (End)，底部 (Bottom)
                .align(Alignment.BottomEnd)
                .offset(x = (-20).dp) // 貼齊右邊後，再往左偏移 20.dp
        )

        // 掉落服務圖示 + 拖曳（保持你原本功能）
        // ... (保持不變，但注意 BoxWithConstraints 的內容範圍)
        val services = listOf(R.drawable.service0, R.drawable.service1, R.drawable.service2, R.drawable.service3)
        if (vm.isFalling.value) {
            Image(
                painter = painterResource(services[vm.fallingType.value]),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .offset(vm.fallingX.value.dp, vm.fallingY.value.dp)
                    .pointerInput(Unit) {
                        detectDragGestures { _, drag -> vm.move(drag.x) }
                    }
            )
        }

        // 正解/錯誤紅字
        // ... (保持不變)
        if (vm.message.value.isNotEmpty()) {
            Text(
                vm.message.value,
                color = Color.Red,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}