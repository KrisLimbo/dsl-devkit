/*
 * generated by Xtext
 */
package com.avaloq.tools.ddk.xtext.export.generator

import com.avaloq.tools.ddk.xtext.export.export.ExportModel
import com.avaloq.tools.ddk.xtext.expression.generator.CompilationContext
import com.avaloq.tools.ddk.xtext.expression.generator.GenModelUtilX
import com.avaloq.tools.ddk.xtext.expression.generator.Naming
import com.google.inject.Inject
import org.eclipse.core.resources.IProject
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator

/**
 * Generates code from your model files on save.
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#code-generation
 */
class ExportGenerator implements IGenerator {

  @Inject
  extension ExportGeneratorSupport generatorSupport
  @Inject
  extension Naming
  @Inject
  extension ExportGeneratorX

  @Inject
  GenModelUtilX genModelUtil

  @Inject
  ExportedNamesProviderGenerator exportedNamesProviderGenerator
  @Inject
  ResourceDescriptionManagerGenerator resourceDescriptionManagerGenerator
  @Inject
  ResourceDescriptionStrategyGenerator resourceDescriptionStrategyGenerator
  @Inject
  ResourceDescriptionConstantsGenerator resourceDescriptionConstantsGenerator
  @Inject
  FingerprintComputerGenerator fingerprintComputerGenerator
  @Inject
  FragmentProviderGenerator fragmentProviderGenerator
  @Inject
  ExportFeatureExtensionGenerator exportFeatureExtensionGenerator

  CompilationContext compilationContext

  override void doGenerate(Resource input, IFileSystemAccess fsa) {
    if (input === null || input.contents.empty || !(input.contents.head instanceof ExportModel)) {
      return
    }
    val model = input.contents.head as ExportModel
    genModelUtil.resource = model.eResource
    var IProject project = null
    if (input.URI.isPlatformResource) {
      val res = ResourcesPlugin.workspace.root.findMember(input.URI.toPlatformString(true))
      if (res !== null) {
        project = res.project
      }
    }

    generatorSupport.executeWithProjectResourceLoader(project, [
      compilationContext = generatorSupport.getCompilationContext(model, genModelUtil)

      generateExportedNamesProvider(model, fsa)
      generateResourceDescriptionManager(model, fsa)
      generateResourceDescriptionStrategy(model, fsa)
      generateResourceDescriptionConstants(model, fsa)
      generateFingerprintComputer(model, fsa)
      generateFragmentProvider(model, fsa)
      generateFeatureExtension(model, fsa)
    ])
  }

  def generateExportedNamesProvider(ExportModel model, IFileSystemAccess fsa) {
    val fileName = model.exportedNamesProvider.toFileName
    fsa.generateFile(fileName, exportedNamesProviderGenerator.generate(model, compilationContext, genModelUtil))
  }

  def generateResourceDescriptionManager(ExportModel model, IFileSystemAccess fsa) {
    if(!model.extension){
      val fileName = model.resourceDescriptionManager.toFileName
      fsa.generateFile(fileName, ExportOutputConfigurationProvider.STUB_OUTPUT, resourceDescriptionManagerGenerator.generate(model, compilationContext, genModelUtil))
    }
  }

  def generateResourceDescriptionStrategy(ExportModel model, IFileSystemAccess fsa) {
    val fileName = model.resourceDescriptionStrategy.toFileName
    fsa.generateFile(fileName, resourceDescriptionStrategyGenerator.generate(model, compilationContext, genModelUtil))
  }

  def generateResourceDescriptionConstants(ExportModel model, IFileSystemAccess fsa) {
    val fileName = model.resourceDescriptionConstants.toFileName
    fsa.generateFile(fileName, resourceDescriptionConstantsGenerator.generate(model, compilationContext, genModelUtil))
  }

  def generateFingerprintComputer(ExportModel model, IFileSystemAccess fsa) {
    val fileName = model.fingerprintComputer.toFileName
    fsa.generateFile(fileName, fingerprintComputerGenerator.generate(model, compilationContext, genModelUtil))
  }

  def generateFragmentProvider(ExportModel model, IFileSystemAccess fsa) {
    val fileName = model.fragmentProvider.toFileName
    if (model.exports.exists(e|e.fingerprint && e.fragmentAttribute !== null) || model.isExtension) {
      fsa.generateFile(fileName, fragmentProviderGenerator.generate(model, compilationContext, genModelUtil))
    } else {
      fsa.generateFile(fileName, '''
        package «model.fragmentProvider.toJavaPackage»;

        import com.avaloq.tools.ddk.xtext.linking.ShortFragmentProvider;


        public class «model.fragmentProvider.toSimpleName» extends ShortFragmentProvider {

        }
      ''')
    }
  }

  def generateFeatureExtension(ExportModel model, IFileSystemAccess fsa) {
    if (model.extension) {
      val fileName = model.exportFeatureExtension.toFileName
      fsa.generateFile(fileName, exportFeatureExtensionGenerator.generate(model, compilationContext, genModelUtil))
    }
  }

}
