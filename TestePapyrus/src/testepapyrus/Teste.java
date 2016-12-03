package testepapyrus;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

public class Teste extends ViewPart {

	public static final String ID = "testepapyrus.Teste"; //$NON-NLS-1$
	private final FormToolkit toolkit = new FormToolkit(Display.getCurrent());
	private Text txtName;
	private Text txtKey;
	private Text txtDescription;
	private Text txtDesign;
	private Table tableFR;

	public Teste() {
	}

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createPartControl(Composite parent) {
		Composite container = toolkit.createComposite(parent, SWT.NONE);
		toolkit.paintBordersFor(container);
		
		Button btnSave = new Button(container, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnSave.setBounds(31, 373, 94, 28);
		toolkit.adapt(btnSave, true, true);
		btnSave.setText("Save");
		
		txtName = new Text(container, SWT.BORDER);
		txtName.setBounds(141, 7, 213, 19);
		toolkit.adapt(txtName, true, true);
		
		txtKey = new Text(container, SWT.BORDER);
		txtKey.setBounds(141, 84, 213, 19);
		toolkit.adapt(txtKey, true, true);
		
		Label lblName = new Label(container, SWT.NONE);
		lblName.setBounds(31, 10, 55, 15);
		toolkit.adapt(lblName, true, true);
		lblName.setText("Name:");
		
		Label lblParentElement = new Label(container, SWT.NONE);
		lblParentElement.setBounds(31, 45, 83, 15);
		toolkit.adapt(lblParentElement, true, true);
		lblParentElement.setText("Parent element:");
		
		Label lblKeyStakeholder = new Label(container, SWT.NONE);
		lblKeyStakeholder.setBounds(31, 84, 86, 15);
		toolkit.adapt(lblKeyStakeholder, true, true);
		lblKeyStakeholder.setText("Key stakeholder:");
		
		ComboViewer comboViewer = new ComboViewer(container, SWT.NONE);
		Combo comboParent = comboViewer.getCombo();
		comboParent.setItems(new String[] {"Mapping"});
		comboParent.setBounds(141, 42, 213, 23);
		toolkit.paintBordersFor(comboParent);
		
		Label lblDescription = new Label(container, SWT.NONE);
		lblDescription.setBounds(31, 131, 73, 15);
		toolkit.adapt(lblDescription, true, true);
		lblDescription.setText("Description:");
		
		txtDescription = new Text(container, SWT.BORDER);
		txtDescription.setBounds(31, 152, 213, 83);
		toolkit.adapt(txtDescription, true, true);
		
		Label lblDesignRationale = new Label(container, SWT.NONE);
		lblDesignRationale.setBounds(31, 249, 94, 15);
		toolkit.adapt(lblDesignRationale, true, true);
		lblDesignRationale.setText("Design rationale:");
		
		txtDesign = new Text(container, SWT.BORDER);
		txtDesign.setBounds(31, 270, 216, 83);
		toolkit.adapt(txtDesign, true, true);
		
		tableFR = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		tableFR.setBounds(284, 152, 262, 139);
		toolkit.adapt(tableFR);
		toolkit.paintBordersFor(tableFR);
		tableFR.setHeaderVisible(true);
		tableFR.setLinesVisible(true);

		createActions();
		initializeToolBar();
		initializeMenu();
	}

	public void dispose() {
		toolkit.dispose();
		super.dispose();
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar() {
		IToolBarManager tbm = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu() {
		IMenuManager manager = getViewSite().getActionBars().getMenuManager();
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
