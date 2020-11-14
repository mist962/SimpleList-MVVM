package com.sideki.simplelist.views

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sideki.simplelist.R
import com.sideki.simplelist.model.entity.Note
import com.sideki.simplelist.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddFragment : Fragment(R.layout.fragment_add) {

    lateinit var mViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val prioryty = np_AddPriority
        prioryty.minValue = 1
        prioryty.maxValue = 10
        val btn_add = btn_add

        btn_add.setOnClickListener {
            val imageViewURL = ed_AddURL.text.toString()
            val title = ed_AddTitle.text.toString()
            val desription = ed_AddDescription.text.toString()

            GlobalScope.launch(Dispatchers.IO) {
                if (checkFields(title, desription, imageViewURL)) {
                    val backgroundImage = Glide
                        .with(requireActivity())
                        .asBitmap()
                        .circleCrop()
                        .load(imageViewURL)
                        .submit(100, 100)
                        .get()

                    val note = Note(
                        0,
                        title,
                        desription,
                        backgroundImage,
                        imageViewURL,
                        prioryty.value
                    )
                    mViewModel.addNote(note)
                    withContext(Dispatchers.Main) {
                        findNavController().navigate(R.id.action_addFragment_to_listFragment)
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            requireContext(),
                            "Please fill out all fields",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun checkFields(title: String, description: String, imageViewURL: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description) && TextUtils.isEmpty(
            imageViewURL
        ))
    }
}




