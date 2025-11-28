package fr.univtln.bruno.samples.java101.tp4;

import fr.univtln.bruno.samples.java101.tp4.functionalinterfaces.CustomFunctionalInterfacesExamples;
import fr.univtln.bruno.samples.java101.tp4.functionalinterfaces.StandardFunctionalInterfacesExamples;
import fr.univtln.bruno.samples.java101.tp4.lambda.LambdaBasicsExamples;
import fr.univtln.bruno.samples.java101.tp4.lambda.MethodReferenceExamples;
import fr.univtln.bruno.samples.java101.tp4.misc.OptionalExamples;
import fr.univtln.bruno.samples.java101.tp4.streams.CollectorsExamples;
import fr.univtln.bruno.samples.java101.tp4.streams.StreamCreationExamples;
import fr.univtln.bruno.samples.java101.tp4.streams.StreamIntermediateOperationsExamples;
import fr.univtln.bruno.samples.java101.tp4.streams.TerminalShortCircuitExamples;
import fr.univtln.bruno.samples.java101.tp4.streams.ParallelStreamsExamples;
import lombok.extern.slf4j.Slf4j;

/**
 * Demo runner for TP4 examples (Functional programming & modern Java).
 */
@Slf4j
public class Demo {

  private Demo() { }

  public static void main(String[] args) {
    log.info("==== TP4 Demo (Functional & Modern Java) ====");

    log.info("-- Lambda basics --");
    LambdaBasicsExamples.anonymousClassToLambda();
    LambdaBasicsExamples.lambdaSyntaxVariations();
    LambdaBasicsExamples.lambdaClosures();
    LambdaBasicsExamples.composingFunctions();
    LambdaBasicsExamples.chainingPredicates();
    LambdaBasicsExamples.lambdaComparators();

    log.info("-- Method references --");
    MethodReferenceExamples.staticMethodReferences();
    MethodReferenceExamples.instanceMethodReferences();
    MethodReferenceExamples.unboundMethodReferences();
    MethodReferenceExamples.constructorReferences();
    MethodReferenceExamples.arrayConstructorReferences();
    MethodReferenceExamples.whenToUseMethodReferences();

    log.info("-- Standard functional interfaces --");
    StandardFunctionalInterfacesExamples.predicateExamples();
    StandardFunctionalInterfacesExamples.functionExamples();
    StandardFunctionalInterfacesExamples.consumerExamples();
    StandardFunctionalInterfacesExamples.supplierExamples();
    StandardFunctionalInterfacesExamples.operatorExamples();
    StandardFunctionalInterfacesExamples.primitiveSpecializations();

    log.info("-- Custom functional interfaces --");
    CustomFunctionalInterfacesExamples.customValidatorExample();
    CustomFunctionalInterfacesExamples.checkedFunctionExample();
    CustomFunctionalInterfacesExamples.fallibleExample();
    CustomFunctionalInterfacesExamples.triFunctionExample();

    log.info("-- Stream creation and intermediate operations --");
    StreamCreationExamples.streamsFromCollections();
    StreamCreationExamples.streamsFromArrays();
    StreamCreationExamples.streamBuilder();
    StreamCreationExamples.streamGenerate();
    StreamCreationExamples.streamIterate();
    StreamCreationExamples.primitiveStreams();

    StreamIntermediateOperationsExamples.filterExamples();
    StreamIntermediateOperationsExamples.mapExamples();
    StreamIntermediateOperationsExamples.flatMapExamples();

    log.info("-- Terminal and short-circuit operations --");
    TerminalShortCircuitExamples.terminalDemo();

    log.info("-- Collectors and grouping --");
    CollectorsExamples.collectorsDemo();

    log.info("-- Parallel streams (careful) --");
    ParallelStreamsExamples.parallelPitfallDemo();

    log.info("-- Optional & misc --");
    OptionalExamples.optionalDemo();

    log.info("==== End of TP4 demonstration ====");
  }
}
