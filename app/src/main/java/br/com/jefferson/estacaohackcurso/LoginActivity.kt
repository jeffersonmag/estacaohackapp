package br.com.jefferson.estacaohackcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLoginEntrar.setOnClickListener({
            val email = edtLoginEmail.text.toString().trim().toLowerCase()
            val senha = edtLoginSenha.text.toString().trim()

            if(email.isEmpty()){
                edtLoginEmail.error = "Campo Obrigatório!"
                edtLoginEmail.requestFocus()
            } else if(senha.isEmpty()) {
                edtLoginSenha.error = "Campo Obrigatório!"
                edtLoginSenha.requestFocus()
            } else {
                //Acessando o arquivo de preferências compartilhadas
                val sharedPrefs = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)

                //Recuperando os dados no arquivo shared preferences
                val emailPrefs = sharedPrefs.getString("EMAIL", "Chave não encontrada")
                val senhaPrefs= sharedPrefs.getString("SENHA", "Chave não encontrada")

                //verificando email e senha
                if (email == emailPrefs && senha == senhaPrefs) {
                    Toast.makeText(this, "Usuário logado com sucesso!", Toast.LENGTH_LONG).show()
                    val mIntent = Intent(this, MainActivity::class.java)
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()
                } else {
                    Toast.makeText(this, "E-mail ou senha inválidos!", Toast.LENGTH_SHORT).show()
                }
            }
        })
        btnLoginCadastrar.setOnClickListener({
            val mIntent = Intent(this, CadastroActivity::class.java)
            startActivity(mIntent)
        })
    }
}