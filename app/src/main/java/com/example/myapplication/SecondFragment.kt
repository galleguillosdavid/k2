package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.myapplication.BuildConfig.DEBUG
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.task_item_list.*
import com.example.myapplication.database.Task as Task1

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    lateinit var mViewModel: TaskViewModel
    private var idTask: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        arguments?.let {
            idTask = it.getInt("id")
            Log.d("OBJ", idTask.toString())
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        idTask?.let {
            mViewModel.getOneTaskById(it).observe(viewLifecycleOwner, Observer {
                Log.d("OBJ_LIV", it.task)
                editTextTask.setText(it.task)
                checkBox.isChecked = it.completeTask
            })

        }

        saveBtn.setOnClickListener() {
            val textTask = editTextTask.text.toString()
            val checkBox = checkBox.isChecked

            if (idTask != null) {
                val mTask = Task1(task = textTask, completeTask = checkBox, id = idTask!!)
                mViewModel.updateTask(mTask)
            } else {

                if (textTask.isNotEmpty()) {
                    val mTask = Task1(task = textTask, completeTask = checkBox)
                    mViewModel.insertTask(mTask)

                } else {
                    Toast.makeText(context, "No debe estar Vacio", Toast.LENGTH_SHORT).show()

                }
            }
            view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }
}
