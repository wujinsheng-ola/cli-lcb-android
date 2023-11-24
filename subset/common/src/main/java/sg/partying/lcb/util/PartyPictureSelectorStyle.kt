package sg.partying.lcb.util

import android.content.Context
import androidx.core.content.ContextCompat
import com.luck.picture.lib.style.BottomNavBarStyle
import com.luck.picture.lib.style.PictureSelectorStyle
import com.luck.picture.lib.style.PictureWindowAnimationStyle
import com.luck.picture.lib.style.SelectMainStyle
import com.luck.picture.lib.style.TitleBarStyle
import com.luck.picture.lib.R

/**
 * Time:2023/11/2 10:32
 * Author:
 * Description:
 */
class PartyPictureSelectorStyle(context: Context) : PictureSelectorStyle() {
    init {
        val blueTitleBarStyle = TitleBarStyle()
        blueTitleBarStyle.titleBackgroundColor = ContextCompat.getColor(context, R.color.ps_color_blue)
        val numberBlueBottomNavBarStyle = BottomNavBarStyle()
        numberBlueBottomNavBarStyle.bottomPreviewNormalTextColor = ContextCompat.getColor(context, R.color.ps_color_9b)
        numberBlueBottomNavBarStyle.bottomPreviewSelectTextColor = ContextCompat.getColor(context, R.color.ps_color_blue)
        numberBlueBottomNavBarStyle.bottomNarBarBackgroundColor = ContextCompat.getColor(context, R.color.ps_color_white)
        numberBlueBottomNavBarStyle.bottomSelectNumResources = R.drawable.ps_default_num_oval_selected
        numberBlueBottomNavBarStyle.bottomEditorTextColor = ContextCompat.getColor(context, R.color.ps_color_53575e)
        numberBlueBottomNavBarStyle.bottomOriginalTextColor = ContextCompat.getColor(context, R.color.ps_color_53575e)
        val numberBlueSelectMainStyle = SelectMainStyle()
        numberBlueSelectMainStyle.statusBarColor = ContextCompat.getColor(context, R.color.ps_color_blue)
        numberBlueSelectMainStyle.isSelectNumberStyle = true
        numberBlueSelectMainStyle.isPreviewSelectNumberStyle = true
        numberBlueSelectMainStyle.selectBackground = com.salton123.resources.R.drawable.ps_demo_blue_num_selector
        numberBlueSelectMainStyle.mainListBackgroundColor = ContextCompat.getColor(context, R.color.ps_color_white)
        numberBlueSelectMainStyle.previewSelectBackground = com.salton123.resources.R.drawable.ps_demo_preview_blue_num_selector
        numberBlueSelectMainStyle.selectNormalTextColor = ContextCompat.getColor(context, R.color.ps_color_9b)
        numberBlueSelectMainStyle.selectTextColor = ContextCompat.getColor(context, R.color.ps_color_blue)
        numberBlueSelectMainStyle.setSelectText(R.string.ps_completed)
        titleBarStyle = blueTitleBarStyle
        bottomBarStyle = numberBlueBottomNavBarStyle
        selectMainStyle = numberBlueSelectMainStyle
        val animationStyle = PictureWindowAnimationStyle()
        animationStyle.setActivityEnterAnimation(R.anim.ps_anim_up_in)
        animationStyle.setActivityExitAnimation(R.anim.ps_anim_down_out)
        windowAnimationStyle = animationStyle
    }
}