package com.udacity.shoestore.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel: ViewModel() {
    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()

    fun initializeData(shoe: Shoe) {
        shoeName.value = shoe.name
        shoeSize.value = shoe.size.toString()
        shoeCompany.value = shoe.company
        shoeDescription.value = shoe.description
    }
    fun validateFields(): Boolean {
        return isFieldPopulated(shoeName) && isFieldPopulated(shoeSize) && isFieldPopulated(shoeCompany)
                && isFieldPopulated(shoeDescription)
    }

    fun parseShoeSizeToDouble():Double {
        if (!isFieldPopulated(shoeSize)) return 0.00
        return shoeSize.value?.toDouble()!!
    }


    private fun isFieldPopulated(data: MutableLiveData<String>): Boolean {
        return !data.value.isNullOrEmpty()
}

    fun saveShoe(): Shoe {
        return Shoe(
            shoeName.value ?: "",
            parseShoeSizeToDouble(),
            shoeCompany.value ?: "",
            shoeDescription.value ?: "",
            arrayListOf("")
        )
    }




}