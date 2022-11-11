package com.gl4.tp2mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.gl4.tp2_presenceapp.R

class StudentsAdapter(private val data: ArrayList<Student>) : RecyclerView.Adapter<StudentsAdapter.ViewHolder>(), Filterable {

    var dataFilterList : ArrayList<Student> = data

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val image : ImageView
        val text : TextView
        val checkBox : CheckBox
        init {
            image = itemView.findViewById(R.id.studentImageView)
            text = itemView.findViewById(R.id.nameTextView)
            checkBox = itemView.findViewById(R.id.presentCheckBox)
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.text = "${dataFilterList[position].nom} ${dataFilterList[position].prenom}"
        if(dataFilterList[position].genre == "F"){
            holder.image.setImageResource(R.drawable.woman)
        }
        else{
            holder.image.setImageResource(R.drawable.man)
        }
        holder.checkBox.isChecked = dataFilterList[position].present

        holder.checkBox.setOnClickListener{

        }

    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if(charSearch.isEmpty()) {
                    dataFilterList = data
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in data) {
                        if (student.present == charSearch.toBoolean()) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Student>
                notifyDataSetChanged()
            }

        }
    }


}