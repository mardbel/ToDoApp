package com.example.todoapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.todoapp.databinding.FragmentEditDialogBinding
import com.example.todoapp.model.Task
import com.example.todoapp.viewModel.TaskViewModel


class EditDialogFragment(currentTask: Task) : DialogFragment() {

    private var _binding: FragmentEditDialogBinding? = null
    private val binding get() = _binding!!
    private val taskToEdit = currentTask
    private val taskViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editTaskEt.setText(taskToEdit.task)

        binding.cancelButton.setOnClickListener() {
            dismiss()
        }
        binding.acceptButton.setOnClickListener() {

            var updatedText = binding.editTaskEt.text.toString()
            taskViewModel.updateTask(taskToEdit.id, updatedText)
            dismiss()
        }
    }
}