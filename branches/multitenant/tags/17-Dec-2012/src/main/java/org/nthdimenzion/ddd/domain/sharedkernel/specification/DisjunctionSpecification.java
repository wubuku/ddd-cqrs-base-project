package org.nthdimenzion.ddd.domain.sharedkernel.specification;


public class DisjunctionSpecification<T> extends CompositeSpecification<T>{
    private ISpecification<T>[] disjunction;

    public DisjunctionSpecification(ISpecification<T>... disjunction){
        this.disjunction = disjunction;
    }

    public boolean isSatisfiedBy(T candidate){
        for (ISpecification<T> spec : disjunction){
        	if (spec.isSatisfiedBy(candidate))
        		return true;
        }
    	
    	return false;
    }
}
