package com.example.trabalho1.overview.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.trabalho1.databinding.ViewAddDialogBinding
import com.example.trabalho1.overview.database.BookEntity

class OverviewAddDialog: DialogFragment() {

    private var onClickListener: ((BookEntity) -> Unit)? = null
    private var binding: ViewAddDialogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewAddDialogBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun initViews() {
        binding?.run {
            addbook.setOnClickListener {
                if(author.text.isNotEmpty() && book.text.isNotEmpty()) {
                    onClickListener?.invoke(BookEntity(author.text.toString(), book.text.toString()))
                    dismiss()
                } else {
                    Toast.makeText(requireContext(), "You need to fill in both fields", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    class Builder (
        private val supportFragmentManager: FragmentManager
    ) {
        private var onClickListener: ((BookEntity) -> Unit)? = null


        private var dialogFragment : OverviewAddDialog? = null

        fun onClickListener(block: (BookEntity) -> Unit) = apply {
            this.onClickListener = block
        }

        private fun create(): OverviewAddDialog? {
            if(dialogFragment == null) {
                dialogFragment = OverviewAddDialog().also {
                    it.onClickListener = onClickListener
                }
            }
            return dialogFragment
        }

        fun show() {
            create()?.let {
                if(!(it.isAdded && it.dialog != null && it.dialog?.isShowing != null && !it.isRemoving)) {
                    it.show(this.supportFragmentManager, "TAG")
                }
            }
        }
    }
}