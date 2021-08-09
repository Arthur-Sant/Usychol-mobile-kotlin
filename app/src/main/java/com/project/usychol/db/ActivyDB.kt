package com.project.usychol.db

import com.project.usychol.domain.entities.Activy

class ActivyDB {

    val arrayActivyList = ArrayList<Activy>()

    init {
        arrayActivyList.add(
            Activy(
            null,
            "12/12/1222",
            "Brincar a vontdade",
            "Thought of the day"
        )
        )

        arrayActivyList.add(
            Activy(
                null,
                "12/12/1222",
                "Brincar a vontdade",
                "Thought of the day"
            )
        )

        arrayActivyList.add(
            Activy(
                null,
                "12/12/1222",
                "Brincar a vontdade",
                "Thought of the day"
            )
        )
    }

    fun create(activy: Activy){
        arrayActivyList.add(activy)
    }

    fun findAll(): List<Activy>{
        return arrayActivyList
    }
}