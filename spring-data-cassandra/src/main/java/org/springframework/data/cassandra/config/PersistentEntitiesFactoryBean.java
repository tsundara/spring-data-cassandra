/*
 * Copyright 2020 the original author or authors.
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
package org.springframework.data.cassandra.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.context.PersistentEntities;

/**
 * Simple helper to be able to wire the {@link MappingContext} from a {@link MappingCassandraConverter} bean available
 * in the application context.
 */
class PersistentEntitiesFactoryBean implements FactoryBean<PersistentEntities> {

	private final MappingCassandraConverter converter;

	/**
	 * Creates a new {@link PersistentEntitiesFactoryBean} for the given {@link MappingCassandraConverter}.
	 *
	 * @param converter must not be {@literal null}.
	 */
	public PersistentEntitiesFactoryBean(MappingCassandraConverter converter) {
		this.converter = converter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public PersistentEntities getObject() {
		return PersistentEntities.of(converter.getMappingContext());
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<?> getObjectType() {
		return PersistentEntities.class;
	}
}
