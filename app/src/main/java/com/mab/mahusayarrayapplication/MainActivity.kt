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
    private var displayText: TextView? = null
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
        inputName = findViewById(R.id.inputName)
        displayText = findViewById(R.id.displayText)
        listView?.setOnItemClickListener(OnItemClickListener { adapterView, view, position, l ->
            val student = adapter!!.getItem(position)
            displayText?.setText("Selected Student: $student")
        })
    }

    fun addStudent(view: View?) {
        val name = inputName!!.text.toString().trim { it <= ' ' }
        if (!name.isEmpty()) {
            studentList!!.add(name)
            adapter!!.notifyDataSetChanged()
            inputName!!.setText("")
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
        }
    }
}