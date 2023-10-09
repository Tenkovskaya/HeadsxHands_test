package com.tenkovskaya.headsxhands_test

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var playerHealthTextView: TextView
    private lateinit var monsterHealthTextView: TextView
    private val player = Player("Игрок", 20, 15, 100, 1..10)
    private val monster = Monster("Монстр", 18, 12, 80, 2..8)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerHealthTextView = findViewById(R.id.playerHealthTextView)
        monsterHealthTextView = findViewById(R.id.monsterHealthTextView)
        val attackButton = findViewById<Button>(R.id.attackButton)

        updateHealthText(playerHealthTextView, monsterHealthTextView)

        attackButton.setOnClickListener {
            player.attack(monster)
            if (monster.isAlive()) {
                monster.attack(player)
            }
            updateHealthText(playerHealthTextView, monsterHealthTextView)

            if (!player.isAlive()) {
                val message = "Игрок проиграл! Монстры выиграли!"
                showGameResultDialog(message)
            } else if (!monster.isAlive()) {
                val message = "Монстр проиграл! Вы выиграли!"
                showGameResultDialog(message)
            }
        }
    }

    private fun updateHealthText(playerHealthTextView: TextView, monsterHealthTextView: TextView) {
        playerHealthTextView.text = "Здоровье игрока: ${player.health}"
        monsterHealthTextView.text = "Здоровье монстра: ${monster.health}"
    }

    private fun showGameResultDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setPositiveButton("Новая игра") { _, _ ->
                resetGame()
            }
            .setNegativeButton("Выход") { _, _ ->
                finish()
            }
            .setCancelable(false)
        val dialog = builder.create()
        dialog.show()
    }

    private fun resetGame() {
        player.health = 100
        monster.health = 80
        updateHealthText(playerHealthTextView, monsterHealthTextView)
    }
}


