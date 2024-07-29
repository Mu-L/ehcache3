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

package org.ehcache.transactions.xa.internal.commands;

import org.ehcache.transactions.xa.internal.XAValueHolder;

/**
 * {@link Command} implementation representing an eviction.
 *
 * @author Ludovic Orban
 */
public class StoreEvictCommand<V> implements Command<V> {

  private final V oldValue;

  public StoreEvictCommand(V oldValue) {
    this.oldValue = oldValue;
  }

  @Override
  public V getOldValue() {
    return oldValue;
  }

  @Override
  public XAValueHolder<V> getNewValueHolder() {
    return null;
  }
}
