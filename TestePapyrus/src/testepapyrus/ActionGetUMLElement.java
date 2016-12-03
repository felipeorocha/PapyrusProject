package testepapyrus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.papyrus.editor.PapyrusMultiDiagramEditor;
import org.eclipse.papyrus.infra.core.services.ServiceException;
import org.eclipse.papyrus.infra.core.services.ServicesRegistry;
import org.eclipse.papyrus.infra.gmfdiag.css.CSSShapeImpl;
import org.eclipse.papyrus.infra.services.openelement.service.OpenElementService;
import org.eclipse.papyrus.infra.ui.editor.IMultiDiagramEditor;
import org.eclipse.papyrus.uml.diagram.common.util.CrossReferencerUtil;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;

public class ActionGetUMLElement implements IObjectActionDelegate {

	public ActionGetUMLElement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run(IAction action) {
		
		String URIProject =null;
		
		List<NamedElement>	elementsUMLSelected = getSelectedUmlObject();
		
		IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		
		if (editorPart instanceof PapyrusMultiDiagramEditor) {
			PapyrusMultiDiagramEditor papyrusEditor = (PapyrusMultiDiagramEditor) editorPart;
			
			
			ServicesRegistry papyrusEditorRegistry = (ServicesRegistry) papyrusEditor.getAdapter(ServicesRegistry.class);
			
			try {
				OpenElementService openElementService = papyrusEditorRegistry.getService(OpenElementService.class);
				
				openElementService.openSemanticElement(UMLElement.findUmlElementInPapyrus(papyrusEditorRegistry));
				
				
				
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView("testepapyrus.Teste");
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			NamedElement named = elementsUMLSelected.get(0);
			
			Set<View> referencingObjects = new HashSet<View>();
			
			// getCrossReferencingViews returns a Set of Views which contains the element found
			referencingObjects = CrossReferencerUtil.getCrossReferencingViews(named, null);
			
			System.out.println(referencingObjects.size());
			
			for (View view : referencingObjects) {
				if (view instanceof CSSShapeImpl) {
					CSSShapeImpl cssShapeImpl = (CSSShapeImpl)view;
					
					
					TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(cssShapeImpl);
				    ted.getCommandStack().execute(new RecordingCommand(ted) {
						
						@Override
						protected void doExecute() {
							cssShapeImpl.setFillColor(10806172);
							cssShapeImpl.setBold(true);
							cssShapeImpl.setItalic(true);
							cssShapeImpl.setFontHeight(20);
						}
					});
					
				}
			}
			
			
//			AnimationBuilder.changeUmlElementColor(elementFound);
		}
		


	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		// TODO Auto-generated method stub

	}

	protected List<NamedElement> getSelectedUmlObject() {
		List<Object> selections = lookupSelectedElements(); // see
		// "How to Get the Current Selection from Java code"

		List<NamedElement> results = new ArrayList<NamedElement>();

		// create model with EList<EObject> objects
		for (Object obj : selections) {
			// Adapt object to NamedElement
			NamedElement ele = null;
			if (obj instanceof IAdaptable) {
				ele = (NamedElement) ((IAdaptable) obj).getAdapter(NamedElement.class);
			}
			if (ele == null) {
				ele = (NamedElement) Platform.getAdapterManager().getAdapter(obj, NamedElement.class);
			}
			if (ele != null) {
				results.add(ele);
			}
		}
		return results;
	}

	protected List<Object> lookupSelectedElements() {

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ISelection selection = page.getSelection();
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) selection;
			return structuredSelection.toList();
		} else if (selection instanceof TreeSelection) {
			TreeSelection treeSelection = (TreeSelection) selection;
			return treeSelection.toList();
		}
		return null;

	}

}
