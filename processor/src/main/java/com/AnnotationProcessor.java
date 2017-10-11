package com;

import spoon.processing.AbstractAnnotationProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;
import spoon.support.reflect.code.CtBlockImpl;
import spoon.support.reflect.code.CtCodeSnippetStatementImpl;

public class AnnotationProcessor extends AbstractAnnotationProcessor<Annotation, CtMethod> {

    public void process(Annotation annotation, CtMethod method) {
        method.getBody().replace(new CtBlockImpl());
        CtCodeSnippetStatementImpl statement = new CtCodeSnippetStatementImpl();
        Class<?> clazz = annotation.clazz();
        CtTypeReference<?> reference = getFactory().Type().createReference(clazz);
        statement.setValue("return " + reference.getQualifiedName() + ".class");
        method.getBody().insertBegin(statement);
    }

}
