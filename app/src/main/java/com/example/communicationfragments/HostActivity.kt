package com.example.communicationfragments

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import kotlin.properties.Delegates

class HostActivity : AppCompatActivity(), FragmentA.OnButtonClickListener {
    private var counter: Int = 0

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
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentA_container, FragmentA.newInstance())
            .commit()

        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentB_container, FragmentB.newInstance())
                .commit()
        }
    }

    private fun replaceFragments() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right)
                .addToBackStack(null)
                .replace(R.id.fragmentA_container, FragmentB.newInstance())
                .commit()
        }

    }

    override fun onButtonClick() {
        sharedViewModel.liveCounter.postValue(++counter)
        replaceFragments()
    }

}
