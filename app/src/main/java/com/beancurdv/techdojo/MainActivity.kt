package com.beancurdv.techdojo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.beancurdv.techdojo.bean.Person

/**
 * 通过对ASM学习，对树的结构，有了比较深的理解；
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val p = Person().apply {
            name = "beancurdv"
            age = 10
        }
        findViewById<TextView>(R.id.tv_content).apply {
            text = p.toString()
            setOnClickListener {
                Person::class.java.getDeclaredField("sex").set(p,"女")
                // 检查静态变量初始值； TODO zfc 4 后续对构造方法修改，增加初始值
                val f = p.javaClass.getDeclaredField("n").get(null)
                val method = Person::class.java.getDeclaredMethod("getSex")
                val mv = method.invoke(p)

                Log.e("zfc","f = $f")
                Log.e("zfc","mv = $mv")
                Toast.makeText(this@MainActivity, "hello", 0).show()

                Thread() {
                    p.setName("zfc",true)
                }.start()
            }
        }
    }
}