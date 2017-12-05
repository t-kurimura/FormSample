package com.sample.formsample

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class RegistrationCompleteActivity : AppCompatActivity() {

  companion object {
    fun createIntent(context: Context) = Intent(context, RegistrationCompleteActivity::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_registration_complete)
  }
}
