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
package com.jdon.sample.test.cqrs;

import com.jdon.annotation.Introduce;
import com.jdon.annotation.Service;
import com.jdon.domain.message.DomainMessage;
import com.jdon.sample.test.cqrs.a.AggregateRootA;

@Service("aService")
@Introduce("componentmessage")
public class AServiceImpl implements AService {

	private ABRepositoryIF aBRepository;

	public AServiceImpl(ABRepositoryIF aBRepository) {
		super();
		this.aBRepository = aBRepository;
	}

	public AggregateRootA getAggregateRootA(String id) {
		return aBRepository.loadA(id);
	}

	public DomainMessage commandA(String rootId, AggregateRootA model, int state) {
		System.out.print("\n send to AggregateRootA =" + model.getId());
		return new DomainMessage(new ParameterVO(state), 60000);
	}

}
