package testepapyrus;

import org.antlr.runtime.tree.Tree;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.papyrus.infra.core.resource.ModelSet;
import org.eclipse.papyrus.infra.core.resource.NotFoundException;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.uml.tools.model.UmlModel;
import org.eclipse.uml2.uml.Class;

/**
 * Class which receives a ID and find its corresponding UML element 
 * 
 */
public class UMLElement {

	/**
	 * Method to find a UML element in Model Explorer View and Papyrus' Editor.
	 * @param registry - the registry from the respective ServicesRegistry
	 * @param elementID - the ID of the element which will be search in Model Explorer
	 * @return EObject with the UML element found in the Model Explorer's treeview or Papyrus' Editor
	 */
	public static EObject findUmlElementInPapyrus(ServicesRegistry registry, String elementID) {
		
		try {
			// create a ModelSet with the service from registry variable
			ModelSet papyrusModelSet = registry.getService(ModelSet.class);
			// create a UMLModel with the previous ModelSet
			UmlModel umlModel = (UmlModel)papyrusModelSet.getModel(UmlModel.MODEL_ID);
			// a EObject variable with the model's root
			EObject modelRoot = umlModel.lookupRoot();
			
			//The UML Resource
			Resource umlResource = modelRoot.eResource(); 
			
			//If ID is the EObject's URI Fragment, this will return the correct EObject
			EObject umlElement = umlResource.getEObject(elementID.trim());
			
			/*
			if (umlElement instanceof StateImpl){
				//Now I have the UML State element
				StateImpl state = (StateImpl)umlElement;
			    ShapeImpl shape;
			}*/
				
			return umlElement;
			
		} catch (ServiceException | NotFoundException e) {
			e.printStackTrace();
			// return if try throws a exception
			return null;
		}
	}
	
	/**
	 * Method to find a UML element in Model Explorer View and Papyrus' Editor.
	 * @param registry - the registry from the respective ServicesRegistry
	 * @param elementID - the ID of the element which will be search in Model Explorer
	 * @return EObject with the UML element found in the Model Explorer's treeview or Papyrus' Editor
	 */
	public static EObject findUmlElementInPapyrus(ServicesRegistry registry) {
		
		EObject eObject = null;
		
		try {
			// create a ModelSet with the service from registry variable
			ModelSet papyrusModelSet = registry.getService(ModelSet.class);
			// create a UMLModel with the previous ModelSet
			UmlModel umlModel = (UmlModel)papyrusModelSet.getModel(UmlModel.MODEL_ID);
			// a EObject variable with the model's root
			EObject modelRoot = umlModel.lookupRoot();
			
			//The UML Resource
			Resource umlResource = modelRoot.eResource(); 
			
			//If ID is the EObject's URI Fragment, this will return the correct EObject
			//EObject umlElement = umlResource.getEObject(elementID.trim());
			
			//If ID is the EObject's URI Fragment, this will return the correct EObject
			TreeIterator<EObject> allEObject  = umlResource.getAllContents();
			
			while (allEObject.hasNext()) {
				eObject = (EObject) allEObject.next();
				Class classToGet = null;
				if (eObject instanceof org.eclipse.uml2.uml.Class) {
					classToGet = (org.eclipse.uml2.uml.Class) eObject;
					if (classToGet.getName().equals("Aluno")) {
						System.out.println("Pegou o aluno....");
					}
				}
			}
			
				
			return eObject;
			
		} catch (ServiceException | NotFoundException e) {
			e.printStackTrace();
			// return if try throws a exception
			return null;
		}
	}
	
	
}

