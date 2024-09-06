package base.ryuunta.base_mvvm.utils

import androidx.appcompat.app.AppCompatDelegate


fun setDarkModeTheme(isDarkMode: Boolean) {
    if (isDarkMode) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}