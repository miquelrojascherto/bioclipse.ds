/*******************************************************************************
 * Copyright (c) 2009 Ola Spjuth.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Ola Spjuth - initial API and implementation
 ******************************************************************************/
package net.bioclipse.ds.model;

import net.bioclipse.cdk.domain.ISubStructure;


/**
 * A base interface for all test results. Extends ISubStructure since even if 
 * no substructure is returned it should clear previous substructure selections.
 * @author ola
 *
 */
public interface ITestResult extends ISubStructure{

    
    /**
     * The name of this match to be displayed in e.g. UI
     * @return
     */
    public String getName();

    public void setName( String name );

    /**
     * The parent TestRun. Required in e.g. TreeViewer.
     * @return
     */
    public TestRun getTestRun();

    public void setTestRun( TestRun testRun );

}
