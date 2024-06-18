package com.example.aps1

import android.graphics.RectF
import android.os.Parcel
import android.os.Parcelable

data class Box(
    val rect: RectF,
    val label: String,
    val withMask: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(RectF::class.java.classLoader)!!,
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(rect, flags)
        parcel.writeString(label)
        parcel.writeByte(if (withMask) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Box> {
        override fun createFromParcel(parcel: Parcel): Box {
            return Box(parcel)
        }

        override fun newArray(size: Int): Array<Box?> {
            return arrayOfNulls(size)
        }
    }
}
