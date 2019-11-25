package com.example.intent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //receive do at onCreate method
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.KEY)
        val luckyNum = intent.getIntExtra(MainActivity.SECOND_KEY, 0)

        textViewMyLuckyNumber.text = String.format("%s %s \n %s %s", getString(R.string.message), message,
            getString(R.string.lucky_number), luckyNum)

        buttonDone.setOnClickListener{
            if(!editTextReply.text.isEmpty()){
                val reply = editTextReply.text.toString()
                val intent = getIntent() //return the MainActivity intent
                intent.putExtra(MainActivity.REPLY,reply)

                //Inform the MainActivity that everything is OK
                setResult(Activity.RESULT_OK,intent)

            }else{
                setResult(Activity.RESULT_CANCELED)
            }




            finish()
        }
    }
}
