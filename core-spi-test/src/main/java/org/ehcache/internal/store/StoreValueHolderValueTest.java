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

package org.ehcache.internal.store;

import org.ehcache.core.spi.store.Store;
import org.ehcache.spi.test.SPITest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Test the {@link Store.ValueHolder#get(Object)} contract of the
 * {@link Store.ValueHolder Store.ValueHolder} interface.
 *
 * @author Aurelien Broszniowski
 */

public class StoreValueHolderValueTest<K, V> extends SPIStoreTester<K, V> {

  public StoreValueHolderValueTest(final StoreFactory<K, V> factory) {
    super(factory);
  }

  @SPITest
  public void valueIsHeldByValueHolder()
      throws IllegalAccessException, InstantiationException {
    V value = factory.createValue(1);
    Store.ValueHolder<V> valueHolder = factory.newValueHolder(value);

    try {
      assertThat(valueHolder.get(), is(equalTo(value)));
    } catch (Exception e) {
      System.err.println("Warning, an exception is thrown due to the SPI test");
      e.printStackTrace();
    }
  }
}
