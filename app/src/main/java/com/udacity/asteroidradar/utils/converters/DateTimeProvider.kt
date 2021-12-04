package com.udacity.asteroidradar.utils.converters

interface DateTimeProvider<out T> {
    fun now(): T
}