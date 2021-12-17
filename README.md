# Android 四格驗證碼或密碼

##### 四格驗證碼需要拆分為四個EditText輸入框，並且做一些判斷來控制跳下一格和刪除，還要持續監聽四個EditText。

---

#### 文章目錄
<ol>
    <li><a href="#a">UI</a></li>
    <li><a href="#b">監聽是否輸入</a></li>
    <li><a href="#c">監聽刪除</a></li>
	<li><a href="#d">程式碼範例</a></li>
	<li><a href="#e">效果展示</a></li>
	<li><a href="#f">Github</a></li>
</ol>

---

<a id="a"></a>
#### 1.UI
```XML
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.appcompat.widget.AppCompatTextView
        android:id="@+id/appCompatTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text="輸入驗證碼"
        android:textColor="#FE735F"
        android:textSize="40sp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <androidx.appcompat.widget.AppCompatEditText
        android:id="@+id/one"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="46dp"
        android:background="@null"
        android:cursorVisible="false"
        android:inputType="numberDecimal"
        android:maxLength="1"
        android:textColor="#707070"
        android:textSize="60sp"
        app:layout_constraintEnd_toEndOf="@+id/one_line"
        app:layout_constraintStart_toStartOf="@+id/one_line"
        app:layout_constraintTop_toBottomOf="@+id/appCompatTextView" />

    <View
        android:id="@+id/one_line"
        android:layout_width="40dp"
        android:layout_height="2dp"
        android:background="#BCBCBC"
        app:layout_constraintEnd_toStartOf="@+id/two_line"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/one" />

    <androidx.appcompat.widget.AppCompatEditText
        android:id="@+id/two"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="@null"
        android:cursorVisible="false"
        android:inputType="numberDecimal"
        android:maxLength="1"
        android:textColor="#707070"
        android:textSize="60sp"
        app:layout_constraintEnd_toEndOf="@+id/two_line"
        app:layout_constraintStart_toStartOf="@+id/two_line"
        app:layout_constraintTop_toTopOf="@+id/one" />

    <View
        android:id="@+id/two_line"
        android:layout_width="40dp"
        android:layout_height="2dp"
        android:background="#BCBCBC"
        app:layout_constraintEnd_toStartOf="@+id/three_line"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/one_line"
        app:layout_constraintTop_toBottomOf="@+id/two" />

    <androidx.appcompat.widget.AppCompatEditText
        android:id="@+id/three"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="@null"
        android:cursorVisible="false"
        android:inputType="numberDecimal"
        android:maxLength="1"
        android:textColor="#707070"
        android:textSize="60sp"
        app:layout_constraintEnd_toEndOf="@+id/three_line"
        app:layout_constraintStart_toStartOf="@+id/three_line"
        app:layout_constraintTop_toTopOf="@+id/two" />

    <View
        android:id="@+id/three_line"
        android:layout_width="40dp"
        android:layout_height="2dp"
        android:background="#BCBCBC"
        app:layout_constraintEnd_toStartOf="@+id/four_line"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/two_line"
        app:layout_constraintTop_toBottomOf="@+id/three" />

    <androidx.appcompat.widget.AppCompatEditText
        android:id="@+id/four"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:background="@null"
        android:cursorVisible="false"
        android:inputType="numberDecimal"
        android:maxLength="1"
        android:textColor="#707070"
        android:textSize="60sp"
        app:layout_constraintEnd_toEndOf="@+id/four_line"
        app:layout_constraintStart_toStartOf="@+id/four_line"
        app:layout_constraintTop_toTopOf="@+id/three" />

    <View
        android:id="@+id/four_line"
        android:layout_width="40dp"
        android:layout_height="2dp"
        android:background="#BCBCBC"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/three_line"
        app:layout_constraintTop_toBottomOf="@+id/four" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

<a id="b"></a>
#### 2.監聽是否輸入
```Kotlin
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
```

<a id="c"></a>
#### 3.監聽刪除
```Kotlin
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
```

<a id="d"></a>
#### 4.程式碼範例
```Kotlin
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
```

<a id="e"></a>
#### 5.效果展示
<a href="https://badgameshow.com/fly/wp-content/uploads/2021/12/Screenrecorder-2021-12-17-10-12-22-145.gif"><img src="https://badgameshow.com/fly/wp-content/uploads/2021/12/Screenrecorder-2021-12-17-10-12-22-145.gif" width="40%"/></a>

<a id="f"></a>
#### 6.Github
[Android 四格驗證碼或密碼 Github](https://github.com/MuHongWeiWei/CodeValidateDemo)
