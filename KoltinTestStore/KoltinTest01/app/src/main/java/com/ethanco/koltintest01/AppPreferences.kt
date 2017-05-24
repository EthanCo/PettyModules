package com.ethanco.koltintest01

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/5/22
 */
class AppPreferences(private val context: Context) {
    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    var userName by PreferenceDelegates.string(defaultValue = "SP_USER_NAME")
    var password by PreferenceDelegates.string()
}

object PreferenceDelegates {
    public fun string(defaultValue: String? = null): ReadWriteProperty<AppPreferences, String?> {
        return PrefString(defaultValue)
    }
}

private class PrefString(private val defaultValue: String?) : ReadWriteProperty<AppPreferences, String?> {
    override fun getValue(thisRef: AppPreferences, property: KProperty<*>): String? {
        return thisRef.preferences.getString(property.name, defaultValue)
    }

    override fun setValue(thisRef: AppPreferences, property: KProperty<*>, value: String?) {
        thisRef.preferences.edit().putString(property.name, value).apply()
    }
}
