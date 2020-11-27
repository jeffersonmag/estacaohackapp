package br.com.jefferson.estacaohackcurso

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Recuperando o email passando pelo intent
        val email = intent.getStringExtra("INTENT_EMAIL")

        // Acessar o arquivo de preferências compartilhadas
        val sharedPrefs = getSharedPreferences(
            "cadastro_$email",   // Nome do arquivo
            Context.MODE_PRIVATE        // Modo de acesso
        )

        // Recuperar dados no arquivo de preferencias compartilhadas
        val nome = sharedPrefs.getString("NOME", "")
        val sobrenome = sharedPrefs.getString("SOBRENOME", "")
        val genero = sharedPrefs.getString("GENERO", "")

        txvMainNome.text = "$nome $sobrenome"
        txvMainEmail.text = email
        txvMainGenero.text = genero

        // Escutando o clique do botão sair
        btnMainSair.setOnClickListener{

            // Definindo uma caíxa de diálogo
            val alert = AlertDialog.Builder(this)

            // Definindo o título da caixa de dialógo
            alert.setTitle("App Curso")

            // Definindo o corpo da mensagem
            alert.setMessage("Deseja sair?")

            // Definindo o rótulo do botão e escutando seu clique
            // tbm pode ser: alert.setPositiveButton("Sair"){ _, _ ->
            alert.setPositiveButton("Sair"){ dialog, wich ->
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)
                // Eliminando as telas da pilha
                finishAffinity()
            }

            // Definindo o rótulo do botão
            alert.setNegativeButton("Não Sair"){_, _ ->
            }

            // Exibindo a caixa de dialogo
            alert.show()
        }

        // Escutando o clique do botão Site Cel.Lep
        btnMainSite.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }


    }
}