/*
 * Copyright Terracotta, Inc.
 * Copyright Super iPaaS Integration LLC, an IBM Company 2024
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
package org.ehcache.clustered.client.internal.loaderwriter;

import org.ehcache.core.spi.service.ServiceFactory;
import org.ehcache.spi.service.ServiceCreationConfiguration;
import org.osgi.service.component.annotations.Component;

@Component
public class ClusteredLoaderWriterStoreProviderFactory implements ServiceFactory<ClusteredLoaderWriterStore.Provider> {

  @Override
  public ClusteredLoaderWriterStore.Provider create(ServiceCreationConfiguration<ClusteredLoaderWriterStore.Provider, ?> configuration) {
    return new ClusteredLoaderWriterStore.Provider();
  }

  @Override
  public Class<? extends ClusteredLoaderWriterStore.Provider> getServiceType() {
    return ClusteredLoaderWriterStore.Provider.class;
  }
}
