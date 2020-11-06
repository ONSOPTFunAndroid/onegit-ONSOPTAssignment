package com.hjh96.sopt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fragment.*

class FragmentActivity : AppCompatActivity() {
    var code = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragment1 = FirstFragment()
        val fragment2 = SecondFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment1).commit()

        btn_fragment_change.setOnClickListener {
            val transAction = supportFragmentManager.beginTransaction()
            when(code){
                1->{
                    transAction.replace(R.id.fragment_container, fragment2)
                    code = 2
                }
                2-> {
                    transAction.replace(R.id.fragment_container, fragment1)
                    code = 1
                }
            }
            transAction.commit()
        }
    }
}