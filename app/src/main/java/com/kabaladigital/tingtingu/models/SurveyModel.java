package com.kabaladigital.tingtingu.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SurveyModel {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("surveys")
    @Expose
    private List<Survey> surveys = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public class LangList {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name_english")
        @Expose
        private String nameEnglish;
        @SerializedName("name_language")
        @Expose
        private String nameLanguage;
        @SerializedName("code")
        @Expose
        private String code;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNameEnglish() {
            return nameEnglish;
        }

        public void setNameEnglish(String nameEnglish) {
            this.nameEnglish = nameEnglish;
        }

        public String getNameLanguage() {
            return nameLanguage;
        }

        public void setNameLanguage(String nameLanguage) {
            this.nameLanguage = nameLanguage;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

    }


    public class Survey {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("multilingual")
        @Expose
        private String multilingual;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("endDate")
        @Expose
        private String endDate;
        @SerializedName("surveyCategory")
        @Expose
        private String surveyCategory;
        @SerializedName("duration")
        @Expose
        private Integer duration;
        @SerializedName("benefitValue1")
        @Expose
        private String benefitValue1;
        @SerializedName("benefitValue2")
        @Expose
        private String benefitValue2;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("code")
        @Expose
        private String code;
        @SerializedName("questionCount")
        @Expose
        private Integer questionCount;
        @SerializedName("langList")
        @Expose
        private List<LangList> langList = null;
        @SerializedName("benefitType")
        @Expose
        private Integer benefitType;
        @SerializedName("benefitTypeDes")
        @Expose
        private String benefitTypeDes;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMultilingual() {
            return multilingual;
        }

        public void setMultilingual(String multilingual) {
            this.multilingual = multilingual;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getSurveyCategory() {
            return surveyCategory;
        }

        public void setSurveyCategory(String surveyCategory) {
            this.surveyCategory = surveyCategory;
        }

        public Integer getDuration() {
            return duration;
        }

        public void setDuration(Integer duration) {
            this.duration = duration;
        }

        public String getBenefitValue1() {
            return benefitValue1;
        }

        public void setBenefitValue1(String benefitValue1) {
            this.benefitValue1 = benefitValue1;
        }

        public String getBenefitValue2() {
            return benefitValue2;
        }

        public void setBenefitValue2(String benefitValue2) {
            this.benefitValue2 = benefitValue2;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public Integer getQuestionCount() {
            return questionCount;
        }

        public void setQuestionCount(Integer questionCount) {
            this.questionCount = questionCount;
        }

        public List<LangList> getLangList() {
            return langList;
        }

        public void setLangList(List<LangList> langList) {
            this.langList = langList;
        }

        public Integer getBenefitType() {
            return benefitType;
        }

        public void setBenefitType(Integer benefitType) {
            this.benefitType = benefitType;
        }

        public String getBenefitTypeDes() {
            return benefitTypeDes;
        }

        public void setBenefitTypeDes(String benefitTypeDes) {
            this.benefitTypeDes = benefitTypeDes;
        }
    }
}
