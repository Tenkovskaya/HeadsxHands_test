package com.tenkovskaya.headsxhands_test

import kotlin.random.Random
import kotlin.random.nextInt

class Player(name: String, attack: Int, defense: Int, health: Int, damageRange: IntRange) :
    Creature(name, attack, defense, health, damageRange) {

    fun attack(target: Monster) {
        val attackModifier = attack - target.defense + 1
        val diceRolls = Random.nextInt(attackModifier) + 1
        val successfulAttack = (1..diceRolls).any { it == 5 || it == 6 }

        if (successfulAttack) {
            val damage = Random.nextInt(damageRange)
            target.takeDamage(damage)
        }
    }
}