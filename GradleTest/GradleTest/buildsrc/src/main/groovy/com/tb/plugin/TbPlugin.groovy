package com.tb.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class TbPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println(">>>2 this is a buildSrc plugin...")
    }
}