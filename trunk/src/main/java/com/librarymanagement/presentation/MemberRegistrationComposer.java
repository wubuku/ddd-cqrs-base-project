package com.librarymanagement.presentation;

import com.librarymanagement.domain.Member;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.object.utils.UtilValidator;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;

@Composer
public class MemberRegistrationComposer extends AbstractZkComposer {

    private Member member;

    @Autowired
    private ICrud crudDao;

    private String personRegistrationBtnLbl = "Register Person";
    private boolean isUpdateView = false;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        initializePage();
        String memberId = getParam("memberId");
        if (UtilValidator.isNotEmpty(memberId)) {
            Long memberIdentifier = Long.valueOf(memberId);
            member = crudDao.find(Member.class, memberIdentifier);
            isUpdateView = true;
        }
        comp.setAttribute("member", member);
    }

    private void initializePage() {
        member = new Member();
        personRegistrationBtnLbl = "Register Person";
        isUpdateView = false;
    }

    public boolean isUpdateView() {
        return isUpdateView;
    }

    public boolean isRegisterMemberView() {
        return !isUpdateView();
    }

    public void registerMember() {
        Long id = null;
        try{
        id = crudDao.add(member);
        }catch (Exception ex){
            raiseException(ex);
        }
        if (isSuccess(id))
            navigation.redirect("member");


    }

    public void updaterMember() {
        try{
        crudDao.update(member);
        displayMessages.displaySuccess();
        }catch (Exception ex){
            raiseException(ex);
        }
    }

}
