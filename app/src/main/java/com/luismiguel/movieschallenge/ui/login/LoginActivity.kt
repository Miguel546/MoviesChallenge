package com.luismiguel.movieschallenge.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.databinding.ActivityLoginBinding
import com.luismiguel.movieschallenge.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.apply {
            btnLogin.setOnClickListener {
                val usuario: String = tilUsuario.editText?.text.toString()
                val password: String = tilPassword.editText?.text.toString()

                if(usuario.isEmpty() || password.isEmpty()){
                    usuarioPasswordVacios()
                }else{
                    if(usuario.equals("Admin") && password.equals("Password*123")){
                        loginViewModel.savesp("Admin")
                        loginCorrecto()
                    }else{
                        credencialesIncorrectas()
                    }
                }
            }
        }
    }

    private fun usuarioPasswordVacios(){
        val vacioDialog = MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.campos_vacios))
            .setMessage(getString(R.string.usuario_contra_no_vacios))
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                dialog.dismiss()
            }
            .setCancelable(false)
        vacioDialog.show()
    }

    private fun loginCorrecto(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun credencialesIncorrectas(){
        val incorrectDialog = MaterialAlertDialogBuilder(this)
            .setTitle(getString(R.string.credenciales_incorrectas))
            .setMessage(getString(R.string.credenciales_no_correctas))
            .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                dialog.dismiss()
            }
            .setCancelable(false)
        incorrectDialog.show()
    }
}