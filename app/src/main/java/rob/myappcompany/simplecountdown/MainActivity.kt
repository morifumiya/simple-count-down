package rob.myappcompany.simplecountdown

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var timer: CountDownTimer
    private var remainingTime: Long = 0 // 残り時間を後から入れるので一旦、0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1) 開始時間を設定
        val startTime: Long = 10000 // 10秒 時間はlong

        // 1) view取得
        val tv: TextView = findViewById(R.id.tv)
        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)
        val btnRestart: Button = findViewById(R.id.btnRestart)
        val btnReset: Button = findViewById(R.id.btnReset)
        tv.text = "${startTime / 1000}" // 開始時間を秒で表示

        // 2) ボタン有効無効
        btnStart.isEnabled = true
        btnStop.isEnabled = false
        btnRestart.isEnabled = false

        // 3) カウントダウンタイマーのオブジェクトを用意 // object 無名クラスを作るときの構文（まあCountDownTimer()とセットの決まり文句でおk）
//        val timer

        btnStart.setOnClickListener {
//            timer.start()
            // startTime でカウントダウン開始
            timer = countDownTimer(startTime).start()

            btnStart.isEnabled = false
            btnStop.isEnabled = true
            btnRestart.isEnabled = false
        }

        // ストップボタン
        btnStop.setOnClickListener {
            timer.cancel()

            btnStart.isEnabled = true
            btnStop.isEnabled = false
            btnRestart.isEnabled = true
        }

        // リスタートボタンを推した時の処理
        btnRestart.setOnClickListener {
            // remainingTime でカウントダウン開始
            timer = countDownTimer(remainingTime).start()

            btnStart.isEnabled = false
            btnStop.isEnabled = true
            btnRestart.isEnabled = false
        }

        // リセットボタン
        btnReset.setOnClickListener {
            timer.cancel()
            remainingTime = 0
            tv.text = "${startTime / 1000}" // 開始時間を秒で表示

            btnStart.isEnabled = true
            btnStop.isEnabled = false
            btnRestart.isEnabled = false
        }
    }

    // スタートとリスタートの同じ処理を関数にまとめる
    private fun countDownTimer(st: Long): CountDownTimer {
        val tv: TextView = findViewById(R.id.tv)
        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)
        val btnRestart: Button = findViewById(R.id.btnRestart)

        return object : CountDownTimer(st, 1000) {
            // 途中経過・のこち時間
            override fun onTick(p0: Long) {
//                TODO("Not yet implemented")
                tv.text = "${p0 / 1000}" // 開始時間を秒で表示
                remainingTime = p0 // 残り時間を代入
            }

            // 終了設定
            override fun onFinish() {
//                TODO("Not yet implemented")
                tv.text = "タイムアップ"

                // スタートボタンのみ有効
                btnStart.isEnabled = false
                btnStop.isEnabled = false
                btnRestart.isEnabled = false
            }
        }
    }
}
