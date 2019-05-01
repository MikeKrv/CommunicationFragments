package com.example.communicationfragments

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders

class HostActivity : AppCompatActivity(), FragmentA.OnButtonClickListener {
    private var counter: Int = 0

    private val portrait
        get() = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    private val landscape
        get() = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    private val sharedViewModel by lazy {
        ViewModelProviders.of(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_host)
        counter =  sharedViewModel.liveCounter.value ?: 0

        addFragments()

    }

    private fun addFragments() {
        if (portrait) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragments_container, FragmentA.newInstance())
                .commit()
        }

        if(landscape){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentA_container, FragmentA.newInstance())
                .replace(R.id.fragmentB_container, FragmentB.newInstance())
                .commit()
        }
    }

    private fun replaceFragments() {
        if (portrait){
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                .addToBackStack(null)
                .replace(R.id.fragments_container, FragmentB.newInstance())
                .commit()
        }

    }

    override fun onButtonClick() {
        sharedViewModel.liveCounter.postValue(++counter)
        replaceFragments()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (landscape)finish()
    }
}
