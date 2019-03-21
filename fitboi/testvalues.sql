set FOREIGN_KEY_CHECKS=0;
truncate freeexercise;
truncate machineexercise;
truncate exercise;
truncate machine;
truncate userworkedout;
truncate workout;
truncate exuser;
truncate exercisegroup;
truncate exerciseispartofgroup;
set FOREIGN_KEY_CHECKS=1;

insert into exuser (username) values ("pete");
insert into workout (wodatetime, note, duration, fitness, performance) values ("2019-03-21 13:15:20", "Good session", 1200, 7, 8);
insert into workout (wodatetime, note, duration, fitness, performance) values ("2019-03-20 11:22:34", "Bad session", 600, 3, 4);
insert into userworkedout (uid, wid) values (1, 1);
insert into userworkedout (uid, wid) values (1, 2);
insert into exercise (name) values ("Pushups");
insert into freeexercise (eid, description) values (1, "Pushing the earth away repeatedly");
insert into machine (name, functiondescription) values ("Weightbench", "Used for benchpressing");
insert into exercise (name) values ("Benchpress");
insert into machineexercise (eid, kilos, sets, mid) values (2, 15, 5, 1);
insert into workoutcontains (wid, eid) values (1, 1);
insert into workoutcontains (wid, eid) values (2, 2);
insert into exercisegroup (name) values ("Strength exercises");
insert into exerciseispartofgroup (eid, egid) values (1, 1);
insert into exerciseispartofgroup (eid, egid) values (2, 1);