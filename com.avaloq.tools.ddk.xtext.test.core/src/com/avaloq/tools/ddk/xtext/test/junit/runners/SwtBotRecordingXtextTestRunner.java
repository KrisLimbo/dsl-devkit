/*******************************************************************************
 * Copyright (c) 2016 Avaloq Group AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Avaloq Group AG - initial API and implementation
 *******************************************************************************/
package com.avaloq.tools.ddk.xtext.test.junit.runners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.FrameworkMethod;


/**
 * A {@link org.junit.runner.Runner} to use with swt bot tests featuring screenshot recording.
 */
// suppress warning restriction of org.junit.internal.runners.statements.InvokeMethod
@SuppressWarnings("restriction")
public class SwtBotRecordingXtextTestRunner {
  /**
   * Used to mark a test class or method to define the recording interval in milliseconds to use for the {@link SwtBotRecordingXtextTestRunner}.
   */
  @Retention(RetentionPolicy.RUNTIME)
  @Target({ElementType.METHOD, ElementType.TYPE})
  public static @interface RecordingInterval {
    /**
     * Returns the recording interval value in milliseconds.
     *
     * @return the recording interval value in milliseconds
     */
    long value();
  }

  // /**
  // * Creates a new instance of {@link SwtBotRecordingXtextTestRunner}.
  // * Only called reflectively. Do not use programmatically.
  // *
  // * @param testClass
  // * the test class to run
  // * @throws InitializationError
  // * if any initialization failed
  // */
  // public SwtBotRecordingXtextTestRunner(final Class<? extends AbstractSystemTest> testClass) throws InitializationError {
  // super(testClass);
  // CoreSwtbotTools.initializePreferences();
  // testRunRecording = new TestRunRecording(getTestClass().getJavaClass(), SWTBotPreferences.SCREENSHOTS_DIR);
  // // TODO: add custom made {@link org.junit.runners.model.Statement} to check for exceptions during the test
  // // (by default test failures are reported only after the teardown has taken place)
  // }

  public void run(final RunNotifier notifier) {

  }

  protected void runChild(final FrameworkMethod method, final RunNotifier notifier) {

  }

}
