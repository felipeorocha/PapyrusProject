package testepapyrus;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.services.openelement.service.OpenElementService;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

//import br.tracetool.plugin.animation.AnimationBuilder;

/**
 * Class which access Editor from Papyrus and shows the UML element that matches with umlElementID
 * 
 */
public class PapyrusEditor {
	
	//static ArrayList<AnimationBuilder> viewList = new ArrayList<AnimationBuilder>();
	
	//static int myColor = 0;
	
	//static int i = 0;
	
	/**
	 * Get access to the UML element in Papyrus' Editor.
	 * @param umlElementID
	 * @param window 
	 */
	public static void getPapyrusEditor(String umlElementID, IWorkbenchWindow window) {
		IEditorPart editorPart = window.getActivePage().getActiveEditor();
		
		if (editorPart instanceof PapyrusMultiDiagramEditor) {
			PapyrusMultiDiagramEditor papyrusEditor = (PapyrusMultiDiagramEditor) editorPart;
			
			// Get the services registry from the editor
			ServicesRegistry papyrusEditorRegistry = (ServicesRegistry) papyrusEditor.getAdapter(ServicesRegistry.class);
			
			// variable with the element found in Papyrus' Editor
			EObject elementFound = UMLElement.findUmlElementInPapyrus(papyrusEditorRegistry, umlElementID);
			
			/*
			if (elementFound instanceof State) {
				State state = (State) elementFound;
				state.setName(state.getName()+" "+"AQUI!!");
				//System.out.println(state.getName());
			}
			*/
			
			try {
				// Get access to the editor
				IMultiDiagramEditor editor = papyrusEditor;
				ServicesRegistry editorRegistry = editor.getServicesRegistry();
				
				// Show the UML element in editor
				OpenElementService openElement = editorRegistry.getService(OpenElementService.class);
				openElement.openSemanticElement(elementFound);
				
			} catch (ServiceException | PartInitException e) {
				e.printStackTrace();
			}
			
			/*
			Set<View> referencingObjects = AnimationBuilder.setUp(elementFound);
			
			for (View view : referencingObjects) {
				if (elementFound instanceof State) {
					MState.checkStateShape(view);
				}
			}*/
			
//			AnimationBuilder.changeUmlElementColor(elementFound);
		}
		else {
			System.err.println("editorPart should be an instance of PapyrusMultiDiagramEditor");
		}
	}
}

