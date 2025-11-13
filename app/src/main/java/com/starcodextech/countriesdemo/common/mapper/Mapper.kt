package com.starcodextech.countriesdemo.common.mapper

interface Mapper<FROM, TO> {
    fun map(from: FROM): TO
}