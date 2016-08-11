CREATE TABLE employee (
  id         INT PRIMARY KEY,
  birth_date DATE,
  first_name VARCHAR(50),
  last_name  VARCHAR(50),
  gender     VARCHAR(1)
);

CREATE OR REPLACE PROCEDURE FILTER_EMPLOYEES(name IN VARCHAR2, ref_cursor OUT SYS_REFCURSOR) AS
  BEGIN
    OPEN ref_cursor FOR
    SELECT
      id,
      last_name,
      first_name,
      birth_date
    FROM employee
    WHERE last_name LIKE '%' || name || '%';
  END FILTER_EMPLOYEES;
/

CREATE OR REPLACE PROCEDURE search_employees(
    in_last_name  IN VARCHAR2,
    in_first_name IN VARCHAR2,
    ref_cursor OUT SYS_REFCURSOR)
AS
BEGIN
  OPEN ref_cursor
  FOR
    SELECT id,
    last_name,
    first_name,
    birth_date
    FROM employee
    WHERE last_name LIKE '%' || NVL(in_first_name, '') || '%'
    OR first_name LIKE '%' || NVL(in_last_name, '') || '%';
END search_employees;
/

INSERT INTO employee VALUES (1, '19-JAN-1990', 'Ilon', 'Paiva', 'M');
INSERT INTO employee VALUES (2, '12-MAR-92', 'Scub', 'Slub', 'F')
