package com.example.pruebatecnicabkool.repository

import com.example.pruebatecnicabkool.core.Resource
import com.example.pruebatecnicabkool.data.model.launch.Launch
import com.example.pruebatecnicabkool.data.model.launch.Links
import com.example.pruebatecnicabkool.data.model.launch.Patch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class SpaceXRepositoryImplTest {

    private lateinit var mock: SpaceXRepository

    @Before
    fun setUp() {
        val list = ArrayList<Launch>()

        for (i in 0..4) {
            list.add(Launch(
                    false,
                    emptyList(),
                    emptyList(),
                    emptyList(),
                    "",
                    "",
                    0L,
                    "",
                    "",
                    emptyList(),
                    "",
                    0,
                    i.toString(),
                    "",
                    Links(Patch("",""),""),
                    "",
                    false,
                    emptyList(),
                    "",
                    emptyList(),
                    0L,
                    "",
                    false,
                    false,
                    false,
                    0
                )
            )
        }

        mock = SpaceXRepositoryMock(list)
    }

    @Test
    fun `valid id returns success`() {
        var element : Resource<Launch>
        runBlocking {
            element = mock.getLaunchDetail("2")
        }

        assertNotNull(element.data)

    }

    @Test
    fun `invalid id returns error`() {
        var element : Resource<Launch>
        runBlocking {
            element = mock.getLaunchDetail("7")
        }

        assertNull(element.data)
    }

}