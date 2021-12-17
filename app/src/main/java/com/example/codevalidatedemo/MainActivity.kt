package com.example.codevalidatedemo

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.codevalidatedemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        codeListener()
    }

    private fun codeListener() {
        clearCode()

        binding.one.addTextChangedListener {
            if (it?.length == 1) {
                binding.two.requestFocus()
                binding.oneLine.setBackgroundColor(Color.parseColor("#7E7E7E"))
            } else {
                binding.oneLine.setBackgroundColor(Color.parseColor("#BCBCBC"))
            }
        }

        binding.two.addTextChangedListener {
            if (it?.length == 1) {
                binding.three.requestFocus()
                binding.twoLine.setBackgroundColor(Color.parseColor("#7E7E7E"))
            } else {
                binding.twoLine.setBackgroundColor(Color.parseColor("#BCBCBC"))
            }
        }

        binding.three.addTextChangedListener {
            if (it?.length == 1) {
                binding.four.requestFocus()
                binding.threeLine.setBackgroundColor(Color.parseColor("#7E7E7E"))
            } else {
                binding.threeLine.setBackgroundColor(Color.parseColor("#BCBCBC"))
            }
        }

        binding.four.addTextChangedListener {
            if (it?.length == 1) {
                binding.four.clearFocus()
                SoftKeyboardUtils.showOrHideSoftKeyboard(this)
                binding.fourLine.setBackgroundColor(Color.parseColor("#7E7E7E"))
                Toast.makeText(this, "${binding.one.text}${binding.two.text}${binding.three.text}${binding.four.text}", Toast.LENGTH_LONG).show()
            } else {
                binding.fourLine.setBackgroundColor(Color.parseColor("#BCBCBC"))
            }
        }
    }

    private fun clearCode() {
        binding.two.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                binding.one.requestFocus()
            }
            false
        }
        binding.three.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                binding.two.requestFocus()
            }
            false
        }
        binding.four.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                binding.three.requestFocus()
            }
            false
        }

        binding.one.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.one.text?.clear()
                binding.two.text?.clear()
                binding.three.text?.clear()
                binding.four.text?.clear()
            }
        }

        binding.two.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.one.text.isNullOrEmpty()) {
                    binding.one.requestFocus()
                    SoftKeyboardUtils.showOrHideSoftKeyboard(this)
                } else {
                    binding.two.text?.clear()
                    binding.three.text?.clear()
                    binding.four.text?.clear()
                }
            }
        }

        binding.three.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.one.text.isNullOrEmpty()) {
                    binding.one.requestFocus()
                    SoftKeyboardUtils.showOrHideSoftKeyboard(this)
                } else {
                    binding.three.text?.clear()
                    binding.four.text?.clear()
                }
            }
        }

        binding.four.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                if (binding.one.text.isNullOrEmpty()) {
                    binding.one.requestFocus()
                    SoftKeyboardUtils.showOrHideSoftKeyboard(this)
                } else {
                    binding.four.text?.clear()
                }
            }
        }
    }
}

object SoftKeyboardUtils {

    fun showOrHideSoftKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun showSoftKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(
            activity.currentFocus,
            InputMethodManager.SHOW_FORCED
        )
    }

    fun hideSoftKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            activity.currentFocus?.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    fun getStateKeyboard(activity: Activity): Boolean {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isActive
    }
}