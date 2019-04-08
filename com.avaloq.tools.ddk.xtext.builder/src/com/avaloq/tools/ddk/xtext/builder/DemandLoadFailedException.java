/*******************************************************************************
 * Copyright (c) 2016 Avaloq Evolution AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Evolution AG - initial API and implementation
 *******************************************************************************/

package com.avaloq.tools.ddk.xtext.builder;

/**
 * Exception thrown when a resource set fails to demand-load a resource because of not having enough memory.
 */
public class DemandLoadFailedException extends RuntimeException {

  private static final long serialVersionUID = 8162994321500072190L;

}
