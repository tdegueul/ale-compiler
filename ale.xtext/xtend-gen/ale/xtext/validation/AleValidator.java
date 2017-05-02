/**
 * generated by Xtext 2.10.0
 */
package ale.xtext.validation;

import ale.utils.AleEcoreUtil;
import ale.xtext.ale.AleClass;
import ale.xtext.ale.AlePackage;
import ale.xtext.ale.ImportAle;
import ale.xtext.ale.ImportEcore;
import ale.xtext.ale.Root;
import ale.xtext.validation.AbstractAleValidator;
import ale.xtext.validation.Graph;
import ale.xtext.validation.GraphUtil;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class AleValidator extends AbstractAleValidator {
  private String SYNTAX_URI_NOT_FOUND = "syntax.uri.not.found";
  
  private String SEMANTICS_IMPORT_LOOP = "semantics.import.loop";
  
  private String ALE_CLASS_NAME_ERROR = "ale.class.name";
  
  private String ALE_IMPORT_MISSING_ERROR = "ale.import.missing";
  
  /**
   * TODO
   * Check non cyclic inheritance of the semantics
   * Check non conflicting ecore classnames
   */
  @Check
  public void checkValidSyntax(final ImportEcore syntax) {
    AleEcoreUtil _aleEcoreUtil = new AleEcoreUtil();
    String _ref = syntax.getRef();
    ResourceSetImpl _resourceSetImpl = new ResourceSetImpl();
    final EPackage ePackage = _aleEcoreUtil.loadEPackageByEcorePath(_ref, _resourceSetImpl);
    boolean _equals = Objects.equal(ePackage, null);
    if (_equals) {
      this.error(
        "Package path can\'t be resolve", syntax, 
        AlePackage.Literals.IMPORT_ECORE__REF, 
        this.SYNTAX_URI_NOT_FOUND);
    }
  }
  
  private void loadAllSemantics(final Root root, final List<Root> sems) {
    EList<ImportAle> _importsAle = root.getImportsAle();
    final Function1<ImportAle, Root> _function = (ImportAle it) -> {
      return it.getRef();
    };
    final List<Root> ales = ListExtensions.<ImportAle, Root>map(_importsAle, _function);
    for (final Root ale : ales) {
      boolean _contains = sems.contains(ale);
      boolean _not = (!_contains);
      if (_not) {
        sems.add(ale);
        this.loadAllSemantics(ale, sems);
      }
    }
  }
  
  @Check
  public void checkImportSemanticNonCyclic(final Root root) {
    final ArrayList<Root> recDeps = CollectionLiterals.<Root>newArrayList();
    this.loadAllSemantics(root, recDeps);
    boolean _contains = recDeps.contains(root);
    if (_contains) {
      this.error("Ale dependencies loop", root, AlePackage.Literals.ROOT__NAME, this.SEMANTICS_IMPORT_LOOP);
    }
  }
  
  /**
   * Validates that the syntactic domain of the ale parents is a subset of the one defined for the current Ale file
   */
  @Check
  public void checkAleExtendsMatchesSyntactically(final ImportAle importAle) {
    EObject _rootContainer = EcoreUtil2.getRootContainer(importAle);
    final Root root = ((Root) _rootContainer);
    final AleEcoreUtil aeu = new AleEcoreUtil();
    final ResourceSetImpl rs = new ResourceSetImpl();
    final GraphUtil gu = new GraphUtil(rs);
    EList<ImportEcore> _importsEcore = root.getImportsEcore();
    final Function1<ImportEcore, EPackage> _function = (ImportEcore it) -> {
      String _ref = it.getRef();
      return aeu.loadEPackageByEcorePath(_ref, rs);
    };
    final List<EPackage> ePackages = ListExtensions.<ImportEcore, EPackage>map(_importsEcore, _function);
    final Graph<EClass> graph = gu.buildGraph(ePackages);
    final Function1<Graph.GraphNode, EClass> _function_1 = (Graph.GraphNode it) -> {
      return it.elem;
    };
    Iterable<EClass> _map = IterableExtensions.<Graph.GraphNode, EClass>map(graph.nodes, _function_1);
    final List<EClass> allEClasses = IterableExtensions.<EClass>toList(_map);
    Root _ref = importAle.getRef();
    EList<ImportEcore> _importsEcore_1 = _ref.getImportsEcore();
    final Function1<ImportEcore, EPackage> _function_2 = (ImportEcore it) -> {
      String _ref_1 = it.getRef();
      return aeu.loadEPackageByEcorePath(_ref_1, rs);
    };
    List<EPackage> _map_1 = ListExtensions.<ImportEcore, EPackage>map(_importsEcore_1, _function_2);
    Graph<EClass> _buildGraph = gu.buildGraph(_map_1);
    final Function1<Graph.GraphNode, EClass> _function_3 = (Graph.GraphNode it) -> {
      return it.elem;
    };
    Iterable<EClass> _map_2 = IterableExtensions.<Graph.GraphNode, EClass>map(_buildGraph.nodes, _function_3);
    final List<EClass> allEClassesImported = IterableExtensions.<EClass>toList(_map_2);
    final Function1<EClass, Boolean> _function_4 = (EClass it) -> {
      boolean _contains = allEClasses.contains(it);
      return Boolean.valueOf((!_contains));
    };
    final Iterable<EClass> missingEPackages = IterableExtensions.<EClass>filter(allEClassesImported, _function_4);
    boolean _isEmpty = IterableExtensions.isEmpty(missingEPackages);
    boolean _not = (!_isEmpty);
    if (_not) {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("Missing EPackages: ");
      {
        boolean _hasElements = false;
        for(final EClass missing : missingEPackages) {
          if (!_hasElements) {
            _hasElements = true;
          } else {
            _builder.appendImmediate(", ", "");
          }
          String _name = missing.getName();
          _builder.append(_name, "");
        }
      }
      this.error(_builder.toString(), importAle, 
        AlePackage.Literals.IMPORT_ALE__REF, this.ALE_IMPORT_MISSING_ERROR);
    }
  }
  
  /**
   * Check if the name of the open class matches the name of an imported EClass element
   */
  @Check
  public void checkIsOpenClassImported(final AleClass aleClass) {
    final String name = aleClass.getName();
    EObject _rootContainer = EcoreUtil2.getRootContainer(aleClass);
    final Root root = ((Root) _rootContainer);
    final AleEcoreUtil aeu = new AleEcoreUtil();
    final ResourceSetImpl rs = new ResourceSetImpl();
    GraphUtil _graphUtil = new GraphUtil(rs);
    EList<ImportEcore> _importsEcore = root.getImportsEcore();
    final Function1<ImportEcore, EPackage> _function = (ImportEcore it) -> {
      String _ref = it.getRef();
      return aeu.loadEPackageByEcorePath(_ref, rs);
    };
    List<EPackage> _map = ListExtensions.<ImportEcore, EPackage>map(_importsEcore, _function);
    Graph<EClass> _buildGraph = _graphUtil.buildGraph(_map);
    final Function1<Graph.GraphNode, EClass> _function_1 = (Graph.GraphNode it) -> {
      return it.elem;
    };
    Iterable<EClass> _map_1 = IterableExtensions.<Graph.GraphNode, EClass>map(_buildGraph.nodes, _function_1);
    final List<EClass> allEClasses = IterableExtensions.<EClass>toList(_map_1);
    final Function1<EClass, Boolean> _function_2 = (EClass it) -> {
      String _name = it.getName();
      return Boolean.valueOf(Objects.equal(_name, name));
    };
    boolean _exists = IterableExtensions.<EClass>exists(allEClasses, _function_2);
    boolean _not = (!_exists);
    if (_not) {
      this.error("Non existing EClass for the Ale Class", aleClass, 
        AlePackage.Literals.ALE_CLASS__NAME, this.ALE_CLASS_NAME_ERROR);
    }
  }
}
