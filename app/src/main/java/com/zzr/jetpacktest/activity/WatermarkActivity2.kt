package com.zzr.jetpacktest.activity

import android.graphics.BitmapFactory
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ImageUtils
import com.zzr.jetpacktest.R
import com.zzr.jetpacktest.databinding.ActivityWatermark2Binding
import com.zzr.jetpacktest.entity.hilt.Truck
import com.zzr.jetpacktest.module_logic.network.ApiService
import com.zzr.jetpacktest.viewmodel.TestViewModel2
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class WatermarkActivity2 : BaseActivity<ActivityWatermark2Binding>() {

    @Inject
    lateinit var truck: Truck

    @Inject
    lateinit var retrofit: Retrofit

    private val viewModel: TestViewModel2 by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_watermark2)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.pic001)
        val width = bitmap.width
        val height = bitmap.height
        //测量文字
        val waterText = "我是一个水印"
        val paint = Paint()
//        val pixel = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10.0f, context.resources.displayMetrics)
//        paint.textSize = pixel
        val rect = Rect()
        paint.getTextBounds(waterText, 0, waterText.length, rect)

        val result = ImageUtils.addTextWatermark(
            bitmap, waterText, 40,
            ContextCompat.getColor(this, R.color.color_fff),
            (width - rect.width()) / 2f, 60f
        )
        Log.i("TAG", "onCreate: $result")
        binding.ivShow.setImageBitmap(result)

        truck.deliver()

        //访问网络
        lifecycleScope.launch(Dispatchers.Main) {
            val apiService = retrofit.create(ApiService::class.java)
            binding.tvMsg.text = withContext(Dispatchers.IO) {
                apiService.articleList(0).data.datas[0].title
            }
        }

//        viewModel.fetchDocs()
        viewModel.getTestDocs().observe(this) {
            binding.tvMsg2.text = it
        }

        viewModel.article.observe(this) {
            binding.tvMsg3.text = it[1].title
        }

        binding.edittext.let {
            it.setOnEditorActionListener { _, actionId, event ->
                if ((actionId == EditorInfo.IME_ACTION_SEARCH)) {

                    viewModel.searchFlow.value = it.text.toString()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

            it.addTextChangedListener { _ ->

                viewModel.searchFlow.value = it.text.toString()
            }
        }

        viewModel.searchResult.observe(this){
            binding.tvMsg3.text = it[2].title
        }
    }
}