
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2018, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.source.microsoft.v5;

import org.carrot2.core.test.MultipageDocumentSourceTestBase;
import org.carrot2.source.MultipageSearchEngineMetadata;
import org.carrot2.util.tests.UsesExternalServices;
import org.junit.*;

/** */
@UsesExternalServices
public class Bing5DocumentSourceTest extends MultipageDocumentSourceTestBase<Bing5DocumentSource> {
  @Before
  public void checkKeyAvailable() {
    Assume.assumeTrue(System.getProperty(Bing5DocumentSource.SYSPROP_BING5_API) != null);
  }
  
  @Override
  protected boolean hasTotalResultsEstimate() {
    return true;
  }

  @Override
  public Class<Bing5DocumentSource> getComponentClass() {
    return Bing5DocumentSource.class;
  }

  @Override
  protected MultipageSearchEngineMetadata getSearchEngineMetadata() {
    return Bing5DocumentSource.METADATA;
  }

  @Override
  protected boolean hasUtfResults() {
    return true;
  }
}
