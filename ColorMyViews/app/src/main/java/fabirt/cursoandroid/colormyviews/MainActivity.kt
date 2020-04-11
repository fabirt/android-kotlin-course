package fabirt.cursoandroid.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        val clickableViews: List<View> =
            listOf(
                box_one_text,
                box_two_text,
                box_three_text,
                box_four_text,
                box_five_text,
                first_button,
                second_button,
                third_button
            )
        for (view in clickableViews) {
            view.setOnClickListener {
                makeColored(it)
            }
        }
    }

    private fun makeColored(view: View) {
        when (view.id) {
            // Boxes using Color class colors for background
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background
            R.id.box_three_text -> view.setBackgroundResource(R.color.colorPrimaryDark)
            R.id.box_four_text -> view.setBackgroundResource(R.color.colorPrimaryLight)
            R.id.box_five_text -> view.setBackgroundResource(R.color.colorPrimary)
            R.id.first_button -> box_three_text.setBackgroundResource(R.color.colorSecondaryDark)
            R.id.second_button -> box_four_text.setBackgroundResource(R.color.colorSecondaryLight)
            R.id.third_button -> box_five_text.setBackgroundResource(R.color.colorSecondary)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
