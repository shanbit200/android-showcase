package com.igorwojda.showcase.feature.album

import com.igorwojda.showcase.feature.album.Layers.DATA
import com.igorwojda.showcase.feature.album.Layers.DOMAIN
import com.igorwojda.showcase.feature.album.Layers.PRESENTATION
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.runner.RunWith

@RunWith(ArchUnitRunner::class)
@AnalyzeClasses(packagesOf = [CleanArchitectureDependencyTest::class])
class CleanArchitectureDependencyTest {

    @ArchTest
    fun `check dependency rule`() {
        // given
        val classes = ClassFileImporter().importPackages("com.igorwojda.showcase.feature.album")

        val rule = layeredArchitecture()
            .layer(PRESENTATION).definedBy("com.igorwojda.showcase.feature.album.presentation")
            .layer(DOMAIN).definedBy("com.igorwojda.showcase.feature.album.domain")
            .layer(DATA).definedBy("com.igorwojda.showcase.feature.album.data")
            .whereLayer(DATA).mayOnlyBeAccessedByLayers(DOMAIN)
            .whereLayer(DOMAIN).mayOnlyBeAccessedByLayers(PRESENTATION, DATA)

        // then
        rule.check(classes)
    }

}

object Layers {
    const val PRESENTATION = "presentation"
    const val DOMAIN = "domain"
    const val DATA = "data"
}
