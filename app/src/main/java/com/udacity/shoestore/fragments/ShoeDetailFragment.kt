package com.udacity.shoestore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodels.ShoeDetailViewModel
import com.udacity.shoestore.viewmodels.ShoesViewModel

class ShoeDetailFragment : Fragment() {
    private val shoesViewModel: ShoesViewModel by activityViewModels()
    private lateinit var detailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail,container,false)

        detailViewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.btnSave.setOnClickListener {
            if (!detailViewModel.validateFields()){
                val shoe = detailViewModel.saveShoe()
                Log.d("shoe_name", shoe.toString())
                shoesViewModel.addShoe(shoe)
                Toast.makeText(context, binding.editName.text.toString(), Toast.LENGTH_SHORT).show()
                Log.d("name", detailViewModel.shoeName.value.toString())
                Log.d("size", detailViewModel.shoeSize.value.toString())
                Log.d("company", detailViewModel.shoeCompany.value.toString())
                Log.d("Desc", detailViewModel.shoeDescription.value.toString())
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            }else{
                Toast.makeText(context, "Please fill all these fields", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}