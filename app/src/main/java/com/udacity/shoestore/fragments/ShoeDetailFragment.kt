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
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.viewmodels.ShoeDetailViewModel
import com.udacity.shoestore.viewmodels.ShoesViewModel

class ShoeDetailFragment : Fragment() {
    private val shoesViewModel: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentShoeDetailBinding.inflate(inflater,container, false)

        // Get shoe from arguments
        val shoe = arguments?.getParcelable<Shoe>("shoe")
        val detailViewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]

        // Initialize view model data from shoe
        if (shoe != null) {
            detailViewModel.initializeData(shoe)
        }

        return with(binding){
            viewModel = detailViewModel

            btnCancel.setOnClickListener {
                findNavController().navigateUp()
            }

            btnSave.setOnClickListener {
                if (detailViewModel.validateFields()){
                    val updatedShoe = detailViewModel.saveShoe()
                    shoesViewModel.addShoe( updatedShoe)
                    findNavController().navigateUp()
                }else{
                    showError(binding)
                }
            }
            root
        }

    }

    private fun showError(binding: FragmentShoeDetailBinding) = with(binding) {
        Toast.makeText(context, "Please fill all these fields", Toast.LENGTH_SHORT).show()
    }


}