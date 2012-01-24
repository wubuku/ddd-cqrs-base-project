package org.nthdimenzion.security.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.nthdimenzion.ddd.domain.IdGeneratingArcheType;
import org.nthdimenzion.ddd.domain.annotations.ValueObject;
import org.nthdimenzion.object.utils.EqualsFacilitator;
import org.springframework.util.ObjectUtils;

import javax.persistence.Entity;

@ValueObject
@Entity
public class HomePageDetails extends IdGeneratingArcheType {

    private String homepageViewId;
    private String description;

    HomePageDetails() {

    }

    public HomePageDetails(String permissionId, String description) {
        this.homepageViewId = permissionId;
        this.description = description;
    }

    public String getDescription() {
        return new String(description);
    }

    void setDescription(String description) {
        this.description = description;
    }

    public String getHomepageViewId() {
        return new String(homepageViewId);
    }

    void setHomepageViewId(String homepageViewId) {
        this.homepageViewId = homepageViewId;
    }

    @Override
    public String toString() {
        return new String(homepageViewId);
    }

    @Override
    public boolean equals(Object o) {
        if (EqualsFacilitator.initialChecksPass(o, this)) {
            HomePageDetails obj = (HomePageDetails) o;
            return new EqualsBuilder().reflectionEquals(obj, this);
        }
        return EqualsFacilitator.initialChecksPass(o, this);
    }

    @Override
    public int hashCode() {
        return ObjectUtils.nullSafeHashCode(homepageViewId);
    }

}
