package com.sample.formsample

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.sample.formsample.databinding.ActivityFormBinding
import io.reactivex.disposables.CompositeDisposable
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity


class FormActivity : AppCompatActivity() {

  private val compositeDisposable by lazy { CompositeDisposable() }

  val properties = FormProperties()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = DataBindingUtil.setContentView<ActivityFormBinding>(this, R.layout.activity_form)
    binding.activity = this

    compositeDisposable.add(properties.getValidationObservable())
  }

  fun onGenderClicked() {
    val items = arrayOf("男性", "女性")
    AlertDialog.Builder(this)
        .setTitle("性別を選択してください")
        .setItems(items, { dialog, which -> properties.isMan = (which == 0) })
        .show()
  }

  fun register() {
    startActivity(RegistrationCompleteActivity.createIntent(this))
    finish()
  }

  override fun onDestroy() {
    compositeDisposable.dispose()
    super.onDestroy()
  }
}