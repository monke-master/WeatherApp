package ru.monke.weatherapp.data

import android.os.Parcel
import android.os.Parcelable
import ru.monke.weatherapp.domain.City

class CityParcelable() : Parcelable {
    lateinit var city: City
        private set

    constructor(parcel: Parcel) : this() {
        city = City(
            name = parcel.readString() ?: "",
            id = parcel.readString() ?: "",
            latitude = parcel.readFloat(),
            longitude = parcel.readFloat()
        )
    }

    constructor(city: City): this() {
        this.city = city
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city.name)
        parcel.writeString(city.id)
        parcel.writeFloat(city.latitude)
        parcel.writeFloat(city.longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityParcelable> {
        override fun createFromParcel(parcel: Parcel): CityParcelable {
            return CityParcelable(parcel)
        }

        override fun newArray(size: Int): Array<CityParcelable?> {
            return arrayOfNulls(size)
        }
    }
}
