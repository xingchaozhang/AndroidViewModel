package com.derry.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

// 绑定机制
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 300个字段
    // var number : Int = 0;

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("DDD", "onCreate: ")

        // myViewModel = MyViewModel()  不能直接实例化，因为如果能这样写，系统不可控了

        // 旧版本的写法，更新特别快（扩展性不强）
        // ViewModelProviders.of()

        // this == ViewModelStoreOwner,
        myViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(MyViewModel::class.java)

        // findv  text_number
        text_number.text = "${myViewModel.number}"

        // 点击事件 lambda
        btn_plus.setOnClickListener {
            text_number.text = "${++myViewModel.number}"
        }

        // 源码分析：ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())

        // 源码分析：.get(MyViewModel::class.java) 调用工厂ViewModelProvider.NewInstanceFactory() 反射实例化
    }

    // 扩展给 LoginA  MainActivity 子类保存临时数据用的
    override fun onRetainCustomNonConfigurationInstance(): Any? {
        Log.d("DDD", "横竖屏切换了 onRetainCustomNonConfigurationInstance: ")
        return "DDDDDDD"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DDD", "onDestroy: ")
    }

    // ams创建activity会把nc传给新建的activity
    // 在转屏情况时转屏在销毁前缓存NC里面 ， 转回来时候会从NC取出来
}