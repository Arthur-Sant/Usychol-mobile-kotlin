package com.project.usychol.implementations

import com.project.usychol.db.PsychologistDB
import com.project.usychol.data.dao.PsychologistDAO
import com.project.usychol.domain.entities.PLan
import com.project.usychol.domain.entities.Psychologist

class PsychologistImplementation(private val psychologistDB: PsychologistDB):
    PsychologistDAO {
    override fun create(psychologist: Psychologist) {
        psychologistDB.create(psychologist)
    }

    override fun findAll(): ArrayList<Psychologist> = psychologistDB.findAll()

    override fun findByEmail(email: String): Psychologist? {
        val psychologist = psychologistDB.findAll()

        return psychologist.find {
            it.email == email
        }
    }

    override fun updatePlan(userId: Int, plan: PLan) {
        val psychologist = psychologistDB.find(userId)

        psychologist!!.plan = plan

        psychologistDB.update(userId, psychologist)
    }

    override fun findById(id: Int): Psychologist? = psychologistDB.find(id)


    override fun update(id: Int, psychologist: Psychologist) {
        psychologistDB.update(id, psychologist)
    }

}