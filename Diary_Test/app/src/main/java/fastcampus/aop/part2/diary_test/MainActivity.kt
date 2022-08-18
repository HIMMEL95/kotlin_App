package fastcampus.aop.part2.diary_test

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private val firstNum: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.firstNum).apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val secondNum: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.secondNum).apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val thirdNum: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.thirdNum).apply {
            minValue = 0
            maxValue = 9
        }
    }

    private val openButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.openButton)
    }

    private val changePasswordButton: AppCompatButton by lazy {
        findViewById<AppCompatButton>(R.id.changePasswordButton)
    }

    private var changePass = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstNum
        secondNum
        thirdNum

        openButton.setOnClickListener {
            if (changePass) {
                Toast.makeText(this, "비밀번호 변경 중 입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${firstNum.value}${secondNum.value}${thirdNum.value}"

            if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {

            } else {
                showErrorAlertDialog()
            }
        }

        changePasswordButton.setOnClickListener {

            val passwordPreferences = getSharedPreferences("password", Context.MODE_PRIVATE)
            val passwordFromUser = "${firstNum.value}${secondNum.value}${thirdNum.value}"

            if (changePass) {
                passwordPreferences.edit(true) {
                    putString("password", passwordFromUser)
                }

                changePass = false
                changePasswordButton.setBackgroundColor(Color.BLACK)
            } else {

                if (passwordPreferences.getString("password", "000").equals(passwordFromUser)) {
                    changePass = true
                    Toast.makeText(this, "변경할 패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    changePasswordButton.setBackgroundColor(Color.RED)
                } else  {
                    showErrorAlertDialog()
                }
            }
        }
    }

    private fun showErrorAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("실패!")
            .setMessage("비밀번호가 잘못되었습니다.")
            .setPositiveButton("확인") {_, _ ->}
            .create()
            .show()
    }
}