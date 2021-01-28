object Versions {
    val retrofit = "2.9.0"
    val okhttpLogging = "4.8.0"
    val appcompat = "1.2.0"
    val coreKtx = "1.3.1"
    val constraintlayout = "2.0.2"
    val paging = "3.0.0-alpha02"
    val timber = "4.7.1"
    val kotlin = "1.3.72"
    val kotlinCoroutinesCore = "1.3.7"
    val kotlinCoroutinesAndrid = "1.3.6"
    val koin = "2.1.5"
    val work = "2.2.0"
    val room = "2.3.0-alpha01"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val fragment = "1.3.0-alpha06"
    val anko = "0.10.8"
    val swiperefreshlayout = "1.1.0"
    val junit = "4.13"
    val junitExt = "1.1.1"
    val espressoCore = "3.2.0"
    val jDatabinding = "1.0.1"
    val progressview = "1.0.0"
    val runtime = "0.11.0"
    val hit = "2.28-alpha"
    val hitViewModule = "1.0.0-alpha01"
    val appStartup = "1.0.0-alpha01"
    val material = "1.2.0-alpha06"
    const val BRVH = "3.0.4"
}

object Libs {

}

object AndroidX {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"

    val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    val workTesting = "androidx.work:work-testing:${Versions.work}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val swiperefreshlayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"

    val appStartup = "androidx.startup:startup-runtime:${Versions.appStartup}"

    val BRVH = "com.github.CymChad:BaseRecyclerViewAdapterHelper:${Versions.BRVH}"
}
