/*
 * Copyright 2003-2009 the original author or authors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.jdon.sample.test.cqrs.a;

import com.jdon.annotation.Model;
import com.jdon.annotation.model.Inject;
import com.jdon.annotation.model.OnCommand;
import com.jdon.sample.test.cqrs.ParameterVO;

@Model
public class AggregateRootA {
	private String id;

	private String aggregateRootBId;

	private int state = 100;

	@Inject
	private DomainEventProduceIF domainEventProducer;

	public AggregateRootA(String id) {
		super();
		this.id = id;
	}

	@OnCommand("CommandtoEventA")
	public Object save(ParameterVO parameterVO) {
		this.state = parameterVO.getValue() + state;
		System.out.print("\n AggregateRootA Action " + state);

		return domainEventProducer.sendtoAnotherAggragate(aggregateRootBId, this.state);

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAggregateRootBId() {
		return aggregateRootBId;
	}

	public void setAggregateRootBId(String aggregateRootBId) {
		this.aggregateRootBId = aggregateRootBId;
	}

}
