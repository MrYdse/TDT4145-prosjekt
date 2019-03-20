create table exuser (
	uid integer not null auto_increment,
    username varchar(45),
    primary key (uid)
);

create table workout (
	wid integer not null auto_increment,
    datetime datetime,
    note text,
    duration integer unsigned,
    fitness tinyint check (fitness between 1 and 10),
    performance tinyint check (performance between 1 and 10),
    primary key (wid)
);

create table userworkedout (
	uid integer not null,
    wid integer not null,
    primary key (uid, wid),
    foreign key (uid) references exuser(uid)
		on update cascade
        on delete cascade,
    foreign key (wid) references workout(wid)
		on update cascade
        on delete cascade
);

create table machine (
	mid integer not null auto_increment,
    name varchar(45),
    functiondescription text,
    primary key (mid)
);

create table exercise (
	eid integer not null auto_increment,
    name varchar(45),
    primary key (eid)
);

create table freeexercise (
	eid integer not null,
    description text,
    primary key (eid),
    foreign key (eid) references exercise(eid)
		on update cascade
        on delete cascade
);

create table machineexercise (
	eid integer not null,
    kilos int,
    sets int,
    mid integer not null,
    primary key (eid),
	foreign key (eid) references exercise(eid)
		on update cascade
		on delete cascade,
    foreign key (mid) references machine(mid)
		on update cascade
        on delete cascade
);

create table exercisegroup (
    egid integer not null auto_increment,
    name varchar(45),
    primary key (egid)
);

create table exerciseispartofgroup (
	eid integer not null,
    egid integer not null,
    primary key (eid, egid),
    foreign key (eid) references exercise(eid)
		on update cascade
        on delete cascade,
    foreign key (egid) references exercisegroup(egid)
		on update cascade
        on delete cascade
);

create table workoutcontains (
	wid integer not null,
    eid integer not null,
    primary key (wid, eid),
    foreign key (wid) references workout(wid)
		on update cascade
        on delete cascade,
    foreign key (eid) references exercise(eid)
		on update cascade
        on delete cascade
);