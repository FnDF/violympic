package edu.vio.violympic.model;

import java.io.Serializable;

public class TopicSet implements Serializable {

    private String id;
    private String subject;
    private String gradeTh;
    private long timeEventStart;
    private long timeEventEnd;

    private long testTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGradeTh() {
        return gradeTh;
    }

    public void setGradeTh(String gradeTh) {
        this.gradeTh = gradeTh;
    }

    public long getTimeEventStart() {
        return timeEventStart;
    }

    public void setTimeEventStart(long timeEventStart) {
        this.timeEventStart = timeEventStart;
    }

    public long getTimeEventEnd() {
        return timeEventEnd;
    }

    public void setTimeEventEnd(long timeEventEnd) {
        this.timeEventEnd = timeEventEnd;
    }

    public long getTestTime() {
        return testTime;
    }

    public void setTestTime(long testTime) {
        this.testTime = testTime;
    }
}
