package com.arinspect.proof_of_concept.application

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate


/**
 * [ProofConceptApplication] :
 *
 * @author : Mitesh Vanaliya
 * @version 1.0.0
 * @since 09/11/2019
 */
class ProofConceptApplication : Application() {
    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        @Volatile
        @JvmStatic
        lateinit var context: ProofConceptApplication
    }

    override fun onCreate() {
        super.onCreate()
        context = this
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                // Left out because "Unused"
            }

            override fun onActivityStarted(activity: Activity?) {
                // Left out because "Unused"
            }

            override fun onActivityResumed(activity: Activity?) {
                // Left out because "Unused"
            }

            override fun onActivityPaused(activity: Activity?) {
                // Left out because "Unused"
            }

            override fun onActivityStopped(activity: Activity?) {
                // Left out because "Unused"
            }

            override fun onActivityDestroyed(activity: Activity?) {
                // Left out because "Unused"
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                // Left out because "Unused"
            }
        })
    }


}