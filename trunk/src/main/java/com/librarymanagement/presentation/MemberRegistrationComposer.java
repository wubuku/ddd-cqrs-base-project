package com.librarymanagement.presentation;

import com.librarymanagement.domain.Member;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

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
        Long memberId = (Long)Executions.getCurrent().getArg().get("memberId");
        if(memberId!=null){
            member = crudDao.find(Member.class, memberId);
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

    public void registerMember(){
        Long id = crudDao.add(member);
        navigation.redirect("member");
    }

    public void updaterMember() {
        crudDao.update(member);
        displayMessages.displaySuccess();
    }

}
