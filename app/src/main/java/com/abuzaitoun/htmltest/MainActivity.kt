package com.abuzaitoun.htmltest

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.abuzaitoun.htmltest.ui.theme.HTMLTestTheme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HTMLTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Html("<h1>Heading 1</h1>\n" +
                            "<h2>Heading 2</h2>\n" +
                            "<h3>Heading 3</h3>\n" +
                            "<h4>Heading 4</h4>\n" +
                            "<h5>Heading 5</h5>\n" +
                            "<h6>Heading 6</h6><i>Italic text.</i>")
                }
                val mYear: Int
                val mMonth: Int
                val mDay: Int

                // Initializing a Calendar
                val mCalendar = Calendar.getInstance()

                // Fetching current year, month and day
                mYear = mCalendar.get(Calendar.YEAR)
                mMonth = mCalendar.get(Calendar.MONTH)
                mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

                mCalendar.time = Date()

                // Declaring a string value to
                // store date in string format
                val mDate = remember { mutableStateOf("") }

                val mDatePickerDialog = DatePickerDialog(
                    this,
                    { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                        mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
                    }, mYear, mMonth, mDay
                )
                mDatePickerDialog.show()
            }
        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HTMLTestTheme {
        TextHtml("<h1>Heading 1</h1>\n" +
                "<h2>Heading 2</h2>\n" +
                "<h3>Heading 3</h3>\n" +
                "<h4>Heading 4</h4>\n" +
                "<h5>Heading 5</h5>\n" +
                "<h6>Heading 6</h6>" )
    }
}
@Composable
fun Html(text: String) {
    AndroidView(factory = { context ->
        TextView(context).apply {
            setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY))
        }
    })
}

@Composable
fun TextHtml(html: String) {
    Text(text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString())
}






