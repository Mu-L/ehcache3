/*
 * Copyright Terracotta, Inc.
 * Copyright IBM Corp. 2024, 2025
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehcache.impl.config.loaderwriter;

import org.ehcache.spi.loaderwriter.CacheLoaderWriter;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Mockito.mock;

public class DefaultCacheLoaderWriterProviderConfigurationTest {

  @Test @SuppressWarnings("unchecked")
  public void testDeriveDetachesCorrectly() {
    DefaultCacheLoaderWriterProviderConfiguration configuration = new DefaultCacheLoaderWriterProviderConfiguration();
    configuration.addLoaderFor("foo", (Class<? extends CacheLoaderWriter<?, ?>>) mock(CacheLoaderWriter.class).getClass());

    DefaultCacheLoaderWriterProviderConfiguration derived = configuration.build(configuration.derive());

    assertThat(derived, is(not(sameInstance(configuration))));
    assertThat(derived.getDefaults(), is(not(sameInstance(configuration.getDefaults()))));
    assertThat(derived.getDefaults(), is(configuration.getDefaults()));
  }
}
