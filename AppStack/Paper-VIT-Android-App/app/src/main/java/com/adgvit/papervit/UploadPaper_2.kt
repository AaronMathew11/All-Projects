package com.adgvit.papervit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_upload_paper2.*

class UploadPaper_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_paper2)
        uploadbtn.isClickable=false
        uploadbtn.alpha=0.6f
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            setProgressWithAnimation(100f, 4000) //3secs
            // Set Progress Max
            progressMax = 100f
            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

        circularProgressBar.onProgressChangeListener = { progress ->
            progresspercentage.text="${progress.toInt()}%"
            if (circularProgressBar.progress==100f)
            {
                text1.setText("Upload Complete")
                text2.setText("Your Paper is ready to Submit. Once reviewed,\n it'll be available on the App ")
                uploadbtn.isClickable=true
                uploadbtn.alpha=1f
            }
        }
    }}