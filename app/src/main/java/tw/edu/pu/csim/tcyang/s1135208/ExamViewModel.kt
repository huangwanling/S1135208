package tw.edu.pu.csim.tcyang.s1135208

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class ExamViewModel : ViewModel() {
    var screenWidth = mutableStateOf(0)
    var screenHeight = mutableStateOf(0)
    var fallingX = mutableStateOf(0f)
    var fallingY = mutableStateOf(-300f)
    var fallingType = mutableStateOf(0)
    var isFalling = mutableStateOf(false)
    var score = mutableStateOf(0)
    var message = mutableStateOf("")

    private var job: Job? = null

    fun startFalling() {
        if (job?.isActive == true) return
        fallingType.value = (0..3).random()
        fallingX.value = screenWidth.value / 2f - 150f
        fallingY.value = -300f
        isFalling.value = true

        job = CoroutineScope(Dispatchers.Main).launch {
            while (isFalling.value) {
                delay(16)
                fallingY.value += 20f
                if (fallingY.value > screenHeight.value - 500) checkCollision()
            }
        }
    }

    fun checkCollision() {
        isFalling.value = false
        job?.cancel()

        val fx = fallingX.value + 150f
        val fy = fallingY.value + 150f

        val correct = when (fallingType.value) {
            0 -> fx < 400 && fy > screenHeight.value / 2 - 200
            1 -> fx > screenWidth.value - 400 && fy > screenHeight.value / 2 - 200
            2 -> fx < 400 && fy > screenHeight.value - 500
            3 -> fx > screenWidth.value - 400 && fy > screenHeight.value - 500
            else -> false
        }

        if (correct) {
            score.value += 1
            message.value = when (fallingType.value) {
                0 -> "正確！極早期療育"
                1 -> "正確！離島服務"
                2 -> "正確！極重多障"
                3 -> "正確！輔具服務"
                else -> ""
            }
        } else {
            score.value -= 1
            message.value = "錯誤！"
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            message.value = ""
            startFalling()
        }
    }

    fun move(dx: Float) {
        fallingX.value = (fallingX.value + dx).coerceIn(0f, screenWidth.value - 300f)
    }
}