package com.jlshix.kt.mrr.hospitals.domain

import kotlinx.serialization.json.*
import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.*

interface DomainRecord: Entity<DomainRecord> {
    companion object: Entity.Factory<DomainRecord>()
    val id: Int
    val caseId: String
    val docId: String
    val documentName: String
    val contents: JsonElement
    val isEmr: Boolean
    val hospital: String
    val notice: String

}

object Suqian : Table<DomainRecord>("suqian") {
    val id = int("id").primaryKey().bindTo { it.id }
    val caseId = varchar("caseId").bindTo { it.caseId }
    val docId = varchar("docId").bindTo { it.docId }
    val documentName = varchar("documentName").bindTo { it.documentName }
    val contents = varchar("contents")
        .transform(
            { Json.parseToJsonElement(it)},
            {it.toString()}
        ).bindTo { it.contents }
    val isEmr = boolean("isEmr").bindTo { it.isEmr }
    val hospital = varchar("hospital").bindTo { it.hospital }
    val notice = text("notice").bindTo { it.notice }
}

val Database.domain get() = this.sequenceOf(Suqian)
