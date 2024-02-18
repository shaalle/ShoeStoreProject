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
    private lateinit var detailViewModel: ShoeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoeDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_detail,container,false)

        // Get shoe from arguments
        val shoe = arguments?.getParcelable<Shoe>("shoe")
        detailViewModel = ViewModelProvider(this)[ShoeDetailViewModel::class.java]

        // Initialize view model data from shoe
        if (shoe != null) {
            detailViewModel.initializeData(shoe)
        }

        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
        }

        binding.btnSave.setOnClickListener {
            // On save
            if(detailViewModel.validateFields()) {
                val updatedShoe = detailViewModel.saveShoe()
                Log.d("shoe_name", updatedShoe.toString())
                shoesViewModel.addShoe(updatedShoe)
                Toast.makeText(context, binding.editName.text.toString(), Toast.LENGTH_SHORT).show()
                findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
            }else{
                Toast.makeText(context, "Please fill all these fields", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }
}