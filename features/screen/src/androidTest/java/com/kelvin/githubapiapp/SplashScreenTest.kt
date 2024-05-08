package com.kelvin.githubapiapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.kelvin.githubapiapp.splash.SplashScreen
import com.kelvin.githubapiapp.splash.viewmodel.SplashViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//@RunWith(AndroidJUnit4::class)
//@ExperimentalComposeUiApi
//@ExperimentalCoroutinesApi
//@ExperimentalAnimationApi
@HiltAndroidTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class SplashScreenTest {


    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
//    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var navController: TestNavHostController

//    private lateinit var navController: NavHostController
    private lateinit var splashViewModel: com.kelvin.githubapiapp.splash.viewmodel.SplashViewModel

    @Before
    fun setupMoodTrackerAppNavHost() {
        hiltRule.inject()

        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            SplashScreen(navController = navController)
        }
    }

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.kelvin.githubapiapp", appContext.packageName)
//    }

    @Test
    fun moodTrackerNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(com.kelvin.githubapiapp.navigation.NavigationItem.Splash.route)
    }
}
