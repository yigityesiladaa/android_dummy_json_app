package com.example.vize2.common.extensions


import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(message : String){
    Toast.makeText(this.context,message, Toast.LENGTH_LONG).show()
}