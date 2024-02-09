package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel: ViewModel() {
    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
    get() = _shoeList

    fun shoeEmpty(): Boolean{
        return _shoeList.value == null || _shoeList.value?.isEmpty() == true
    }

    fun addShoe(shoe: Shoe){
        if(_shoeList.value == null){
            _shoeList.value = mutableListOf(shoe)
        }else{
            _shoeList.value?.add(shoe)
        }
    }
}