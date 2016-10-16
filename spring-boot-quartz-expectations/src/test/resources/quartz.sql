DROP TABLE qrtz_locks
IF EXISTS;
DROP TABLE qrtz_scheduler_state
IF EXISTS;
DROP TABLE qrtz_fired_triggers
IF EXISTS;
DROP TABLE qrtz_paused_trigger_grps
IF EXISTS;
DROP TABLE qrtz_calendars
IF EXISTS;
DROP TABLE qrtz_blob_triggers
IF EXISTS;
DROP TABLE qrtz_cron_triggers
IF EXISTS;
DROP TABLE qrtz_simple_triggers
IF EXISTS;
DROP TABLE qrtz_simprop_triggers
IF EXISTS;
DROP TABLE qrtz_triggers
IF EXISTS;
DROP TABLE qrtz_job_details
IF EXISTS;

CREATE TABLE qrtz_job_details
(
  SCHED_NAME        VARCHAR(120) NOT NULL,
  JOB_NAME          VARCHAR(200) NOT NULL,
  JOB_GROUP         VARCHAR(200) NOT NULL,
  DESCRIPTION       VARCHAR(250) NULL,
  JOB_CLASS_NAME    VARCHAR(250) NOT NULL,
  IS_DURABLE        BOOLEAN      NOT NULL,
  IS_NONCONCURRENT  BOOLEAN      NOT NULL,
  IS_UPDATE_DATA    BOOLEAN      NOT NULL,
  REQUESTS_RECOVERY BOOLEAN      NOT NULL,
  JOB_DATA          BLOB         NULL,
  CONSTRAINT QRTZ_JOB_DETAILS_PK PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
);
CREATE TABLE qrtz_triggers
(
  SCHED_NAME     VARCHAR(120) NOT NULL,
  TRIGGER_NAME   VARCHAR(200) NOT NULL,
  TRIGGER_GROUP  VARCHAR(200) NOT NULL,
  JOB_NAME       VARCHAR(200) NOT NULL,
  JOB_GROUP      VARCHAR(200) NOT NULL,
  DESCRIPTION    VARCHAR(250) NULL,
  NEXT_FIRE_TIME NUMERIC(19)  NULL,
  PREV_FIRE_TIME NUMERIC(19)  NULL,
  PRIORITY       NUMERIC(13)  NULL,
  TRIGGER_STATE  VARCHAR(16)  NOT NULL,
  TRIGGER_TYPE   VARCHAR(8)   NOT NULL,
  START_TIME     NUMERIC(19)  NOT NULL,
  END_TIME       NUMERIC(19)  NULL,
  CALENDAR_NAME  VARCHAR(200) NULL,
  MISFIRE_INSTR  NUMERIC(2)   NULL,
  JOB_DATA       BLOB         NULL,
  CONSTRAINT QRTZ_TRIGGERS_PK PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  CONSTRAINT QRTZ_TRIGGER_TO_JOBS_FK FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
  REFERENCES QRTZ_JOB_DETAILS (SCHED_NAME, JOB_NAME, JOB_GROUP)
);
CREATE TABLE qrtz_simple_triggers
(
  SCHED_NAME      VARCHAR(120) NOT NULL,
  TRIGGER_NAME    VARCHAR(200) NOT NULL,
  TRIGGER_GROUP   VARCHAR(200) NOT NULL,
  REPEAT_COUNT    NUMERIC(7)   NOT NULL,
  REPEAT_INTERVAL NUMERIC(12)  NOT NULL,
  TIMES_TRIGGERED NUMERIC(10)  NOT NULL,
  CONSTRAINT QRTZ_SIMPLE_TRIG_PK PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  CONSTRAINT QRTZ_SIMPLE_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE qrtz_cron_triggers
(
  SCHED_NAME      VARCHAR(120) NOT NULL,
  TRIGGER_NAME    VARCHAR(200) NOT NULL,
  TRIGGER_GROUP   VARCHAR(200) NOT NULL,
  CRON_EXPRESSION VARCHAR(120) NOT NULL,
  TIME_ZONE_ID    VARCHAR(80),
  CONSTRAINT QRTZ_CRON_TRIG_PK PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  CONSTRAINT QRTZ_CRON_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE qrtz_simprop_triggers
(
  SCHED_NAME    VARCHAR(120)   NOT NULL,
  TRIGGER_NAME  VARCHAR(200)   NOT NULL,
  TRIGGER_GROUP VARCHAR(200)   NOT NULL,
  STR_PROP_1    VARCHAR(512)   NULL,
  STR_PROP_2    VARCHAR(512)   NULL,
  STR_PROP_3    VARCHAR(512)   NULL,
  INT_PROP_1    NUMERIC(10)    NULL,
  INT_PROP_2    NUMERIC(10)    NULL,
  LONG_PROP_1   NUMERIC(13)    NULL,
  LONG_PROP_2   NUMERIC(13)    NULL,
  DEC_PROP_1    NUMERIC(13, 4) NULL,
  DEC_PROP_2    NUMERIC(13, 4) NULL,
  BOOL_PROP_1   VARCHAR(1)     NULL,
  BOOL_PROP_2   VARCHAR(1)     NULL,
  CONSTRAINT QRTZ_SIMPROP_TRIG_PK PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  CONSTRAINT QRTZ_SIMPROP_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE qrtz_blob_triggers
(
  SCHED_NAME    VARCHAR(120) NOT NULL,
  TRIGGER_NAME  VARCHAR(200) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  BLOB_DATA     BLOB         NULL,
  CONSTRAINT QRTZ_BLOB_TRIG_PK PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
  CONSTRAINT QRTZ_BLOB_TRIG_TO_TRIG_FK FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
  REFERENCES QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);
CREATE TABLE qrtz_calendars
(
  SCHED_NAME    VARCHAR(120) NOT NULL,
  CALENDAR_NAME VARCHAR(200) NOT NULL,
  CALENDAR      BLOB         NOT NULL,
  CONSTRAINT QRTZ_CALENDARS_PK PRIMARY KEY (SCHED_NAME, CALENDAR_NAME)
);
CREATE TABLE qrtz_paused_trigger_grps
(
  SCHED_NAME    VARCHAR(120) NOT NULL,
  TRIGGER_GROUP VARCHAR(200) NOT NULL,
  CONSTRAINT QRTZ_PAUSED_TRIG_GRPS_PK PRIMARY KEY (SCHED_NAME, TRIGGER_GROUP)
);
CREATE TABLE qrtz_fired_triggers
(
  SCHED_NAME        VARCHAR(120) NOT NULL,
  ENTRY_ID          VARCHAR(95)  NOT NULL,
  TRIGGER_NAME      VARCHAR(200) NOT NULL,
  TRIGGER_GROUP     VARCHAR(200) NOT NULL,
  INSTANCE_NAME     VARCHAR(200) NOT NULL,
  FIRED_TIME        NUMERIC(19)  NOT NULL,
  SCHED_TIME        NUMERIC(19)  NOT NULL,
  PRIORITY          NUMERIC(13)  NOT NULL,
  STATE             VARCHAR(16)  NOT NULL,
  JOB_NAME          VARCHAR(200) NULL,
  JOB_GROUP         VARCHAR(200) NULL,
  IS_NONCONCURRENT  BOOLEAN      NULL,
  REQUESTS_RECOVERY BOOLEAN      NULL,
  CONSTRAINT QRTZ_FIRED_TRIGGER_PK PRIMARY KEY (SCHED_NAME, ENTRY_ID)
);
CREATE TABLE qrtz_scheduler_state
(
  SCHED_NAME        VARCHAR(120) NOT NULL,
  INSTANCE_NAME     VARCHAR(200) NOT NULL,
  LAST_CHECKIN_TIME NUMERIC(19)  NOT NULL,
  CHECKIN_INTERVAL  NUMERIC(13)  NOT NULL,
  CONSTRAINT QRTZ_SCHEDULER_STATE_PK PRIMARY KEY (SCHED_NAME, INSTANCE_NAME)
);
CREATE TABLE qrtz_locks
(
  SCHED_NAME VARCHAR(120) NOT NULL,
  LOCK_NAME  VARCHAR(40)  NOT NULL,
  CONSTRAINT QRTZ_LOCKS_PK PRIMARY KEY (SCHED_NAME, LOCK_NAME)
);