package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.Task
import kotlinx.android.synthetic.main.task_item_list.view.*

class TaskAdapter(var passTheData: PassTheData):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>()  {

    private var dataList = emptyList<Task>()


    fun updateDataList(mDataList:List<Task>){
        dataList=mDataList
        notifyDataSetChanged()
    }

    inner class TaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView), View.OnClickListener{
        val teskText     = itemView.taskTv
        val checkTask    = itemView.checkBoxTask
        val idText       = itemView.idTv
        val itemView     = itemView.setOnClickListener(this)
        override fun onClick(v: View?) {
            passTheData.passTheData(dataList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_item_list,parent,false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
       val mTask: Task = dataList[position]
        holder.teskText.text = mTask.task
        holder.checkTask.isChecked = mTask.completeTask
        holder.idText.text = mTask.id.toString()
    }

    override fun getItemCount() = dataList.size

    interface PassTheData{
        fun passTheData(mTask: Task)

        }

    }

