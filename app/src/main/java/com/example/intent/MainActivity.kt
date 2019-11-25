package com.example.intent

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.net.HttpCookie

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSend.setOnClickListener {
            sendMessage()
        }

        buttonCallMe.setOnClickListener {
            dialPhone()
        }
    }

    private fun dialPhone(){
        //Implicit Intent
        val intent = Intent(Intent.ACTION_VIEW)
        val phone: String = "tel:0123456789" //can change to facebook link - "http:facebook.xxxxxx"
        val facebook: String = "https://www.facebook.com/vinsonlau.weiping"

        //Check package manager for app to handle an intent
        intent.setData(Uri.parse(phone))
        if(intent.resolveActivity(packageManager) !== null){
            startActivity(intent)
        }

        
    }

    private fun sendMessage(){
        //Explicit Intent
        val intent = Intent(this,SecondActivity::class.java);

        val message = editTextMessage.text.toString()
        val luckyNum = Integer.parseInt(editTextMyLuckyNumber.text.toString())
        intent.putExtra(KEY,message)
        intent.putExtra(SECOND_KEY,luckyNum)
        //startActivity(intent) //An intent without return value
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(REPLY).toString()
                textViewReply.text = String.format("%s %s", getString(R.string.reply_hint), reply)
            }
        }
    }

    companion object{
        const val KEY = "com.example.intent.KEY"
        const val SECOND_KEY = "com.example.intent.SECOND_KEY"
        const val REPLY = "com.example.intent.REPLY"
        const val REQUEST_CODE = 1
    }
}
