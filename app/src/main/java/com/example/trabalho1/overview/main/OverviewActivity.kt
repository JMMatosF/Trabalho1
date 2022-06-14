package com.example.trabalho1.overview.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho1.databinding.ActivityOverviewBinding
import com.example.trabalho1.overview.database.BookEntity
import com.example.trabalho1.overview.database.OverviewAdapter
import com.example.trabalho1.overview.dialog.OverviewAddDialog

class OverviewActivity : AppCompatActivity() {

    private val viewModel : OverviewViewModel by viewModels()

    private lateinit var binding : ActivityOverviewBinding

    private val mAdapter by lazy {
        OverviewAdapter {
            viewModel.deleteBook(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.initScreen(this)
        initViews()
        initObservers()
    }

    private fun initViews() {
        binding.fab.setOnClickListener {
            initDialog()
        }
        binding.list.adapter = mAdapter
        viewModel.getAllBooks()
    }

    private fun initDialog() {
        OverviewAddDialog.Builder(supportFragmentManager)
            .onClickListener {
                viewModel.insertBook(it)
            }
            .show()

    }

    private fun initObservers() {
        viewModel.getUiObservable().observe(this) {
            when(it){
                is OverviewUiState.Error -> handleError(it.resId)
                OverviewUiState.Loading -> handleLoading()
                is OverviewUiState.Success -> handleSuccess(it.books)
            }
        }
    }

    private fun handleSuccess(list: List<BookEntity>) {
        mAdapter.submitList(list)
    }

    private fun handleLoading() {

    }

    private fun handleError(@StringRes resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()
    }
}