package com.tenkovskaya.headsxhands_test

import kotlin.random.Random

open class Creature(val name: String, var attack: Int, var defense: Int, var health: Int, val damageRange: IntRange) {
    init {
        require(attack in 1..30) { "Атака должна быть в диапазоне от 1 до 30" }
        require(defense in 1..30) { "Защита должна быть в диапазоне от 1 до 30" }
        require(health >= 0) { "Здоровье не может быть отрицательным" }
    }

    fun takeDamage(damage: Int) {
        require(damage >= 0) { "Урон не может быть отрицательным" }
        health -= damage
        if (health < 0) {
            health = 0
        }
    }

    fun isAlive(): Boolean {
        return health > 0
    }

    fun heal() {
        val maxHeal = (health * 0.3).toInt()
        val amountToHeal = Random.nextInt(1, maxHeal + 1)
        health += amountToHeal
        if (health > maxHealth) {
            health = maxHealth
        }
    }

    private val maxHealth = health
}