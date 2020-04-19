INSERT INTO USER (EMPID, ACCESSKEY,COUNTRY) VALUES
  ('Miller', 'TRMLLR','SG'),
  ('Tessa', 'TRTSSA','SG'),
  ('Dosson', 'TRDSSN','HK'),
  ('Ricky', 'TRRCKY','SG'),
  ('Aaron', 'TRARON','SG'),
  ('Bob', 'TRBOB','HK');

  INSERT INTO TEAMHIERARCHY(EMPID,MANAGERID) VALUES
  ('Tessa','Miller'),
  ('Dosson','Miller'),
  ('Ricky','Tessa'),
  ('Aaron','Tessa'),
  ('Bob','Dosson');