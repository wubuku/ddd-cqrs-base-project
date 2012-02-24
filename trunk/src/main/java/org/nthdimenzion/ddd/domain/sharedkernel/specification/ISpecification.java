package org.nthdimenzion.ddd.domain.sharedkernel.specification;

public interface ISpecification<T> {
	boolean isSatisfiedBy(T candidate);

	ISpecification<T> and(ISpecification<T> other);

	ISpecification<T> or(ISpecification<T> other);

	ISpecification<T> not();
}
