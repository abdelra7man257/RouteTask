package com.example.routetask.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.utils.ResultWrapper
import com.example.routetask.R
import com.example.routetask.databinding.ActivityMainBinding
import com.example.routetask.utils.showSnackBar
import com.facebook.shimmer.Shimmer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding
    private val viewModel: ProductsViewModel by viewModels<ProductsViewModel>()
    private val adapter = ProductsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initViews()
        collectUiData()

    }

    private fun initViews() {
        binding?.productsRv?.adapter = adapter
    }

    private fun collectUiData() {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED)
            {
                viewModel.response.collect {
                    when (it) {
                        is ResultWrapper.Error -> {
                            showSnackBar(it.message, binding?.root!!)
                            hideShimmer()
                        }

                        is ResultWrapper.Loading -> {
                            showShimmer()
                        }

                        is ResultWrapper.Success -> {
                            hideShimmer()
                            adapter.setProductsList(it.data)
                        }
                    }
                }
            }


        }
    }


    private fun showShimmer() {
        val shimmer = Shimmer
            .AlphaHighlightBuilder()
            .setAutoStart(true)
            .setBaseAlpha(0.25f)
            .setHighlightAlpha(0.50f)
            .setDropoff(50f)
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .build()
        binding?.apply {
            shimmerLayout.setShimmer(shimmer)
            shimmerLayout.startShimmer()
            shimmerLayout.isVisible = true
            productsRv.isVisible = false
        }

    }

    private fun hideShimmer() {
        binding?.apply {
            shimmerLayout.stopShimmer()
            shimmerLayout.isVisible = false
            productsRv.isVisible = true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}