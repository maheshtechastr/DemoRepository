package com.mpg.shaadidemoapp.ui.home

import android.view.View
import com.mpg.shaadidemoapp.BR
import com.mpg.shaadidemoapp.R
import com.mpg.shaadidemoapp.base.BaseFragment
import com.mpg.shaadidemoapp.databinding.HomeFragmentBinding
import com.mpg.shaadidemoapp.ui.home.adapter.UserListAdapter
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeFragmentBinding, HomeViewModel>() {

    override val bindingVariable: Int
        get() = BR.viewModel

    private val TAG = "HomeFragment"

    @Inject
    lateinit var adapter: UserListAdapter

    override fun viewLayoutResource() = R.layout.home_fragment

    override fun viewModelClass() = HomeViewModel::class.java

    override fun onActivityCreated(rootView: View) {
        super.onActivityCreated(rootView)
        Timber.i(TAG, "onActivityCreatedA: $rootView")
        getViewModel().userList.observe(viewLifecycleOwner, {
            Timber.i(TAG, "onActivityCreated: $it")
            adapter.submitList(it)
        })

        getViewModel().showLoader.observe(viewLifecycleOwner, {
            getBinding().progressBar.visibility = View.INVISIBLE
        })
        getBinding().userList.adapter = adapter
    }
}