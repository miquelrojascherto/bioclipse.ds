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
package net.bioclipse.ds.ui.views;

import net.bioclipse.ds.Activator;
import net.bioclipse.ds.model.IDSTest;
import net.bioclipse.ds.model.ITestResult;
import net.bioclipse.ds.model.TestRun;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;


public class TestsViewLabelProvider implements ILabelProvider{

    public Image getImage( Object element ) {

        ImageDescriptor desc=null;

        if ( element instanceof ITestResult ) {
            ITestResult match = (ITestResult) element;
            if (match.getTestRun().isRun()){
                if (match.getTestRun().getMatches().size()>0){
                    desc=Activator.getImageDecriptor( "icons/hit.png" );
                }
                else{
                    desc=Activator.getImageDecriptor( "icons/testok.gif" );
                }
            }
            else{
                desc=Activator.getImageDecriptor( "icons/test.gif" );
            }
        }
        else if ( element instanceof IDSTest ) {
            IDSTest test = (IDSTest)element;
            try{
                desc=Activator.imageDescriptorFromPlugin( test.getPluginID(), test.getIcon() );
            }catch (Exception e){
                desc=null;
            }
        }
        else if ( element instanceof TestRun ) {

            TestRun run = (TestRun) element;
            if (run.isRun()){
                //If we have matches, test has failed
                if (run.hasMatches()){
                    desc=Activator.getImageDecriptor( "icons/tsuiteerror.gif" );
                }

                //If not, all is well
                else{
                    desc=Activator.getImageDecriptor( "icons/tsuiteok.gif" );
                }
            }
            
            //If not run yet...
            else{
                desc=Activator.getImageDecriptor( "icons/tsuite.gif" );
            }

            //            if (run.getTest().getIcon()!=null)
            //                desc=Activator.getImageDecriptor( run.getTest().getIcon() );
            //            else
            //                desc=null;
        }

        if (desc==null)
            return null;

        return desc.createImage();
    }

    public String getText( Object element ) {

        if ( element instanceof ITestResult ) {
            ITestResult match = (ITestResult) element;
            return match.getName();
        }
        else if ( element instanceof TestRun ) {
            TestRun run = (TestRun) element;
            if (run.hasMatches())
                return run.getTest().getName() + " [" + run.getMatches().size()+" hits]";
            else
                return run.getTest().getName();
        }

        return element.toString();
    }

    public void addListener( ILabelProviderListener listener ) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty( Object element, String property ) {
        return false;
    }

    public void removeListener( ILabelProviderListener listener ) {
    }

}
