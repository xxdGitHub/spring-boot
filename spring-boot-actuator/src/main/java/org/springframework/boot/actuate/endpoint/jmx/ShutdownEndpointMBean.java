/*
 * Copyright 2013-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.endpoint.jmx;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.ShutdownEndpoint;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * Special endpoint wrapper for {@link ShutdownEndpoint}.
 *
 * @author Christian Dupuis
 * @author Andy Wilkinson
 */
@ManagedResource
public class ShutdownEndpointMBean extends EndpointMBean {

	/**
	 * Create a new {@link ShutdownEndpointMBean} instance.
	 * @param beanName the bean name
	 * @param endpoint the endpoint to wrap
	 * @deprecated since 1.3 in favor of
	 * {@link #ShutdownEndpointMBean(String, Endpoint, ObjectMapper)}
	 */
	@Deprecated
	public ShutdownEndpointMBean(String beanName, Endpoint<?> endpoint) {
		super(beanName, endpoint);
	}

	/**
	 * Create a new {@link ShutdownEndpointMBean} instance.
	 * @param beanName the bean name
	 * @param endpoint the endpoint to wrap
	 * @param objectMapper the {@link ObjectMapper} used to convert the payload
	 */
	public ShutdownEndpointMBean(String beanName, Endpoint<?> endpoint,
			ObjectMapper objectMapper) {
		super(beanName, endpoint, objectMapper);
	}

	@ManagedOperation(description = "Shutdown the ApplicationContext")
	public Object shutdown() {
		return convert(getEndpoint().invoke());
	}
}