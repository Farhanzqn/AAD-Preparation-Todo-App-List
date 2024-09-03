package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTitle: TextInputEditText
    private lateinit var detailDescription: TextInputEditText
    private lateinit var detailDueDate: TextInputEditText
    private lateinit var btnDelete: Button
    private lateinit var viewModelDetail: DetailTaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        detailTitle = findViewById(R.id.detail_ed_title)
        detailDescription = findViewById(R.id.detail_ed_description)
        detailDueDate = findViewById(R.id.detail_ed_due_date)
        btnDelete = findViewById(R.id.btn_delete_task)

        viewModelDetail =
            ViewModelProvider(this,ViewModelFactory
                .getInstance(this))[DetailTaskViewModel::class.java]

        viewModelDetail.setTaskId(intent.getIntExtra(TASK_ID,0))

        viewModelDetail.task.observe(this,{task ->
            if (task != null){
                detailTitle.setText(task.title)
                detailDescription.setText(task.description)
                detailDueDate.setText(DateConverter.convertMillisToString(task.dueDateMillis))
            }
        })
        btnDelete.setOnClickListener {
            viewModelDetail.deleteTask()
            onBackPressed()
        }
    }
}