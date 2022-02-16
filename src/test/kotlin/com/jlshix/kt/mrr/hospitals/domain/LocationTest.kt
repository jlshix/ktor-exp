package com.jlshix.kt.mrr.hospitals.domain

import kotlin.test.*
import com.jlshix.kt.mrr.hospitals.domain.domainRouter
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.assertEquals

class LocationTest {
    @Test
    fun testRoot() {
        withTestApplication({ domainRouter() }) {
            handleRequest(HttpMethod.Get, "/api/hospitals/noHospital/records/20223456/basicInfo").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }
}