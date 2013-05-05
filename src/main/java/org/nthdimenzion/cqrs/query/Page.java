/*
 * Copyright 2008-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nthdimenzion.cqrs.query;

import com.google.common.base.Preconditions;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 */
public class Page<T> implements IPage<T> {

    private class PageMarker {
        final int pageNumber;
        final int pageSize;

        private PageMarker(RowBounds rowBounds) {
            Preconditions.checkArgument(rowBounds.getOffset()%rowBounds.getLimit()==0);
            this.pageSize = rowBounds.getLimit();
            this.pageNumber = (rowBounds.getOffset()/this.pageSize)+1;
        }
    }


    private final List<T> content = new ArrayList<T>();
    private final PageMarker pageMarker;
    private final long total;


    /**
     * Constructor of {@code PageImpl}.
     *
     * @param content   the content of this page
     * @param rowBounds the paging information
     * @param total     the total amount of items available
     */
    public Page(List<T> content, RowBounds rowBounds, long total) {

        if (null == content) {
            throw new IllegalArgumentException("Content must not be null!");
        }

        this.content.addAll(content);
        this.total = total;
        this.pageMarker = new PageMarker(rowBounds);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#getNumber()
     */
    public int getNumber() {

        return pageMarker == null ? 0 : pageMarker.pageNumber;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#getSize()
     */
    public int getSize() {

        return pageMarker == null ? 0 : pageMarker.pageSize;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#getTotalPages()
     */
    public int getTotalPages() {

        return getSize() == 0 ? 0 : (int) Math.ceil((double) total / (double) getSize());
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#getNumberOfElements()
     */
    public int getNumberOfElements() {

        return content.size();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#getTotalElements()
     */
    public long getTotalElements() {

        return total;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#hasPreviousPage()
     */
    public boolean hasPreviousPage() {

        return getNumber() > 0;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#isFirstPage()
     */
    public boolean isFirstPage() {

        return !hasPreviousPage();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#hasNextPage()
     */
    public boolean hasNextPage() {

        return ((getNumber() + 1) * getSize()) < total;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#isLastPage()
     */
    public boolean isLastPage() {

        return !hasNextPage();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#iterator()
     */
    public Iterator<T> iterator() {

        return content.iterator();
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.data.domain.Page#asList()
     */
    public List<T> getContent() {

        return Collections.unmodifiableList(content);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.domain.Page#hasContent()
     */
    public boolean hasContent() {

        return !content.isEmpty();
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String contentType = "UNKNOWN";

        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances",
                getNumber(), getTotalPages(), contentType);
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Page<?>)) {
            return false;
        }

        Page<?> that = (Page<?>) obj;

        boolean totalEqual = this.total == that.total;
        boolean contentEqual = this.content.equals(that.content);
        boolean pageableEqual = this.pageMarker == null ? that.pageMarker == null : this.pageMarker.equals(that.pageMarker);

        return totalEqual && contentEqual && pageableEqual;
    }


    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;

        result = 31 * result + (int) (total ^ total >>> 32);
        result = 31 * result + (pageMarker == null ? 0 : pageMarker.hashCode());
        result = 31 * result + content.hashCode();

        return result;
    }
}
