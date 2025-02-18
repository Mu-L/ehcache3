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

package org.ehcache.impl.serialization;

import org.ehcache.core.spi.store.TransientStateRepository;
import org.ehcache.spi.persistence.StateHolder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * TransientStateRepositoryTest
 */
public class TransientStateRepositoryTest {

  @Test
  public void testRemembersCreatedMaps() throws Exception {
    TransientStateRepository repository = new TransientStateRepository();
    StateHolder<Long, String> test = repository.getPersistentStateHolder("test", Long.class, String.class, c -> true, null);
    test.putIfAbsent(42L, "Again??");

    test = repository.getPersistentStateHolder("test", Long.class, String.class, c -> true, null);
    assertThat(test.get(42L), is("Again??"));
  }

}
