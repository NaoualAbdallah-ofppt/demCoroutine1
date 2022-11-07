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
            Log.i(montag, "Coroutine")

            var data1= async { fctlourde() }
            var data2=async{fcttreslourde()}
            //pour exécuter ces deux méthodes en parallèle
            //le retour n'est pas un String c'est un deferred String
            //c'est pourquoi on a besoin d'utiliser await
            withContext(Dispatchers.Main)
            {
                val txt1=findViewById(R.id.txt) as TextView
                txt1.text = data1.await()
            }
            withContext(Dispatchers.Main)
            {
                val txt2 = findViewById(R.id.txt) as TextView
                txt2.text = data2.await()
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