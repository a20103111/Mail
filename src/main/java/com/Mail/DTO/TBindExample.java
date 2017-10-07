package com.Mail.DTO;

import java.util.ArrayList;
import java.util.List;

public class TBindExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public TBindExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserMailIsNull() {
            addCriterion("user_mail is null");
            return (Criteria) this;
        }

        public Criteria andUserMailIsNotNull() {
            addCriterion("user_mail is not null");
            return (Criteria) this;
        }

        public Criteria andUserMailEqualTo(Integer value) {
            addCriterion("user_mail =", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotEqualTo(Integer value) {
            addCriterion("user_mail <>", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailGreaterThan(Integer value) {
            addCriterion("user_mail >", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_mail >=", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailLessThan(Integer value) {
            addCriterion("user_mail <", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailLessThanOrEqualTo(Integer value) {
            addCriterion("user_mail <=", value, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailIn(List<Integer> values) {
            addCriterion("user_mail in", values, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotIn(List<Integer> values) {
            addCriterion("user_mail not in", values, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailBetween(Integer value1, Integer value2) {
            addCriterion("user_mail between", value1, value2, "userMail");
            return (Criteria) this;
        }

        public Criteria andUserMailNotBetween(Integer value1, Integer value2) {
            addCriterion("user_mail not between", value1, value2, "userMail");
            return (Criteria) this;
        }

        public Criteria andMailAccIsNull() {
            addCriterion("mail_acc is null");
            return (Criteria) this;
        }

        public Criteria andMailAccIsNotNull() {
            addCriterion("mail_acc is not null");
            return (Criteria) this;
        }

        public Criteria andMailAccEqualTo(String value) {
            addCriterion("mail_acc =", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccNotEqualTo(String value) {
            addCriterion("mail_acc <>", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccGreaterThan(String value) {
            addCriterion("mail_acc >", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccGreaterThanOrEqualTo(String value) {
            addCriterion("mail_acc >=", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccLessThan(String value) {
            addCriterion("mail_acc <", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccLessThanOrEqualTo(String value) {
            addCriterion("mail_acc <=", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccLike(String value) {
            addCriterion("mail_acc like", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccNotLike(String value) {
            addCriterion("mail_acc not like", value, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccIn(List<String> values) {
            addCriterion("mail_acc in", values, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccNotIn(List<String> values) {
            addCriterion("mail_acc not in", values, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccBetween(String value1, String value2) {
            addCriterion("mail_acc between", value1, value2, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAccNotBetween(String value1, String value2) {
            addCriterion("mail_acc not between", value1, value2, "mailAcc");
            return (Criteria) this;
        }

        public Criteria andMailAuthIsNull() {
            addCriterion("mail_auth is null");
            return (Criteria) this;
        }

        public Criteria andMailAuthIsNotNull() {
            addCriterion("mail_auth is not null");
            return (Criteria) this;
        }

        public Criteria andMailAuthEqualTo(String value) {
            addCriterion("mail_auth =", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthNotEqualTo(String value) {
            addCriterion("mail_auth <>", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthGreaterThan(String value) {
            addCriterion("mail_auth >", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthGreaterThanOrEqualTo(String value) {
            addCriterion("mail_auth >=", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthLessThan(String value) {
            addCriterion("mail_auth <", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthLessThanOrEqualTo(String value) {
            addCriterion("mail_auth <=", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthLike(String value) {
            addCriterion("mail_auth like", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthNotLike(String value) {
            addCriterion("mail_auth not like", value, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthIn(List<String> values) {
            addCriterion("mail_auth in", values, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthNotIn(List<String> values) {
            addCriterion("mail_auth not in", values, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthBetween(String value1, String value2) {
            addCriterion("mail_auth between", value1, value2, "mailAuth");
            return (Criteria) this;
        }

        public Criteria andMailAuthNotBetween(String value1, String value2) {
            addCriterion("mail_auth not between", value1, value2, "mailAuth");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_bind
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_bind
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}