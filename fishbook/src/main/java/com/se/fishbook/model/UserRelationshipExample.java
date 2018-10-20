package com.se.fishbook.model;

import java.util.ArrayList;
import java.util.List;

public class UserRelationshipExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserRelationshipExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

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

        public Criteria andRelationshipidIsNull() {
            addCriterion("RelationshipId is null");
            return (Criteria) this;
        }

        public Criteria andRelationshipidIsNotNull() {
            addCriterion("RelationshipId is not null");
            return (Criteria) this;
        }

        public Criteria andRelationshipidEqualTo(Integer value) {
            addCriterion("RelationshipId =", value, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidNotEqualTo(Integer value) {
            addCriterion("RelationshipId <>", value, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidGreaterThan(Integer value) {
            addCriterion("RelationshipId >", value, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidGreaterThanOrEqualTo(Integer value) {
            addCriterion("RelationshipId >=", value, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidLessThan(Integer value) {
            addCriterion("RelationshipId <", value, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidLessThanOrEqualTo(Integer value) {
            addCriterion("RelationshipId <=", value, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidIn(List<Integer> values) {
            addCriterion("RelationshipId in", values, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidNotIn(List<Integer> values) {
            addCriterion("RelationshipId not in", values, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidBetween(Integer value1, Integer value2) {
            addCriterion("RelationshipId between", value1, value2, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andRelationshipidNotBetween(Integer value1, Integer value2) {
            addCriterion("RelationshipId not between", value1, value2, "relationshipid");
            return (Criteria) this;
        }

        public Criteria andFolloweridIsNull() {
            addCriterion("FollowerId is null");
            return (Criteria) this;
        }

        public Criteria andFolloweridIsNotNull() {
            addCriterion("FollowerId is not null");
            return (Criteria) this;
        }

        public Criteria andFolloweridEqualTo(Integer value) {
            addCriterion("FollowerId =", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridNotEqualTo(Integer value) {
            addCriterion("FollowerId <>", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridGreaterThan(Integer value) {
            addCriterion("FollowerId >", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridGreaterThanOrEqualTo(Integer value) {
            addCriterion("FollowerId >=", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridLessThan(Integer value) {
            addCriterion("FollowerId <", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridLessThanOrEqualTo(Integer value) {
            addCriterion("FollowerId <=", value, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridIn(List<Integer> values) {
            addCriterion("FollowerId in", values, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridNotIn(List<Integer> values) {
            addCriterion("FollowerId not in", values, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridBetween(Integer value1, Integer value2) {
            addCriterion("FollowerId between", value1, value2, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweridNotBetween(Integer value1, Integer value2) {
            addCriterion("FollowerId not between", value1, value2, "followerid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidIsNull() {
            addCriterion("FolloweeId is null");
            return (Criteria) this;
        }

        public Criteria andFolloweeidIsNotNull() {
            addCriterion("FolloweeId is not null");
            return (Criteria) this;
        }

        public Criteria andFolloweeidEqualTo(Integer value) {
            addCriterion("FolloweeId =", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidNotEqualTo(Integer value) {
            addCriterion("FolloweeId <>", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidGreaterThan(Integer value) {
            addCriterion("FolloweeId >", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("FolloweeId >=", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidLessThan(Integer value) {
            addCriterion("FolloweeId <", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidLessThanOrEqualTo(Integer value) {
            addCriterion("FolloweeId <=", value, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidIn(List<Integer> values) {
            addCriterion("FolloweeId in", values, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidNotIn(List<Integer> values) {
            addCriterion("FolloweeId not in", values, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidBetween(Integer value1, Integer value2) {
            addCriterion("FolloweeId between", value1, value2, "followeeid");
            return (Criteria) this;
        }

        public Criteria andFolloweeidNotBetween(Integer value1, Integer value2) {
            addCriterion("FolloweeId not between", value1, value2, "followeeid");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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