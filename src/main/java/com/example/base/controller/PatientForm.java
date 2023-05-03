package com.example.base.controller;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PatientForm {
    private Long id;
    @NotEmpty(message = "이름을 비우지 말아주세요")
    private String name;

    @NotEmpty(message = "생년월일은 6자리로 입력해주세요")
    @Column(length = 6)
    private String birthday;
    @NotEmpty(message = "성별을 비우지 말아주세요")
    private String gender;

    private String guardianPhoneNumber;
}
