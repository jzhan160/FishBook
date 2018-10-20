package com.se.fishbook.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NotificationExample() {
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

        public Criteria andNotificationidIsNull() {
            addCriterion("NotificationId is null");
            return (Criteria) this;
        }

        public Criteria andNotificationidIsNotNull() {
            addCriterion("NotificationId is not null");
            return (Criteria) this;
        }

        public Criteria andNotificationidEqualTo(Integer value) {
            addCriterion("NotificationId =", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotEqualTo(Integer value) {
            addCriterion("NotificationId <>", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidGreaterThan(Integer value) {
            addCriterion("NotificationId >", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("NotificationId >=", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidLessThan(Integer value) {
            addCriterion("NotificationId <", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidLessThanOrEqualTo(Integer value) {
            addCriterion("NotificationId <=", value, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidIn(List<Integer> values) {
            addCriterion("NotificationId in", values, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotIn(List<Integer> values) {
            addCriterion("NotificationId not in", values, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidBetween(Integer value1, Integer value2) {
            addCriterion("NotificationId between", value1, value2, "notificationid");
            return (Criteria) this;
        }

        public Criteria andNotificationidNotBetween(Integer value1, Integer value2) {
            addCriterion("NotificationId not between", value1, value2, "notificationid");
            return (Criteria) this;
        }

        public Criteria andReceiveridIsNull() {
            addCriterion("ReceiverId is null");
            return (Criteria) this;
        }

        public Criteria andReceiveridIsNotNull() {
            addCriterion("ReceiverId is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveridEqualTo(Integer value) {
            addCriterion("ReceiverId =", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotEqualTo(Integer value) {
            addCriterion("ReceiverId <>", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridGreaterThan(Integer value) {
            addCriterion("ReceiverId >", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridGreaterThanOrEqualTo(Integer value) {
            addCriterion("ReceiverId >=", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridLessThan(Integer value) {
            addCriterion("ReceiverId <", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridLessThanOrEqualTo(Integer value) {
            addCriterion("ReceiverId <=", value, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridIn(List<Integer> values) {
            addCriterion("ReceiverId in", values, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotIn(List<Integer> values) {
            addCriterion("ReceiverId not in", values, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridBetween(Integer value1, Integer value2) {
            addCriterion("ReceiverId between", value1, value2, "receiverid");
            return (Criteria) this;
        }

        public Criteria andReceiveridNotBetween(Integer value1, Integer value2) {
            addCriterion("ReceiverId not between", value1, value2, "receiverid");
            return (Criteria) this;
        }

        public Criteria andTriggeridIsNull() {
            addCriterion("TriggerId is null");
            return (Criteria) this;
        }

        public Criteria andTriggeridIsNotNull() {
            addCriterion("TriggerId is not null");
            return (Criteria) this;
        }

        public Criteria andTriggeridEqualTo(Integer value) {
            addCriterion("TriggerId =", value, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridNotEqualTo(Integer value) {
            addCriterion("TriggerId <>", value, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridGreaterThan(Integer value) {
            addCriterion("TriggerId >", value, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridGreaterThanOrEqualTo(Integer value) {
            addCriterion("TriggerId >=", value, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridLessThan(Integer value) {
            addCriterion("TriggerId <", value, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridLessThanOrEqualTo(Integer value) {
            addCriterion("TriggerId <=", value, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridIn(List<Integer> values) {
            addCriterion("TriggerId in", values, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridNotIn(List<Integer> values) {
            addCriterion("TriggerId not in", values, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridBetween(Integer value1, Integer value2) {
            addCriterion("TriggerId between", value1, value2, "triggerid");
            return (Criteria) this;
        }

        public Criteria andTriggeridNotBetween(Integer value1, Integer value2) {
            addCriterion("TriggerId not between", value1, value2, "triggerid");
            return (Criteria) this;
        }

        public Criteria andEventIsNull() {
            addCriterion("Event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("Event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(String value) {
            addCriterion("Event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(String value) {
            addCriterion("Event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(String value) {
            addCriterion("Event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(String value) {
            addCriterion("Event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(String value) {
            addCriterion("Event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(String value) {
            addCriterion("Event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLike(String value) {
            addCriterion("Event like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotLike(String value) {
            addCriterion("Event not like", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<String> values) {
            addCriterion("Event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<String> values) {
            addCriterion("Event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(String value1, String value2) {
            addCriterion("Event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(String value1, String value2) {
            addCriterion("Event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andViewedIsNull() {
            addCriterion("Viewed is null");
            return (Criteria) this;
        }

        public Criteria andViewedIsNotNull() {
            addCriterion("Viewed is not null");
            return (Criteria) this;
        }

        public Criteria andViewedEqualTo(Byte value) {
            addCriterion("Viewed =", value, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedNotEqualTo(Byte value) {
            addCriterion("Viewed <>", value, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedGreaterThan(Byte value) {
            addCriterion("Viewed >", value, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedGreaterThanOrEqualTo(Byte value) {
            addCriterion("Viewed >=", value, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedLessThan(Byte value) {
            addCriterion("Viewed <", value, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedLessThanOrEqualTo(Byte value) {
            addCriterion("Viewed <=", value, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedIn(List<Byte> values) {
            addCriterion("Viewed in", values, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedNotIn(List<Byte> values) {
            addCriterion("Viewed not in", values, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedBetween(Byte value1, Byte value2) {
            addCriterion("Viewed between", value1, value2, "viewed");
            return (Criteria) this;
        }

        public Criteria andViewedNotBetween(Byte value1, Byte value2) {
            addCriterion("Viewed not between", value1, value2, "viewed");
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