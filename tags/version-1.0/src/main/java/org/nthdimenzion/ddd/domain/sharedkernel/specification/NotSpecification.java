package org.nthdimenzion.ddd.domain.sharedkernel.specification;

public class NotSpecification<T> extends CompositeSpecification<T> {
    private ISpecification<T> wrapped;

    public NotSpecification(ISpecification<T> wrapped) {
        this.wrapped = wrapped;
    }

    public boolean isSatisfiedBy(T candidate) {
        return !wrapped.isSatisfiedBy(candidate);
    }
}
