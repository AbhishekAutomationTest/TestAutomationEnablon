package com.learning.util

import org.testng.annotations.ITestAnnotation
import org.testng.internal.annotations.IAnnotationTransformer
import java.lang.reflect.Constructor
import java.lang.reflect.Method

internal class AnnotationTransformer : IAnnotationTransformer {
    override fun transform(
        iTestAnnotation: ITestAnnotation,
        aClass: Class<*>?,
        constructor: Constructor<*>?,
        method: Method
    ) {
        iTestAnnotation.setRetryAnalyzer(RetryAnalyzer::class.java)
    }
}