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
package com.jdon.sample.test.command;

import com.jdon.annotation.Component;
import com.jdon.annotation.Introduce;

@Component("producerforCommand")
@Introduce("componentmessage")
public class AComponent implements AComponentIF {

	public TestCommand ma(BModel bModel) {
		System.out.print("send to BModel =" + bModel.getId());
		return new TestCommand(99);
	}
}
