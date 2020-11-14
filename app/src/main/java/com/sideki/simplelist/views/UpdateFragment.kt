package com.sideki.simplelist.views

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sideki.simplelist.R
import com.sideki.simplelist.model.entity.Note
import com.sideki.simplelist.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateFragment : Fragment(R.layout.fragment_update) {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel: NoteViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        ed_UpdateTitle.setText(args.currentNote.title)
        ed_UpdateDescription.setText(args.currentNote.description)
        ed_UpdateUrl.setText(args.currentNote.url)
        np_UpdatePriority.minValue = 1
        np_UpdatePriority.maxValue = 10
        np_UpdatePriority.value = args.currentNote.priority

        btn_update.setOnClickListener {
            val title = ed_UpdateTitle.text.toString()
            val description = ed_UpdateDescription.text.toString()
            val imageViewURL = ed_UpdateUrl.text.toString()
            val priority = np_UpdatePriority.value

            GlobalScope.launch(Dispatchers.IO) {
                if (inputCheck(imageViewURL)) {

                    val backgroundImage = Glide
                        .with(requireActivity())
                        .asBitmap()
                        .circleCrop()
                        .load(imageViewURL)
                        .submit(100, 100)
                        .get()

                    val note = Note(
                        args.currentNote.id,
                        title,
                        description,
                        backgroundImage,
                        imageViewURL,
                        priority
                    )
                    mViewModel.updateNote(note)
                    withContext(Dispatchers.Main) {
                        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
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

    private fun inputCheck(imageViewURL: String): Boolean {
        return !(TextUtils.isEmpty(imageViewURL))
    }
}
