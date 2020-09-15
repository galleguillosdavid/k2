package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.database.Task
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(),TaskAdapter.PassTheData {

    lateinit var viewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val task1= Task (101,"prueba 1",true)
//        viewModel.insertTask(task1)
        //instanciar el elemento visual RV
       val mRecyclerView = recyclerView

        // Instanciar el objeto de la clase adapter
        val mAdapter = TaskAdapter(this)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.AllTask.observe(viewLifecycleOwner, Observer { Log.d("Saludo", it.toString())
            mAdapter.updateDataList(it)})


        fab.setOnClickListener{
            findNavController().navigate((R.id.action_FirstFragment_to_SecondFragment))
        }

      //  view.findViewById<Button>(R.id.btnCreate).setOnClickListener {
        btnCreate.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        }
    }

    override fun passTheData(mTask: Task) {
        val mBundle = Bundle()
        mBundle.putInt("id", mTask.id)
        Toast.makeText(context,mTask.id.toString(),Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, mBundle)
    }
}