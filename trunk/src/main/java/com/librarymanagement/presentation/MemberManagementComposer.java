package com.librarymanagement.presentation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.librarymanagement.domain.BookLending;
import com.librarymanagement.domain.IBookLendingRepository;
import com.librarymanagement.domain.Member;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;

import java.util.List;

@Composer
public class MemberManagementComposer extends AbstractZkComposer {

    private List<Member> members = Lists.newArrayList();

    @Autowired
    private ICrud crudDao;

    @Autowired
    private IBookLendingRepository bookLendingRepository;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        members = getMembers();
        comp.setAttribute("members", members);
    }

    public List<Member> getMembers() {
        List<Member> members = crudDao.getAll(Member.class);
        return UtilValidator.isNotEmpty(members) ? members : null;
    }

    public boolean isNoRecordsFound() {
        return UtilValidator.isEmpty(members);
    }

    public boolean isRecordsFound(){
        return !isNoRecordsFound();
    }

    public void selectMember(Long memberId,String pageId) {
        navigation.navigateToDefaultContainer(pageId, ImmutableMap.of("memberId", memberId));
    }


    public void unRegisterMembers(List<Member> unRegisteredMembers) {
        for (Member unRegisteredMember : unRegisteredMembers) {
            if(isMemberHavingBooks(unRegisteredMember)){
                displayErrorMessage();
            }else{
            crudDao.deactivate(unRegisteredMember);
            displayMessages.showSuccess("Member unregistered successfully");
            }
        }
    }

    private void displayErrorMessage() {
        displayMessages.clearMessage();
        displayMessages.displayError("Member cannot be unregistered,because he/she has not returned all books");
    }

    private boolean isMemberHavingBooks(Member unRegisteredMember) {
        List<BookLending> bookLendings = bookLendingRepository.findAllBooksWithMember(unRegisteredMember);
        boolean isMemberHavingBooks = UtilValidator.isNotEmpty(bookLendings);
        return isMemberHavingBooks;
    }
}

