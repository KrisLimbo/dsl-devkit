/*
 * generated by Xtext 2.26.0
 */
package com.avaloq.tools.ddk.sample.helloworld.serializer;

import com.avaloq.tools.ddk.sample.helloworld.helloWorld.Greeting;
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.HelloWorldPackage;
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.KeywordsExample;
import com.avaloq.tools.ddk.sample.helloworld.helloWorld.Model;
import com.avaloq.tools.ddk.sample.helloworld.services.HelloWorldGrammarAccess;
import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class HelloWorldSemanticSequencer extends AbstractDelegatingSemanticSequencer {

    @Inject
    private HelloWorldGrammarAccess grammarAccess;

    @Override
    public void sequence(ISerializationContext context, EObject semanticObject) {
        EPackage epackage = semanticObject.eClass().getEPackage();
        ParserRule rule = context.getParserRule();
        Action action = context.getAssignedAction();
        Set<Parameter> parameters = context.getEnabledBooleanParameters();
        if (epackage == HelloWorldPackage.eINSTANCE)
            switch (semanticObject.eClass().getClassifierID()) {
            case HelloWorldPackage.GREETING:
                sequence_Greeting(context, (Greeting) semanticObject);
                return;
            case HelloWorldPackage.KEYWORDS_EXAMPLE:
                sequence_KeywordsExample(context, (KeywordsExample) semanticObject);
                return;
            case HelloWorldPackage.MODEL:
                sequence_Model(context, (Model) semanticObject);
                return;
            }
        if (errorAcceptor != null)
            errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
    }

    /**
     * <pre>
     * Contexts:
     *     Greeting returns Greeting
     *
     * Constraint:
     *     name=ID
     * </pre>
     */
    protected void sequence_Greeting(ISerializationContext context, Greeting semanticObject) {
        if (errorAcceptor != null) {
            if (transientValues.isValueTransient(semanticObject, HelloWorldPackage.Literals.GREETING__NAME) == ValueTransient.YES)
                errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, HelloWorldPackage.Literals.GREETING__NAME));
        }
        SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
        feeder.accept(grammarAccess.getGreetingAccess().getNameIDTerminalRuleCall_1_0(), semanticObject.getName());
        feeder.finish();
    }


    /**
     * <pre>
     * Contexts:
     *     KeywordsExample returns KeywordsExample
     *
     * Constraint:
     *     (option=OptionOne | option=OptionTwo)
     *     (option=OptionOne | option=OptionTwo | option=OptionThree | option=OptionFour)
     * </pre>
     */
    protected void sequence_KeywordsExample(ISerializationContext context, KeywordsExample semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }


    /**
     * <pre>
     * Contexts:
     *     Model returns Model
     *
     * Constraint:
     *     ((greetings+=Greeting+ keywordsExample=KeywordsExample) | keywordsExample=KeywordsExample)?
     * </pre>
     */
    protected void sequence_Model(ISerializationContext context, Model semanticObject) {
        genericSequencer.createSequence(context, semanticObject);
    }


}
