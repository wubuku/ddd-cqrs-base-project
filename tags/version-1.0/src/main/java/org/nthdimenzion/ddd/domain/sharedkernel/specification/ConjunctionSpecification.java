package org.nthdimenzion.ddd.domain.sharedkernel.specification;

public class ConjunctionSpecification<T> extends CompositeSpecification<T>{
    private ISpecification<T>[] conjunction;

    public ConjunctionSpecification(ISpecification<T>... conjunction){
        this.conjunction = conjunction;
    }

    public boolean isSatisfiedBy(T candidate){
        for (ISpecification<T> spec : conjunction){
        	if (!spec.isSatisfiedBy(candidate))
        		return false;
        }
    	
    	return true;
    }
}
