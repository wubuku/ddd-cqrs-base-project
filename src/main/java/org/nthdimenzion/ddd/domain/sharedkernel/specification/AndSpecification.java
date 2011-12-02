package org.nthdimenzion.ddd.domain.sharedkernel.specification;

public class AndSpecification<T> extends CompositeSpecification<T>{
    private ISpecification<T> a;
    private ISpecification<T> b;

    public AndSpecification(ISpecification<T> a, ISpecification<T> b){
        this.a = a;
        this.b = b;
    }

    public boolean isSatisfiedBy(T candidate){
        return a.isSatisfiedBy(candidate) && b.isSatisfiedBy(candidate);
    }
}
