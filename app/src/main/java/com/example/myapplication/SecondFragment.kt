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
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_second.*
import java.lang.Integer.toString
import kotlin.Unit.toString
import com.example.myapplication.database.Task as Task1

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */




class SecondFragment : Fragment() {
    lateinit var mViewModel: TaskViewModel
    private var idTask: Int? = null
    var name: String    = ""
    var cuanty: Int     = 0
    var price: Int      = 0
    var subtotal: Int   = 0
    var total: Int      =0


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
                editTextTask.setText(it.NameOfItem)
//                editTextTask2.setText(it.Price)
//                editTextTask3.setText(it.Cuantity)
//                editTextTask4.setText(it.Subtotal)
//                editTextTask5.setText(it.Total)
            })
        }

        btnSave.setOnClickListener() {
             name       = editTextTask.text.toString()
             price      = Integer.valueOf(editTextTask2.text.toString())
             cuanty     = Integer.valueOf(editTextTask3.text.toString())
             subtotal   = Integer.valueOf(editTextTask4.text.toString())
             total      = Integer.valueOf(editTextTask5.text.toString())

            if (idTask != null) {
                val mTask = Task1(
                    id = idTask!!, NameOfItem = name, Cuantity = cuanty, Price = price, Subtotal = subtotal, Total = total,
                )
                mViewModel.updateTask(mTask)
            } else {
                if (name.isNotEmpty()) {
                    val mTask = Task1(NameOfItem = name, Cuantity = cuanty, Price = price, Subtotal = subtotal, Total = total,
                    )
                    mViewModel.insertTask(mTask)
                } else {
                    Toast.makeText(context, "No debe estar Vacio", Toast.LENGTH_SHORT).show()
                }
            }
            Toast.makeText(context, "Guardado en ${id}", Toast.LENGTH_SHORT).show()
        }
        btnResum.setOnClickListener {
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        btncalcular.setOnClickListener(){
            cuanty         = Integer.valueOf(editTextTask3.text.toString())
            price          = Integer.valueOf(editTextTask2.text.toString())
//          val  res       = cuanty*price
//          val resultado  = res.toString()
//          editTextTask4.setText(resultado)
            editTextTask4.setText((cuanty *price).toString())
            editTextTask5.setText((cuanty *price).toString())
        }

    }
}