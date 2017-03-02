DROP TABLE IF EXISTS book;
;;;
DROP TRIGGER IF EXISTS add_book_last_modified_date;
;;
CREATE TABLE book (
  id                 BIGINT GENERATED BY DEFAULT AS IDENTITY (
  START WITH 1 ),
  last_modified_date TIMESTAMP,
  version            BIGINT,
  name               VARCHAR(255),
  PRIMARY KEY (id)
);
;;
CREATE TRIGGER add_book_last_modified_date
BEFORE INSERT ON book
  REFERENCING NEW AS newrow
FOR EACH ROW
  SET last_modified_date = TIMESTAMP(0) + SESSION_TIMEZONE();
;;
CREATE TRIGGER update_book_last_modified_date
BEFORE UPDATE ON book
  REFERENCING NEW AS newrow
FOR EACH ROW
  SET last_modified_date = TIMESTAMPADD(SQL_TSI_MILLI_SECOND, 1, newrow.last_modified_date);
;;
