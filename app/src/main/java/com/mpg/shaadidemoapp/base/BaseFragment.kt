package com.mpg.shaadidemoapp.base

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VB : ViewDataBinding, VM : BaseViewModel> : DaggerFragment() {

    abstract val bindingVariable: Int

    private lateinit var viewDataBinding: VB
    private lateinit var viewModel: VM

    private lateinit var progressAlertDialog: AlertDialog

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun viewLayoutResource(): Int

    abstract fun viewModelClass(): Class<VM>

    open fun onActivityCreated(rootView: View) {

    }

    override fun onAttach(context: Context) {
        /*
  *  Remember in our FragmentModule, we
  * defined HomeFragment injection? So we need
  * to call this method in order to inject the
  * ViewModelFactory into our Fragment
  * */
        AndroidSupportInjection.inject(this)
        super.onAttach(context)

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(viewModelClass())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, viewLayoutResource(), container, false)
        viewDataBinding.setVariable(bindingVariable, viewModel)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        onActivityCreated(viewDataBinding.root)
    }

    protected fun getBinding() = viewDataBinding
    protected fun getViewModel() = viewModel
}