package com.oed.commons.utils

import com.google.gson.Gson
import com.google.gson.JsonParseException
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.Type

fun String?.getJsonValue(key: String): String {
    return if (null == this) {
        ""
    } else {
        try {
            JSONObject(this).get(key).toString()
        } catch (e: JSONException) {
            ""
        } catch (e: NullPointerException) {
            ""
        }
    }
}

fun <T> String?.fromJson(classOfT: Class<T>): T {
    return Gson().fromJson<T>(this, classOfT)
}

fun <T> String?.fromJson(typeOfT: Type): T? {
    return Gson().fromJson<T>(this, typeOfT)
}

fun Any.toJson(): String {
    return try {
        Gson().toJson(this)
    } catch (e: JsonParseException) {
        ""
    }
}
