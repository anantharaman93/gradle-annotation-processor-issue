package com.suraj;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.ServiceLoader;
import java.util.Set;

@AutoService(Processor.class)
public class MyAnnotationProcessor extends AbstractProcessor {
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Sets.newHashSet(MyAnnotation.class.getCanonicalName());
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        ImmutableList<MyInterface> list = ImmutableList.copyOf(ServiceLoader.load(MyInterface.class));
        if (list.isEmpty()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "No Services are loaded");
        } else {
            list.stream().map(MyInterface::className)
                    .forEach(className -> processingEnv.getMessager().printMessage(Diagnostic.Kind.MANDATORY_WARNING, className));
        }
        return false;
    }
}
