package com.jlshix.kt.mrr.hospitals.domain

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.filter
import org.ktorm.entity.mapColumns


@Location("/api/hospitals/{hospital}/records/{caseId}/basicInfo")
data class LocationBasicInfo(val hospital: String, val caseId: String)

@Location("/api/hospitals/{hospital}/records/{caseId}/emr")
data class LocationEmr(val hospital: String, val caseId: String)

fun cnHospital(hospital: String): String =
    mapOf("suqian" to "宿迁")[hospital] ?: throw NotFoundException("未找到 $hospital 对应医院")



fun Application.domainRouter() {
    val database = Database.connect(
        url = environment.config.property("db.domain.url").getString(),
        user = environment.config.property("db.domain.user").getString(),
        password = environment.config.property("db.domain.password").getString(),
    )
    routing {
        get<LocationBasicInfo> {req ->
            val count = database.domain
                .filter { it.caseId eq req.caseId }
                .filter { it.hospital eq cnHospital(req.hospital) }
                .mapColumns { it.caseId }.count()
            when (count) {
                0 -> throw NotFoundException("找不到 ${req.caseId} 的基本信息")
                else -> call.respond(mapOf("caseId" to req.caseId))
            }
        }
    }

}