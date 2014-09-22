package com.shinzul.blog.entity;

import java.io.Serializable;
import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import com.shinzul.blog.jackson.BigIntegerAsStringSerializer;

@Document
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonSerialize(using=BigIntegerAsStringSerializer.class)
	private BigInteger id;
	@Indexed(unique=true)
	private String name;
	
	public Category() {
		super();
	}
	
	public Category(BigInteger id, String name) {
		this();
		this.id = id;
		this.name = name;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Category other = (Category) obj;
		return Objects.equal(this.id, other.id)
				&& Objects.equal(this.name, other.name);
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				.addValue(this.id)
				.addValue(this.name)
				.toString();
	}
	

}
