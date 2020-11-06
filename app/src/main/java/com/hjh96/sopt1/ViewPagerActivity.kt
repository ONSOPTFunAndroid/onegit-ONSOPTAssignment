package com.hjh96.sopt1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlin.properties.Delegates

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)

        sample_bottom_viewpager.adapter = viewPagerAdapter

        // ViewPager slide 시 BottomNavi 변경
        sample_bottom_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                sample_bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        // 바텀 네비게이션 세팅
        sample_bottom_navi.setOnNavigationItemReselectedListener {
            var index by Delegates.notNull<Int>()
            when (it.itemId) {
                R.id.menu_profile -> index = 0
                R.id.menu_portfolio -> index = 1
                R.id.menu_setting -> index = 2
            }
            sample_bottom_viewpager.currentItem = index
            true
        }
    }
}