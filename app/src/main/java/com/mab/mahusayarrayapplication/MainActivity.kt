package com.mab.mahusayarrayapplication

import com.mab.mahusayarrayapplication.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var studentList: ArrayList<String>? = null
    private var adapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null
    private var inputName: EditText? = null
    private var inputEmailAddress: EditText? = null
    private var inputPassword: EditText? = null
    private var displayText: TextView? = null
    private var displayTextEmail: TextView? = null
    private var displayTextPassword: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentList = ArrayList()
        adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            studentList!!
        )
        listView = findViewById(R.id.listView)
        listView?.setAdapter(adapter)

        inputName = findViewById(R.id.editName)
        inputEmailAddress = findViewById(R.id.editEmailAddress)
        inputPassword = findViewById(R.id.editPassword)

        displayText = findViewById(R.id.displayText)
        displayTextEmail = findViewById(R.id.displayTextEmail)
        displayTextPassword = findViewById(R.id.displayTextPassword)


        listView?.setOnItemClickListener(OnItemClickListener { adapterView, view, position, l ->
            val student = adapter!!.getItem(position)
            displayText?.setText("Selected Student: $student")
        })
    }

    fun addStudent(view: View?) {
        val name = inputName!!.text.toString().trim()
        val email = inputEmailAddress!!.text.toString().trim()
        val password = inputPassword!!.text.toString().trim()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }
        // Display the data
        val existingText = displayText?.text.toString()
        val existingEmailText = displayTextEmail?.text.toString()
        val existingPasswordText = displayTextPassword?.text.toString()

        displayText?.text = "$existingText\nName: $name"
        displayTextEmail?.text = "$existingEmailText\nEmail: $email"
        displayTextPassword?.text = "$existingPasswordText\nPassword: $password"

        // Optionally, you can clear the input fields after adding the student
        inputName?.setText("")
        inputEmailAddress?.setText("")
        inputPassword?.setText("")

    }
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}