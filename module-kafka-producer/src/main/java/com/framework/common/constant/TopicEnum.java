package com.framework.common.constant;

public enum TopicEnum {

    TEST_TOPIC_1("TEST.TOPIC.1"),
    TEST_TOPIC_2("TEST.TOPIC.2");

    private final String topicName;


    private TopicEnum(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return this.topicName;
    }
}
