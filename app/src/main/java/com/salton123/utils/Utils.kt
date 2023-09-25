package com.salton123.utils

import android.os.Build
import android.text.TextUtils
import com.blankj.utilcode.util.TimeUtils
import kt.log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * User: wujinsheng1@yy.com
 * Date: 2021/9/4 23:32
 * ModifyTime: 23:32
 * Description:
 */
object Utils {
    private const val TAG = "Utils"
    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    fun getDateTitle(timeMills: Long): String {
        val title = simpleDateFormat.format(timeMills)
        return title
    }

    fun getDateTime(timeMills: Long): Long {
        return try {
            val calendar = Calendar.getInstance(Locale.getDefault())
            calendar.timeInMillis = timeMills
            calendar.set(Calendar.HOUR_OF_DAY, 20)
            calendar.set(Calendar.MINUTE, 50)
            calendar.set(Calendar.SECOND, 50)
            calendar.set(Calendar.MILLISECOND, 0)
//            Log.i(TAG, "[getDateTime] timeInMillis:${calendar.timeInMillis},timeMills:${timeMills},${calendar.timeInMillis - timeMills}")
            calendar.timeInMillis
        } catch (ex: Exception) {
            ex.printStackTrace()
            log("getDateTime,timeMills:$timeMills")
            0L
        }
    }

    /**
     * 将毫秒数格式化为"##:##"的时间
     * @param milliseconds 毫秒数
     * @return ##:##
     */
    fun formatTime(milliseconds: Long): String {
        if (milliseconds <= 0 || milliseconds >= 24 * 60 * 60 * 1000) {
            return "00:00"
        }
        val totalSeconds = milliseconds / 1000
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        val stringBuilder = StringBuilder()
        val mFormatter = java.util.Formatter(stringBuilder, Locale.getDefault())
        return if (hours > 0) {
            mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
        } else {
            mFormatter.format("%02d:%02d", minutes, seconds).toString()
        }
    }

    val videoSuffix = arrayOf("mp4", "mpeg", "mpg", "m4v")
    fun filterVideoBySuffix(file: File): Boolean {
        return if (file.isFile) {
            return videoSuffix.contains(file.name.substringAfter("."))
        } else {
            false
        }
    }

    private fun dirName(filePath: String): String {
        val dirList = filePath.split(File.separator)
        val size = dirList.size
        var dirName = "sdcard"
        dirName = when (size) {
            in 0..3 -> {
                "sdcard"
            }
            in 4..6 -> {
                dirList[3]
            }
            in 7..9 -> {
                dirList[5]
            }
            else -> {
                dirList[6]
            }
        }
        return dirName
    }

    /**
     * Return the name of file.
     *
     * @param filePath The path of file.
     * @return the name of file
     */
    fun getFileName(filePath: String): String {
        if (TextUtils.isEmpty(filePath)) return ""
        val lastSep = filePath.lastIndexOf(File.separator)
        return if (lastSep == -1) filePath else filePath.substring(lastSep + 1)
    }

    /**
     * 重命名文件和文件夹
     *
     * @param file        File对象
     * @param newFileName 新的文件名
     * @return 执行结果
     */
    fun renameFile(file: File, newFileName: String): Boolean {
        var newFile = if (file.isDirectory) {
            File(file.parentFile, newFileName)
        } else {
            val temp = (newFileName
                + file.name.substring(
                file.name.lastIndexOf('.')))
            File(file.parentFile, temp)
        }
        println("file:${file.absolutePath},newFile:${newFile.absolutePath}")
        if (file.renameTo(newFile)) {
            return true
        }
        return false
    }

    fun isFileExist(filePath: String): Boolean {
        return File(filePath).exists()
    }

    /**
     * 获取目录文件大小
     * a
     *
     * @param dir
     * @return
     */
    fun getDirSize(dir: File): Long {
        if (dir == null) {
            return 0
        }
        if (!dir.isDirectory) {
            return 0
        }
        var dirSize: Long = 0
        val files = dir.listFiles()
        for (file in files) {
            if (file.isFile) {
                dirSize += file.length()
            } else if (file.isDirectory) {
                dirSize += file.length()
                dirSize += getDirSize(file) // 递归调用继续统计
            }
        }
        return dirSize
    }

    fun fileFilter(file: File): Boolean {
        if (file.length() < 1024 * 1024) {
            return false
        }
        if (file.name.contains("_squeeze")) {
            return false
        }
        return true
    }

    fun isAboveAndroid11(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
    }
}