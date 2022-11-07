package com.example.demcoroutine1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val montag:String="MonTag"
    lateinit var txt:TextView
    lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt=findViewById(R.id.txt) as TextView
        btn=findViewById(R.id.btn) as Button
        btn.setOnClickListener{
            val int =Intent(applicationContext,testActivity::class.java)
            startActivity(int)
        }
        Log.i(montag, Thread.currentThread().name)
        GlobalScope.launch(Dispatchers.IO) {
            Log.i(montag, "Coroutine1")
            fctlourde()


        }



        Log.i(montag, "Je suis thread main")
txt.text="Hello main"
    }
    suspend fun fctlourde() {
        //on va essayer de simuler une action lourde qui a besoin de
        //10s pour s'exécuter
        // en retardant la coroutine
        for (i in 1..1000)
        {    delay(1000L)
        //delay ne peut être utilisée qu'avec des méthode suspend
        Log.i(montag,"Tour : " + i)
        }
    }

}