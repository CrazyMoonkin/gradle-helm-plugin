package org.unbrokendome.gradle.plugins.helm.command.tasks

import org.gradle.api.provider.Property
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import org.unbrokendome.gradle.plugins.helm.util.property


/**
 * Upgrades a release on the cluster. Corresponds to the `helm upgrade` CLI command.
 */
open class HelmUpgrade : AbstractHelmInstallationCommandTask() {


    /**
     * If `true`, run an install if a release by this name doesn't already exist.
     */
    @get:Internal
    val install: Property<Boolean> =
        project.objects.property()


    /**
     * If `true`, reset the values to the ones built into the chart when upgrading.
     *
     * Corresponds to the `--reset-values` CLI parameter.
     */
    @get:Internal
    val resetValues: Property<Boolean> =
        project.objects.property()


    /**
     * If `true`, reuse the last release's values, and merge in any new values. If [resetValues] is specified,
     * this is ignored.
     *
     * Corresponds to the `--reuse-values` CLI parameter.
     */
    @get:Internal
    val reuseValues: Property<Boolean> =
        project.objects.property()


    @TaskAction
    fun upgradeRelease() {
        execHelm("upgrade") {
            flag("--install", install)
            flag("--reset-values", resetValues)
            flag("--reuse-values", reuseValues)
        }
    }
}
