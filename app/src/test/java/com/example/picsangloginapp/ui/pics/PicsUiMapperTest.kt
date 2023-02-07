package com.example.picsangloginapp.ui.pics

import com.best.core.exception.ExceptionType
import com.best.core.resourcemanager.TestResourceManager
import com.example.pics_api.PicItem
import com.example.picsangloginapp.ui.pics.adapter.*
import com.google.common.truth.Truth.assertThat
import org.junit.Test

internal class PicsUiMapperTest {

    private val mapper = PicsUiMapper(resourceManager = TestResourceManager())

    @Test
    fun test_basic() {
        val sourceList = listOf(
            PicItem.Base(text = "stub", url = "https:/google.com"),
            PicItem.Base(text = "stub", url = "https:/google.com"),
            PicItem.Base(text = "stub", url = "https:/google.com")
        )

        val result = mapper.map(source = sourceList)
        val expected = listOf(
            Basic(text = "stub", url = "https:/google.com"),
            Basic(text = "stub", url = "https:/google.com"),
            Basic(text = "stub", url = "https:/google.com"),
            BottomLoader
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_empty_result() {
        val sourceList = emptyList<PicItem>()

        val result = mapper.map(source = sourceList)
        val expected = listOf(FullSizeLoader)

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_last_error() {
        val sourceList = listOf(
            PicItem.Base(text = "stub", url = "https:/google.com"),
            PicItem.Base(text = "stub", url = "https:/google.com"),
            PicItem.Base(text = "stub", url = "https:/google.com"),
            PicItem.Error(exceptionType = ExceptionType.NETWORK_UNAVAILABLE),
        )

        val result = mapper.map(source = sourceList)
        val expected = listOf(
            Basic(text = "stub", url = "https:/google.com"),
            Basic(text = "stub", url = "https:/google.com"),
            Basic(text = "stub", url = "https:/google.com"),
            BottomError(message = "network is not available!")
        )

        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test_single_error() {
        val sourceList = listOf(PicItem.Error())

        val result = mapper.map(source = sourceList)
        val expected = listOf(FullSizeError(message = "just generic error"))

        assertThat(result).isEqualTo(expected)
    }
}