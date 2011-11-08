package org.nthdimenzion.security.domain;

import com.google.common.collect.Sets;
import org.nthdimenzion.ddd.domain.BaseAggregateRoot;
import org.nthdimenzion.ddd.domain.annotations.AggregateRoot;
import org.nthdimenzion.object.utils.UtilValidator;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@AggregateRoot
@Entity
public class SecurityGroup extends BaseAggregateRoot {

    private Set<SecurityPermission> securityPermissions = Sets.newHashSet();
    private String name;
    private SecurityGroupId securityGroupId;

    SecurityGroup() {
    }

    public SecurityGroup(SecurityGroupId securityGroupId) {
        this.securityGroupId = securityGroupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    public Set<SecurityPermission> getSecurityPermissions() {
        return securityPermissions;
    }

     void setSecurityPermissions(Set<SecurityPermission> securityPermissions) {
        this.securityPermissions = securityPermissions;
    }

    public void add(SecurityPermission securityPermission){
    this.securityPermissions.add(securityPermission);
    }

    public void addAll(Set<SecurityPermission> securityPermissions){
        if(UtilValidator.isNotEmpty(securityPermissions)){
            for(SecurityPermission securityPermission : securityPermissions){
                add(securityPermission);
            }
        }
    }

    @Embedded
    public SecurityGroupId getSecurityGroupId() {
        return securityGroupId;
    }

    public void setSecurityGroupId(SecurityGroupId securityGroupId) {
        this.securityGroupId = securityGroupId;
    }
}
