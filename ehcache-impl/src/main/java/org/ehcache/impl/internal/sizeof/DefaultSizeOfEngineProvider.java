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
package org.ehcache.impl.internal.sizeof;

import org.ehcache.config.ResourceUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.ServiceUtils;
import org.ehcache.spi.service.ServiceProvider;
import org.ehcache.spi.service.Service;
import org.ehcache.spi.service.ServiceConfiguration;

/**
 * @author Abhilash
 *
 */
@Deprecated
public class DefaultSizeOfEngineProvider implements org.ehcache.core.spi.store.heap.SizeOfEngineProvider {

  private final long maxObjectGraphSize;
  private final long maxObjectSize;

  public DefaultSizeOfEngineProvider(long maxObjectGraphSize, long maxObjectSize) {
    this.maxObjectGraphSize = maxObjectGraphSize;
    this.maxObjectSize = maxObjectSize;
  }

  @Override
  public void start(ServiceProvider<Service> serviceProvider) {
    //no op
  }

  @Override
  public void stop() {
    //no op
  }

  @Override
  public org.ehcache.core.spi.store.heap.SizeOfEngine createSizeOfEngine(ResourceUnit resourceUnit, ServiceConfiguration<?, ?>... serviceConfigs) {
    boolean isByteSized = resourceUnit instanceof MemoryUnit;
    if(!isByteSized) {
      return new NoopSizeOfEngine(); // Noop Size of Engine
    }
    org.ehcache.impl.config.store.heap.DefaultSizeOfEngineConfiguration config = ServiceUtils.findSingletonAmongst(org.ehcache.impl.config.store.heap.DefaultSizeOfEngineConfiguration.class, (Object[]) serviceConfigs);
    if(config != null) {
      long maxSize = config.getUnit().toBytes(config.getMaxObjectSize());
      return new DefaultSizeOfEngine(config.getMaxObjectGraphSize(), maxSize);
    }
    return new DefaultSizeOfEngine(maxObjectGraphSize, maxObjectSize);
  }
}
