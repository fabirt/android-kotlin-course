package fabirt.cursoandroid.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        done_button.setOnClickListener {
            addNickname(it)
        }

        nickname_edit.editText?.setOnEditorActionListener { v, actionId, event ->
            nickname_edit.editText?.clearFocus()
            false
        }
    }

    private fun addNickname(view: View) {
        val text = nickname_edit.editText?.text
        if (text != null && text.isNotEmpty()) {
            nickname_edit.error = null
            nickname_text.text = "Hi, $text."
            nickname_edit.visibility = View.GONE
            view.visibility = View.GONE
            nickname_text.visibility = View.VISIBLE

            // Hide the keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        } else {
            nickname_edit.error = "Please enter your nickname"
        }
    }
}
