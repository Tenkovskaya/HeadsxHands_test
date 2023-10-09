package com.tenkovskaya.headsxhands_test

import kotlin.random.Random
import kotlin.random.nextInt

class Monster(name: String, attack: Int, defense: Int, health: Int, damageRange: IntRange) :
    Creature(name, attack, defense, health, damageRange) {

    fun attack(player: Player) {
        val attackModifier = attack - player.defense + 1
        val diceRolls = Random.nextInt(attackModifier) + 1
        val successfulAttack = (1..diceRolls).any { it == 5 || it == 6 }

        if (successfulAttack) {
            val damage = Random.nextInt(damageRange)
            player.takeDamage(damage)
        }
    }
}


