package com.zzr.jetpacktest.module_logic.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.kotlin_test.showToast
import com.zzr.jetpacktest.module_logic.model.LoadState
import com.zzr.jetpacktest.module_logic.viewModel.HomeAdapter
import com.zzr.jetpacktest.module_logic.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : AppCompatActivity() {

    private lateinit var articleAdapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        rv.run {
            layoutManager = LinearLayoutManager(this@ArticleActivity)
            articleAdapter = HomeAdapter(R.layout.item_article)
            adapter = articleAdapter
        }

        loadArticle()
    }

    private fun loadArticle() {
//        viewModel.msg.observe(this) {
//            tvArticle.text = it
//        }
//        viewModel.getMsg()

        /*viewModel.articleData.observe(this) {
            if (it.errorCode == 0) {
                articleAdapter.setData(it.data.datas)
//                tvArticle.text = it.data.datas[0].title
            } else {
                "失败=${it.errorMsg}}".showToast(this)
            }
        }
        viewModel.loadState.observe(this) {
            when (it) {
                is LoadState.Fail -> Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show()
                is LoadState.Success -> Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                is LoadState.Loading -> Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.articleList(0)*/

        viewModel.articleLiveData.observe(this) {
            val article = it.getOrNull()
            if (article != null) {
                articleAdapter.setData(article)
            } else {
                "获取失败".showToast(this)
            }
        }
        viewModel.getArticle(1)
    }
}