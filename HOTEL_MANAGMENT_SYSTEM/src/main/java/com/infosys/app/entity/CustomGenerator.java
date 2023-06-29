package com.infosys.app.entity;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomGenerator implements IdentifierGenerator {
	private static final long serialVersionUID = 1L;

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		return "ID-" + UUID.randomUUID().toString();
	}

}
