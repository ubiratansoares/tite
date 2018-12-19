
object Versions {

    const val kotlin = "1.3.11"
    const val rxJava2 = "2.2.4"
    const val jUnit4 = "4.12"

}

object Dependencies {
    val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava2}"
}

object TestDependencies {
    val jUnit = "junit:junit:${Versions.jUnit4}"
}
