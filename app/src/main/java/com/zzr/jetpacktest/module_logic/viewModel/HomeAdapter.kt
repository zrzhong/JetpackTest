package com.zzr.jetpacktest.module_logic.viewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.module_logic.model.Article

class HomeAdapter(private val itemRes: Int) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private val articleList = mutableListOf<Article>()

    fun setData(data: ArrayList<Article>) {
        this.articleList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(itemRes, parent, false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int = articleList.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val article = articleList[position]
        holder.tvTitle.text = article.title
        holder.tvDate.text = article.niceDate
        holder.tvAuthor.text = if (article.shareUser.isNullOrEmpty()) "佚名" else article.shareUser
    }

    inner class HomeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val tvAuthor: TextView = view.findViewById(R.id.tvAuthor)
    }
}