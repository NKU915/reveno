/** 
 *  Copyright (c) 2015 The original author or authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.reveno.atp.core.engine.components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.reveno.atp.api.transaction.TransactionContext;

public class TransactionsManager {

	@SuppressWarnings("unchecked")
	public <T> void registerTransaction(Class<T> transactionType, BiConsumer<T, TransactionContext> handler) {
		txs.put(transactionType, (BiConsumer<Object, TransactionContext>) handler);
	}
	
	public void execute(List<Object> transactions, TransactionContext context) {
		transactions.forEach(t -> txs.get(t.getClass()).accept(t, context));
	}
	
	protected Map<Class<?>, BiConsumer<Object, TransactionContext>> txs = new HashMap<>();
	
}