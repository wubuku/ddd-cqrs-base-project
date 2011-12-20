package com.librarymanagement.presentation;

import com.google.common.collect.Sets;
import com.librarymanagement.domain.Member;
import com.librarymanagement.domain.MemberBuilder;
import com.librarymanagement.presentation.dto.MemberDto;
import com.simplepersoncrud.application.commands.PersonNameChangeCommand;
import com.simplepersoncrud.application.commands.UnRegisterCommand;
import com.simplepersoncrud.application.commands.PersonRegistrationCommand;
import com.simplepersoncrud.presentation.IPersonFinder;
import com.simplepersoncrud.presentation.dto.PersonDetailsDto;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.nthdimenzion.crud.ICrud;
import org.nthdimenzion.presentation.annotations.Composer;
import org.nthdimenzion.presentation.infrastructure.AbstractZkComposer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import java.util.Set;

@Composer
public class MemberRegistrationComposer extends AbstractZkComposer {

    private final Logger logger = LoggerFactory.getLogger(MemberRegistrationComposer.class);
    private MemberDto memberDto = new MemberDto();
    private Member member;

    @Autowired
    private MemberBuilder memberBuilder;

    @Autowired
    private ICrud crudDao;

    private String personRegistrationBtnLbl = "Register Person";
    private boolean isUpdateView = false;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        super.doAfterCompose(comp);
        initializePage();
        Long memberId = (Long)Executions.getCurrent().getArg().get("memberId");
        if(memberId!=null){
            member = crudDao.find(Member.class,memberId);
            memberDto = modelMapper.map(member,MemberDto.class);
            isUpdateView = true;
        }
        comp.setAttribute("dto", memberDto);
    }

    private void initializePage() {
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
        Member member = memberBuilder.createMember(memberDto.firstName,memberDto.lastName,memberDto.dateOfBirth).withMiddleName(memberDto.middleName).build();
        Long id = crudDao.add(member);
        navigation.redirect("member");
    }

    public void updaterMember(){
        Member member = modelMapper.map(memberDto,Member.class);
        crudDao.save(member);
        displayMessages.displaySuccess();
    }

}
