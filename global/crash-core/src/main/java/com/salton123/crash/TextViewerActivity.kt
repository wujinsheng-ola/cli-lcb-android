package com.salton123.crash

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kt.executeByIo
import java.io.File

/**
 * @Time:2021/1/22 14:47
 * @Author:wujinsheng
 * @Description:
 */
class TextViewerActivity : Activity() {

    companion object {
        val FILE_KEY = "file_key"
    }

    private val mAdapter by lazy { TextViewerAdapterStyle() }
    private lateinit var recyclerView: RecyclerView
    private lateinit var targetFile: File
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        targetFile = intent?.getSerializableExtra(FILE_KEY) as File
        setContentView(R.layout.common_item_recyclerview)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val reader = targetFile.bufferedReader()
        executeByIo {
            var line = reader.readLine()
            while (line != null) {
                mAdapter.addItemAndNotify(line)
                line = reader.readLine()
            }
        }
    }
}