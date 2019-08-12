package com.example.twitterclone

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.twitterclone.fragments.HomeFragment
import com.example.twitterclone.fragments.MyActivityFragment
import com.example.twitterclone.fragments.SearchFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val userid = firebaseAuth.currentUser?.uid
    //fragments
    private val homeFragment = HomeFragment()
    private val searchFragment = SearchFragment()
    private val myActivityFragment = MyActivityFragment()

    private var sectionPagerAdapter: SectionPagerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sectionPagerAdapter = SectionPagerAdapter(supportFragmentManager)
        container.adapter = sectionPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
        tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {

            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {

            }

        })
    }



    inner class SectionPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> homeFragment
                1 -> searchFragment
                else -> myActivityFragment
            }
        }
        override fun getCount(): Int = 3
    }

    // on logout

    fun onLogout(v: View) {
        firebaseAuth.signOut()
        if(userid == null) {
            startActivity(LoginActivity.newIntent(this))
            finish()
        }


    }

    // check if user is logged in or not ,if not then send to Login screen
    override fun onResume() {
        super.onResume()
        startActivity(LoginActivity.newIntent(this))
        finish()
    }


    companion object {
        fun newIntent(context: Context) = Intent(context, HomeActivity::class.java)
    }


}
