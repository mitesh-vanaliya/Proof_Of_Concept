package com.arinspect.proof_of_concept.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding

/**
 * [AbstractActivityBuilder] :
 *
 * Main **builder** class for Activity that is used as *Model* to hold data used in abstract activity class.
 *
 * Check out variables:
 *   1. [AbstractActivityBuilder.contentView]
 *   2. [AbstractActivityBuilder.abstractBinding]
 */
class AbstractActivityBuilder {

    /**
     * Variable that hold layout `resource id` of Activity
     */
    @LayoutRes
    var contentView: Int? = null

    /**
     * Variable to be set if used **Data-Binding** to provide Generic logic for [AbstractBinding].
     */
    var abstractBinding: AbstractBinding<out ViewDataBinding>? = null
}

/**
 * DSL-Method to provide imperative logic for object creation of [AbstractActivityBuilder] with validations
 *
 * @param builder as lambda method parameter for setup as callback to apply
 *
 * @return [AbstractActivityBuilder]
 */
fun absActivityBuilder(builder: AbstractActivityBuilder.() -> Unit): AbstractActivityBuilder {
    val absActivityBuilder = AbstractActivityBuilder()
    absActivityBuilder.apply(builder)
    return when {
        (absActivityBuilder.contentView == null) or (absActivityBuilder.contentView == -1) -> {
            throw IllegalArgumentException("Content view must not be null or -1")
        }
        else -> {
            absActivityBuilder
        }
    }
}
