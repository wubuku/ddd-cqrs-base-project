package org.nthdimenzion.ddd.domain.sharedkernel.specification;

public abstract class CompositeSpecification<T> implements ISpecification<T> {
	
 
    public ISpecification<T> and(ISpecification<T> other){
		return new AndSpecification<T>(this, other);
    }
    public ISpecification<T> or(ISpecification<T> other){
		return new OrSpecification<T>(this, other);
    }
	public ISpecification<T> not(){
		return new NotSpecification<T>(this);
    }
}
