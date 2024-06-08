package ru.monke.weatherapp.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import ru.monke.weatherapp.data.city.CityParcelable

class CityNavType(override val isNullableAllowed: Boolean): NavType<CityParcelable>(isNullableAllowed) {

    override fun get(bundle: Bundle, key: String): CityParcelable? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): CityParcelable {
        val gson = Gson()
        return gson.fromJson(value, CityParcelable::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: CityParcelable) {
        bundle.putParcelable(key, value)
    }

}