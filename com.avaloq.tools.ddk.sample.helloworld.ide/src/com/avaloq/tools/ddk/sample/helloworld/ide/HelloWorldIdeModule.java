/*
 * generated by Xtext 2.19.0
 */
package com.avaloq.tools.ddk.sample.helloworld.ide;

import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementCalculator;
import org.eclipse.xtext.ide.editor.contentassist.antlr.FollowElementComputer;

import com.avaloq.tools.ddk.xtext.generator.ide.contentAssist.AnnotationAwareFollowElementCalculator;
import com.avaloq.tools.ddk.xtext.generator.ide.contentAssist.AnnotationAwareFollowElementComputer;

/**
 * Use this class to register ide components.
 */
public class HelloWorldIdeModule extends AbstractHelloWorldIdeModule {

    public Class<? extends FollowElementComputer> bindFollowElementComputer() {
        return AnnotationAwareFollowElementComputer.class;
    }
    public Class<? extends FollowElementCalculator> bindFollowElementCalculator() {
        return AnnotationAwareFollowElementCalculator.class;
    }
}
