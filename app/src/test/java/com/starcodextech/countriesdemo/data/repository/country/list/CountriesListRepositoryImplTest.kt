package com.starcodextech.countriesdemo.data.repository.country.list

import com.starcodextech.countriesdemo.common.mapper.Mapper
import com.starcodextech.countriesdemo.data.remote.api.RestCountriesApi
import com.starcodextech.countriesdemo.data.remote.dto.*
import com.starcodextech.countriesdemo.data.remote.mapper.CountrySummaryMapper
import com.starcodextech.countriesdemo.domain.countries.list.model.CountrySummary
import com.starcodextech.countriesdemo.domain.countries.list.repository.CountriesListRepository
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

@HiltAndroidTest
class CountriesListRepositoryImplTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var repository: CountriesListRepository


    @Before
    fun setUp() {
        //mapper = CountrySummaryMapper()

    }

}