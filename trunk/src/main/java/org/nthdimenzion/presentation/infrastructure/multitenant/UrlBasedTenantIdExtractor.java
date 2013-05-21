package org.nthdimenzion.presentation.infrastructure.multitenant;

import com.google.common.base.Function;

/**
 * Created by IntelliJ IDEA.
 * User: Nthdimenzion
 * Date: 21/5/13
 * Time: 11:27 AM
 */
public class UrlBasedTenantIdExtractor implements Function<String,String> {
    @Override
    public String apply(final String input) {
        String trimmedInput = input.trim();
        if(trimmedInput.toLowerCase().startsWith("www".toLowerCase())){
            trimmedInput = trimmedInput.replaceFirst("www\\.","");
        }
        final String tenantId = trimmedInput.substring(0,trimmedInput.indexOf("."));
        return tenantId;
    }
}
