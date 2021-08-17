package com.project.usychol.db

import android.content.SharedPreferences
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Psychologist

class PsychologistDB {

    private var arrayPsychologistsList = ArrayList<Psychologist>()

    init {
            arrayPsychologistsList.add(
                Psychologist(
                    10,
                    null,
                    "Arthur",
                    "jbdjwb",
                    222,
                    "3e3",
                    "arthur@gmail.com",
                    "12345",
                    null
                )
            )

        arrayPsychologistsList.add(
            Psychologist(
                13,
                null,
                "Arthur",
                "jbdjwb",
                222,
                "3e3",
                "arthursant@gmail.com",
                "12345",
                PLan("full", "creadit card", "07")
            )
        )
    }

    fun create(psychologist: Psychologist){
        arrayPsychologistsList.add(psychologist)
    }

    fun findAll(): ArrayList<Psychologist> = arrayPsychologistsList

    fun delete(index: Int){
        arrayPsychologistsList.removeAt(index)
    }

    fun update(id: Int, psychologist: Psychologist){
        val index = arrayPsychologistsList.indexOfFirst {
            it.id == id
        }

        arrayPsychologistsList[index] = psychologist
    }

    fun find(id: Int): Psychologist? {
        println(id)

        return arrayPsychologistsList.find {
            it.id == id
        }

    }

}