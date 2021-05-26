package com.dicoding.sample.academy.ui.reader.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.sample.academy.data.entity.ModuleEntity
import com.dicoding.sample.academy.databinding.FragmentModuleContentBinding
import com.dicoding.sample.academy.ui.reader.CourseReaderViewModel
import com.dicoding.sample.academy.viewModel.ViewModelFactory

class ModuleContentFragment : Fragment() {

    private lateinit var fragmentModuleContentBinding: FragmentModuleContentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentModuleContentBinding = FragmentModuleContentBinding.inflate(inflater, container, false)
        return fragmentModuleContentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
            fragmentModuleContentBinding.progressBar.visibility = View.VISIBLE
            viewModel.getSelectedModule().observe(viewLifecycleOwner, {modules ->
                fragmentModuleContentBinding.progressBar.visibility = View.GONE
                if (modules != null){
                    populateWebView(modules)
                }
            })

        }
    }

    private fun populateWebView(content: ModuleEntity) {
        fragmentModuleContentBinding.webView.loadData(content.contentEntity?.content ?:  "", "text/html", "UTF-8")
    }

    companion object {
        val TAG: String = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }
}