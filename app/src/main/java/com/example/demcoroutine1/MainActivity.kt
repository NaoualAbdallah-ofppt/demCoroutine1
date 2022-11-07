package com.example.demcoroutine1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val montag:String="MonTag"
    lateinit var txt:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txt=findViewById(R.id.txt) as TextView
        Log.i(montag, Thread.currentThread().name)
        GlobalScope.launch(Dispatchers.IO) {
            Log.i(montag, "Coroutine1")
            Log.i(montag,fctlourde())
            withContext(Dispatchers.Main)
            {
                txt.text = "fct lourde"
            }

        }
        GlobalScope.launch(Dispatchers.IO) {
            Log.i(montag, "Coroutine2")
            Log.i(montag,fcttreslourde())
            withContext(Dispatchers.Main)
            {
                txt.text = "fct très lourde"
            }
        }


        Log.i(montag, "Je suis thread main")
txt.text="Hello main"
    }
    suspend fun fctlourde():String
    {
        //on va essayer de simuler une action lourde qui a besoin de
        //10s pour s'exécuter
        // en retardant la coroutine
        delay(10000L)
        //delay ne peut être utilisée qu'avec des méthode suspend
        return "Enfin j'ai fini fct lourde"
    }
    suspend fun fcttreslourde():String
    {

        delay(20000L)
        return "Enfin j'ai fini la fonction très lourde"
    }
}