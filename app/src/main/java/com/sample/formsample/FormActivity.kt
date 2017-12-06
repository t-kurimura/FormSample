package com.sample.formsample

import android.app.DatePickerDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.sample.formsample.databinding.ActivityFormBinding
import io.reactivex.disposables.CompositeDisposable
import java.util.*


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
                .setItems(items, { _, which -> properties.isMan = (which == 0) })
                .show()
    }

    fun onBirthdayClicked() {
        DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener({ _, y, m, d -> properties.birthday = Calendar.getInstance().apply { set(y, m, d) } }),
                2000, 0, 1).show()
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