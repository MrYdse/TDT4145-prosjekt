set FOREIGN_KEY_CHECKS=0;
truncate exercise;
truncate exercisegroup;
truncate exerciseispartofgroup;
truncate exuser;
truncate freeexercise;
truncate machine;
truncate machineexercise;
truncate userworkedout;
truncate workout;
truncate workoutcontains;
set FOREIGN_KEY_CHECKS=1;

insert into exuser (username) values ("pete"); # uid 1
insert into workout (wodatetime, note, duration, fitness, performance) values ("2019-03-21 13:15:20", "Felt like I was air", 1200, 7, 8); # wid 1
insert into workout (wodatetime, note, duration, fitness, performance) values ("2019-03-20 11:22:34", "Felt like I was Snorlax", 600, 3, 4); # wid 2
insert into workout (wodatetime, note, duration, fitness, performance) values ("2019-03-05 12:31:17", "Broke my tailbone", 2400, 1, 1); # wid 3
insert into workout (wodatetime, note, duration, fitness, performance) values ("2019-02-01 09:11:02", "Got ripped", 3600, 10, 10); # wid 4
insert into userworkedout (uid, wid) values (1, 1);
insert into userworkedout (uid, wid) values (1, 2);
insert into userworkedout (uid, wid) values (1, 3);
insert into userworkedout (uid, wid) values (1, 4);
insert into exercise (name) values ("Pushups"); # eid 1
insert into freeexercise (eid, description) values (1, "Pushing the earth away repeatedly");
insert into machine (name, functiondescription) values ("Weightbench", "Used for benchpressing"); # mid 1
insert into exercise (name) values ("Benchpress"); # eid 2
insert into machineexercise (eid, kilos, sets, mid) values (2, 15, 5, 1);
insert into exercise (name) values ("Jogging"); # eid 3
insert into freeexercise (eid, description) values (3, "Moving one leg in front of the other really fast");
insert into workoutcontains (wid, eid) values (1, 1);
insert into workoutcontains (wid, eid) values (2, 2);
insert into workoutcontains (wid, eid) values (3, 3);
insert into workoutcontains (wid, eid) values (4, 2);
insert into exercisegroup (name) values ("Strength exercises"); # egid 1
insert into exerciseispartofgroup (eid, egid) values (1, 1);
insert into exerciseispartofgroup (eid, egid) values (2, 1);
insert into exercisegroup (name) values ("Endurance exercises"); # egid 2
insert into exerciseispartofgroup (eid, egid) values (3, 2);