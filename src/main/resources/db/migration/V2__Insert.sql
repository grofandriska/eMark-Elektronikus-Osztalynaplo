insert into class (id, name) values (1, '1.A');
insert into class (id, name) values (2, '2.A');
insert into class (id, name) values (3, '1.B');
insert into class (id, name) values (4, '2.B');
insert into class (id, name) values (5, '3.A');
insert into class (id, name) values (6, '4.A');
insert into class (id, name) values (7, '5.A');
insert into class (id, name) values (8, '6.A');
insert into class (id, name) values (9, '3.B');
insert into class (id, name) values (10, '4.B');
insert into class (id, name) values (11, '5.B');
insert into class (id, name) values (12, '6.B');

insert into teacher (id, gender, name, subject) values (1, 'Male', 'Nagy László', 'Történelem');
insert into teacher (id, gender, name, subject) values (2, 'Female', 'Kovács Anita', 'Német');
insert into teacher (id, gender, name, subject) values (3, 'Male', 'Erős Ferenc', 'Kémia');
insert into teacher (id, gender, name, subject) values (4, 'Male', 'Dárdás Géza', 'Matematika');
insert into teacher (id, gender, name, subject) values (5, 'Female', 'Herceg Eszter', 'Testnevelés');
insert into teacher (id, gender, name, subject) values (6, 'Female', 'Ottó Győző', 'Fizika');

insert into student (id, class_name_id, gender, student_name) values (1,1,'Male','Szép Ottó');
insert into student (id, class_name_id, gender, student_name) values (2,2,'Male','Magyar Géza');
insert into student (id, class_name_id, gender, student_name) values (3,3,'Female','Hortobágyi Enikő');
insert into student (id, class_name_id, gender, student_name) values (4,2,'Male','Szeles Tamás');
insert into student (id, class_name_id, gender, student_name) values (5,2,'Male','Könyű Roland');
insert into student (id, class_name_id, gender, student_name) values (6,1,'Female','Besztercei Juli');
insert into student (id, class_name_id, gender, student_name) values (7,2,'Female','Holland Tamara');
insert into student (id, class_name_id, gender, student_name) values (8,3,'Female','Fülöp Imola');
insert into student (id, class_name_id, gender, student_name) values (9,1,'Male','Helyes Viktor');
insert into student (id, class_name_id, gender, student_name) values (10,1,'Female','Herceg Ingrid');

insert into mark (id, mark, month, student_id, subject, teacher_id) values (1,4,'April' , 1, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (2,3,'April'  , 2, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (3,4, 'April' , 3, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (4,2, 'May' , 1, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (5,3, 'May' , 2, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (6,5, 'May' , 3, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (7,3, 'April' , 1, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (8,4, 'April' , 2, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (9,3, 'April' , 3, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (10,4 , 'May', 1, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (11,3 ,'May' , 2, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (12,1 , 'May', 3, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (13,2 ,'June' , 1, 'Német' ,2);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (14,3 ,'June' , 2, 'Német', 2);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (15,4 ,'June' , 3, 'Német' ,2);

insert into mark (id, mark, month, student_id, subject, teacher_id) values (16,4,'April' , 5, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (17,3,'April'  , 4, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (18,4, 'April' , 5, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (19,2, 'May' , 4, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (20,3, 'May' , 5, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (21,5, 'May' , 6, 'Kémia', 3);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (22,3, 'April' , 4, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (23,4, 'April' , 5, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (24,3, 'April' , 6, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (25,4 , 'May', 4, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (26,3 ,'May' , 5, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (27,1 , 'May', 6, 'Testnevelés' ,5);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (28,2 ,'June' , 4, 'Német' ,2);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (29,3 ,'June' , 5, 'Német', 2);
insert into mark (id, mark, month, student_id, subject, teacher_id) values (30,4 ,'June' , 6, 'Német' ,2);


